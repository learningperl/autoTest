package com.test.service;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.HashMap;

import com.test.statics.Mysql;
import com.test.tools.ChromeDriver;
import com.test.tools.IEDriver;
import com.test.tools.ImageTool;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class servicerunTest {				//UI用例执行类，未使用多线程，返回很慢
	public static String state = "初始化";
	public static String actualRes = "NULL";
	private static String path = "";
	private static String Browser = "IE";
	private static ChromeDriver c = null;
	private static IEDriver e = null;
	public static WebDriver driver = null;
	public static HashMap<String, String> map = null;
	public static boolean runable = true;

	public servicerunTest() {
		servicerunTest.state = "等待运行";
		File directory = new File("");
		try {
			path = directory.getCanonicalPath();
		} catch (IOException e) {
			path = "";
		}
	}

	public static void run(String[] check) {//用例场景执行方法
		runable = false;
		state = "正在运行...";
		String sql = "";
		String sqlu = "";
		String id = "";
		String str = "";
		try {
			Statement sm = Mysql.ct.createStatement();
			Statement sm1 = Mysql.ct.createStatement();
			for (int i = 0; i < check.length; i++) {
				try {
					map = new HashMap<String, String>();
					sql = "select Browser from casescene where casesId="	//查询所选用例场景用例
							+ check[i] + ";";
					ResultSet rs = sm.executeQuery(sql);
					rs.next();
					Browser = rs.getString(1);
					// System.out.println(Browser);
					sql = "select * from caseoption where casesId=" + check[i] + " order by order_id;";
					rs = null;
					rs = sm.executeQuery(sql);
					while (rs.next()) {
						ResultSetMetaData rsmd = rs.getMetaData();
						for (int j = 1; j <= rsmd.getColumnCount(); j++) {
							if (j == 1)
								id = rs.getString(j);
							map.put(rsmd.getColumnName(j), rs.getString(j));
						}
						try{
						//System.out.println("**88888");
						str = runCase();
						sqlu = "update caseoption set imgName='"
								+ ImageTool.ScreenSnapshot(id) + "', runState='"
								+ str + "',actualRes='" + actualRes + "' where id=" + id;
						//System.out.println(sqlu);
						sm1.executeUpdate(sqlu);
						}catch (Exception e){
							e.printStackTrace();
						}
					}
					try {
						rs=null;
						sql = "select id from caseoption where casesId=" + check[i] + " and runState='FAIL';";
						rs = sm.executeQuery(sql);
						rs.next();
						try{
							if(rs != null && rs.getString(1) != null)
								sqlu="update casescene set runStates='PASS';";
							else
								sqlu="update casescene set runStates='FAIL';";
						}catch (Exception e){
							sqlu="update casescene set runStates='PASS';";
						}
						sm.executeUpdate(sqlu);	
						driver.quit();
						driver = null;
					} catch (Exception e) {
						e.printStackTrace();
					}
				} catch (Exception e) {

				}
			}
			state = "运行完毕";
		} catch (Exception e) {
			e.printStackTrace();
		}
		runable = true;
	}

	public static String runCase() {//单用例执行方法
		runable = false;
		String s = "PASS";
		String res="";
		actualRes="NULL";
		if (driver == null && !map.get("optionss").equals("open"))
			if (Browser.equals("") || Browser.equals("Chrome")
					|| Browser.equals("chrome") || Browser == null) {
				ChromeDriver browser1 = new ChromeDriver(
						"C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe",
						path
								+ "\\workspace1\\WebTester\\WebContent\\WEB-INF\\lib\\chromedriver.exe");
				driver = browser1.getdriver();
			} else if (Browser.equals("IE") || Browser.equals("ie")
					|| Browser.equals("Ie")) {
				IEDriver browser2 = new IEDriver(
						"",
						path
								+ "\\workspace1\\WebTester\\WebContent\\WEB-INF\\lib\\IEDriver.exe");
				driver = browser2.getdriver();
			} else
				System.out.println("log--error:暂时不支持" + map.get("Browser")
						+ "浏览器。");

		switch (map.get("optionss")) {			//UI操作方法和实现
		case "open":
			driver = null;
			if (Browser.equals("") || Browser.equals("Chrome")
					|| Browser.equals("chrome") || Browser == null) {
				System.out
						.println(path
								+ "\\workspace1\\WebTester\\WebContent\\WEB-INF\\lib\\chromedriver.exe");
				c = new ChromeDriver(
						"C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe",
						path + "\\workspace\\Static\\Selenium\\chromedriver.exe");
				driver = c.getdriver();
			} else if (Browser.equals("IE") || Browser.equals("ie")
					|| Browser.equals("Ie")) {
				e = new IEDriver(
						"",
						path
								+ "\\workspace1\\WebTester\\WebContent\\WEB-INF\\lib\\IEDriver.exe");
				driver = e.getdriver();
			} else
				System.out.println("log--error:暂时不支持" + Browser + "浏览器。");

		case "get":
			try {
				Thread.sleep(1000);
				driver.get(map.get("xPath"));
				Thread.sleep(3000);
			} catch (Exception e) {
				s="FAIL";
				actualRes="打开网页失败！";
				System.out.println("log--warn:打开网页失败！");
			}
			break;

		case "click":
			try {
				driver.findElement(By.xpath(map.get("xPath").toString()))
						.click();
				Thread.sleep(2000);
			} catch (Exception e) {
				s="FAIL";
				actualRes="元素点击失败！";
				System.out.println("log--error:元素" + map.get("xPath") + "点击失败！");
			}
			break;

		case "sendKeys":
			try {
				driver.findElement(By.xpath(map.get("xPath").toString()))
						.clear();
				driver.findElement(By.xpath(map.get("xPath").toString()))
						.sendKeys(map.get("datas"));
				Thread.sleep(100);
			} catch (Exception e) {
				s="FAIL";
				actualRes="元素赋值失败！";
				System.out.println("log--error元素" + map.get("xPath") + "赋值失败！");
			}
			break;

		case "select":
			try {
				Select select = new Select(driver.findElement(By.xpath(map.get(
						"xPath").toString())));
				try {
					select.selectByValue(map.get("datas"));
				} catch (Exception e1) {
					select.selectByVisibleText(map.get("datas"));
				}
				Thread.sleep(500);
			} catch (Exception e) {
				s="FAIL";
				actualRes="元素查找或者选择失败！";
				System.out.println("log--error:元素查找或者选择失败！");
			}
			break;

		case "getText":
			try {
				Thread.sleep(3000);
				//System.out.println(driver.findElement(By.xpath(map.get("xPath").toString())));
				res = driver.findElement(By.xpath(map.get("xPath").toString())).getText().toString();
				Thread.sleep(100);
			} catch (Exception e) {
				s="FAIL";
				actualRes="元素获取失败！";
				System.out.println("log--error元素" + map.get("xPath") + "获取失败！");
			}
			break;

		default:
			s="FAIL";
			actualRes="暂时不支持该关键字操作";
			System.out.println("log--error:暂时不支持该关键字"+map.get("optionss")+"操作网页元素！");
		}
		
		switch (map.get("checkMethod")) {		//UI校验方法和实现
		case "NULL":
			break;
		case "equel":
			if(res.equals(map.get("expectedRes").toString()))
				s="PASS";
			else
				s="FAIL";
			actualRes=res;
			break;
		case "contain":
			if(res.contains(map.get("expectedRes").toString()))
				s="PASS";
			else
				s="FAIL";
			actualRes=res;
			break;
		default:
			s="FAIL";
			actualRes=res;
			System.out.println("log--error:暂时不支持该关键字："+map.get("checkMethod")+"处理结果！");
		}

		runable = true;
		return s;
	}
}
