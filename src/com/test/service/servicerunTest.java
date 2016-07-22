package com.test.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.test.statics.Mysql;
import com.test.statics.property;
import com.test.statics.responseList;
import com.test.tools.ChromeDriver;
import com.test.tools.IEDriver;
import com.test.tools.ImageTool;
import com.test.tools.jsonPase;
import com.test.tools.sendUrl;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class servicerunTest { // UI用例执行类
	public static String state = "初始化";
	public static String actualRes = "NULL";
	private static String path = "";
	public static String parameter = "";
	private static String Browser = "IE";
	private static String verifyStr = "";
	private static sendUrl obj = null;
	private static jsonPase json = null;
	private static ChromeDriver c = null;
	private static IEDriver e = null;
	public static WebDriver driver = null;
	public static HashMap<String, String> map = null;
	public static boolean runable = true;
	public static int id = 0;
	public static ArrayList <Map<String,String>> rss = null;

	public servicerunTest() {
		servicerunTest.state = "开始运行";
		File directory = new File("");
		obj = new sendUrl();
		json = new jsonPase();
		try {
			path = directory.getCanonicalPath();
		} catch (IOException e) {
			path = "";
		}
	}

	public static void run(String[] check) {// 用例场景执行方法
		runable = false;
		state = "正在运行...";
		String sql = "";
		String sqlu = "";
		String caseid = "";
		String str = "";
		rss = new ArrayList<Map<String,String>>();

		try {
			Statement sm = Mysql.ct.createStatement();
			Statement sm1 = Mysql.ct.createStatement();
			for (int i = 0; i < check.length; i++) {
				try {
					sql = "select Browser from casescene where casesId=" // 查询所选用例场景用例
							+ check[i] + ";";
					ResultSet rs = sm.executeQuery(sql);
					rs.next();
					Browser = rs.getString(1);
					// System.out.println(Browser);
					sql = "select * from caseoption where casesId=" + check[i] + " order by order_id;";
					rs = null;
					rs = sm.executeQuery(sql);
					int ii=0;
					while (rs.next()) {
						map = null;
						map = new HashMap<String, String>();
						ResultSetMetaData rsmd = rs.getMetaData();
						for (int j = 1; j <= rsmd.getColumnCount(); j++) {
							if (j == 1)
								caseid = rs.getString(j);
							map.put(rsmd.getColumnName(j), rs.getString(j));
						}
						rss.add(map);
						try {
							// System.out.println("**88888");
							str = runCase(ii);
							ii++;
							String sqls = "select imgName from caseoption where id=" +caseid;
							sqlu = "update caseoption set imgName='" + ImageTool.ScreenSnapshot(caseid)
									+ "', runState='" + str + "',actualRes='" + actualRes + "' where id=" + caseid;
							// System.out.println(sqlu);
							ResultSet rs1 = sm1.executeQuery(sqls);
							rs1.next();
							String imgName = rs1.getString(1);
							imgName = ImageTool.path + "/" + imgName;
							ImageTool.DelImageFromDisk(imgName);
							sm1.executeUpdate(sqlu);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					try {
						rs = null;
						sql = "select id from caseoption where casesId=" + check[i] + " and runState='FAIL';";
						rs = sm.executeQuery(sql);
						rs.next();
						try {
							System.out.println("len:" + check.length + "id:" + rs.getString(1));
							if (rs != null && rs.getString(1) != null)
								sqlu = "update casescene set runStates='FAIL' where casesId=" + check[i];
							else
								sqlu = "update casescene set runStates='PASS' where casesId=" + check[i];
						} catch (Exception e) {
							sqlu = "update casescene set runStates='PASS' where casesId=" + check[i];
						}
						sm.executeUpdate(sqlu);
						rss=null;
						driver.quit();
						driver = null;
						closeBrowser(Browser);
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

	public static void checkVerify(int index) {
		//System.out.println(index);
		String caseid = "";
		map = null;
		map = new HashMap<String, String>();
		try {
			caseid = rss.get(index).get("id");
			for (String it : rss.get(index).keySet()) {
				map.put(it,rss.get(index).get(it));
			}
			//System.out.println(rss.toString());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			// System.out.println("**88888");
			String str = runCase(index);
			String sqls = "select imgName from caseoption where id=" +caseid;
			String sqlu = "update caseoption set imgName='" + ImageTool.ScreenSnapshot(caseid)
					+ "', runState='" + str + "',actualRes='" + actualRes + "' where id=" + caseid;
			// System.out.println(sqlu);
			Statement sm1 = Mysql.ct.createStatement();;
			ResultSet rs1 = sm1.executeQuery(sqls);
			rs1.next();
			String imgName = rs1.getString(1);
			imgName = ImageTool.path + "/" + imgName;
			ImageTool.DelImageFromDisk(imgName);
			sm1.executeUpdate(sqlu);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String runCase(int index) {// 单用例执行方法
		runable = false;
		String s = "PASS";
		String res = "";
		String method = "";
		String url = "";
		String cmd_str;
		actualRes = "NULL";

		// 用于调试时，没有打开浏览器的情况
		if (driver == null && !map.get("optionss").equals("open")) {
			if (Browser.equals("") || Browser.equals("Chrome") || Browser.equals("chrome") || Browser == null) {
				ChromeDriver browser1 = new ChromeDriver(property.readRcErpURL("chromepath"),
						property.readRcErpURL("chromedriverpath"));
				driver = browser1.getdriver();
				driver.manage().window().maximize();
			} else if (Browser.equals("IE") || Browser.equals("ie") || Browser.equals("Ie")) {
				IEDriver browser2 = new IEDriver(property.readRcErpURL("IEpath"),
						property.readRcErpURL("IEdriverpath"));
				driver = browser2.getdriver();
				driver.manage().window().maximize();
			} else
				System.out.println("log--error:暂时不支持" + map.get("Browser") + "浏览器。");
		}

		getParameter();

		if (map.get("optionss").contains("(")) {// 处理关键字中带括号的情况
			method = map.get("optionss").substring(map.get("optionss").indexOf("(") + 1,
					map.get("optionss").indexOf(")"));
			map.put("optionss", map.get("optionss").substring(0, map.get("optionss").indexOf("(")));
			// System.out.println("method:" + method + " option:"+
			// map.get("optionss"));
		}

		switch (map.get("optionss")) { // UI操作方法和实现，可以在此添加自定义关键字

		case "open":
			driver = null;
			if (Browser.equals("") || Browser.equals("Chrome") || Browser.equals("chrome") || Browser == null) {
				System.out.println(path + "\\workspace1\\WebTester\\WebContent\\WEB-INF\\lib\\chromedriver.exe");
				c = new ChromeDriver(property.readRcErpURL("chromepath"), property.readRcErpURL("chromedriverpath"));
				driver = c.getdriver();
				driver.manage().window().maximize();
				cmd_str = "cmd /c start ";
				cmd_str += property.url + "sendKeys.vbs " + "F11";
				runCmd(cmd_str);
			} else if (Browser.equals("IE") || Browser.equals("ie") || Browser.equals("Ie")) {
				e = new IEDriver(property.readRcErpURL("IEpath"), property.readRcErpURL("IEdriverpath"));
				driver = e.getdriver();
				driver.manage().window().maximize();
				cmd_str = "cmd /c start ";
				cmd_str += property.url + "sendKeys.vbs " + "F11";
				runCmd(cmd_str);
			} else
				System.out.println("log--error:暂时不支持" + Browser + "浏览器。");
			break;

		case "get":
			try {
				url = map.get("xPath");
				if (!url.contains("http"))
					url = "http//:" + url;
				Thread.sleep(1000);
				driver.get(url);
				Thread.sleep(3000);
			} catch (Exception e) {
				// e.printStackTrace();
				s = "FAIL";
				actualRes = "打开网页失败！";
				System.out.println("log--warn:打开网页失败！网页需要http形式。");
			}
			break;

		case "click":
			try {
				driver.findElement(By.xpath(map.get("xPath").toString())).click();
				Thread.sleep(2000);
			} catch (Exception e) {
				s = "FAIL";
				actualRes = "元素点击失败！";
				System.out.println("log--error:元素" + map.get("xPath") + "点击失败！");
			}
			break;

		case "sendKeys":
			try {
				driver.findElement(By.xpath(map.get("xPath").toString())).clear();
				driver.findElement(By.xpath(map.get("xPath").toString())).sendKeys(map.get("datas"));
				Thread.sleep(300);
			} catch (Exception e) {
				s = "FAIL";
				actualRes = "元素赋值失败！";
				System.out.println("log--error元素" + map.get("xPath") + "赋值失败！");
			}
			break;

		case "select":
			try {
				Select select = new Select(driver.findElement(By.xpath(map.get("xPath").toString())));
				try {
					select.selectByValue(map.get("datas"));
				} catch (Exception e1) {
					select.selectByVisibleText(map.get("datas"));
				}
				Thread.sleep(500);
			} catch (Exception e) {
				s = "FAIL";
				actualRes = "元素查找或者选择失败！";
				System.out.println("log--error:元素查找或者选择失败！");
			}
			break;

		case "getText":
			try {
				Thread.sleep(3000);
				// System.out.println(driver.findElement(By.xpath(map.get("xPath").toString())));
				res = driver.findElement(By.xpath(map.get("xPath").toString())).getText().toString();
				Thread.sleep(100);
			} catch (Exception e) {
				s = "FAIL";
				actualRes = "元素获取失败！";
				System.out.println("log--error元素" + map.get("xPath") + "获取失败！");
			}
			break;

		case "getParameter":
			try {
				Thread.sleep(3000);
				System.out.println(driver.findElement(By.xpath(map.get("xPath").toString())));
				try {
					res = driver.findElement(By.xpath(map.get("xPath").toString())).getText().toString();
					if (res.length() > 0) {
						parameter = res;
						break;
					}
					Thread.sleep(100);
				} catch (Exception e) {

				}

				try {
					res = driver.findElement(By.xpath(map.get("xPath").toString()))
							.getAttribute(map.get("datas").toString()).toString();
					// System.out.println(res.length());
					if (res.length() > 1) {
						parameter = res;
						// System.out.println(res);
						break;
					}
					Thread.sleep(100);
				} catch (Exception e) {
					// System.out.println("2");
				}

				try {
					res = driver.findElement(By.xpath(map.get("xPath").toString()))
							.getCssValue(map.get("datas").toString()).toString();
					// System.out.println(res.length());
					// System.out.println("res" + res);
					if (res.length() > 1) {
						parameter = res;
						break;
					} else {
						s = "FAIL";
						actualRes = "属性获取失败！";
						System.out.println("log--error属性" + map.get("xPath") + "获取失败！");
					}
					Thread.sleep(100);
				} catch (Exception e) {
					s = "FAIL";
					actualRes = "属性获取失败！";
					System.out.println("log--error属性" + map.get("xPath") + "获取失败！");
				}

			} catch (Exception e) {
				s = "FAIL";
				actualRes = "元素获取失败！";
				System.out.println("log--error元素" + map.get("xPath") + "获取失败！");
			}
			break;

		case "setParameter":
			if (map.get("datas").equals("") || map.get("datas").equals("null") || map.get("datas").equals("NULL"))
				responseList.parameter.put(map.get("xPath"), parameter);
			else
				responseList.parameter.put(map.get("xPath"), map.get("datas"));
			break;

		case "getJson":
			if (method.equals("post")) {
				responseList.json = obj.sendPost(map.get("xPath"), map.get("datas"));
				try {
					Thread.sleep(2000);
					responseList.res = new ArrayList<Map<String, Object>>();
					json.Pase(responseList.json, 0, true);
					responseList.jsonObject = responseList.res.get(responseList.res.size() - 1);
				} catch (Exception e) {
					e.printStackTrace();
				}
				res = responseList.json;
				try {
					res = res.substring(0, 511);
				} catch (Exception e) {

				}

				break;

			} else if (method.equals("get")) {
				responseList.json = obj.sendGet(map.get("xPath"), map.get("datas"));
				responseList.res = new ArrayList<Map<String, Object>>();
				json.Pase(responseList.json, 0, true);
				try {
					responseList.jsonObject = responseList.res.get(responseList.res.size() - 1);
				} catch (Exception e) {
					e.printStackTrace();
				}
				res = responseList.json;
				try {
					res = res.substring(0, 511);
				} catch (Exception e) {

				}
				break;
			} else
				System.out.println("关键字getJson不支持" + method + "方法!");
			break;

		case "getVerify":
			try {
				Thread.sleep(2000);
				// System.out.println(driver.findElement(By.xpath(map.get("xPath").toString())));
				/*
				 * obj.setCookie(driver.manage().getCookies().toString());
				 * System.out.println(obj.getCookie());
				 * ImageTool.writeImageToDisk(obj.getImageFromNetByUrl(driver.
				 * findElement(By.xpath(map.get("xPath").toString())).
				 * getAttribute("src"), map.get("datas")),
				 * property.url+"verify.jpg"); cmd_str =
				 * "cmd /c start tesseract "; cmd_str += property.url +
				 * "verify.jpg " + property.url + "verify";
				 * System.out.println(cmd_str); runCmd(cmd_str);
				 */
				/*
				 * JavascriptExecutor jse = (JavascriptExecutor)driver; Object
				 * res1 = jse.executeScript(
				 * "var h=window.screen.availHeight;return h;"); Object res2 =
				 * jse.executeScript(
				 * "var h=document.body.offsetHeight;return h;");
				 * System.out.println(res1+","+res2); int h1=0,h2=0; try{ h1 =
				 * Integer.parseInt(res1.toString()); h2 =
				 * Integer.parseInt(res2.toString()); }catch (Exception e){
				 * System.out.println("log::error:获取屏幕位置错误。");
				 * e.printStackTrace(); }
				 */
				try{
					id = index - Integer.parseInt(map.get("datas"));
				}catch (Exception e){
					id = index;
				}
				Point l = driver.findElement(By.xpath(map.get("xPath").toString())).getLocation();
				Dimension size = driver.findElement(By.xpath(map.get("xPath").toString())).getSize();
				ImageTool.ScreenSnapshot(l.x, l.y, size.width, size.height, property.url + "verify.png");
				System.out.println(l.x + "," + l.y + "," + size.width + "," + size.height);
				System.out.println(property.url + "verify.png");
				cmd_str = "cmd /c start tesseract ";
				cmd_str += property.url + "verify.png " + property.url + "verify";
				System.out.println(cmd_str);
				runCmd(cmd_str);
				Thread.sleep(2000);
				verifyStr = readLine(property.url + "verify.txt");
				if(verifyStr==null || verifyStr.length()<4)
					verifyStr = "1111";
				//System.out.println(verifyStr);
				res = verifyStr;
			} catch (Exception e) {
				s = "FAIL";
				actualRes = "元素获取失败！";
				System.out.println("log--error元素" + map.get("xPath") + "获取失败！");
				e.printStackTrace();
			}
			break;

		case "setVerify":
			try {
				driver.findElement(By.xpath(map.get("xPath").toString())).clear();
				driver.findElement(By.xpath(map.get("xPath").toString())).sendKeys(verifyStr);
				Thread.sleep(300);
			} catch (Exception e) {
				s = "FAIL";
				actualRes = "元素赋值失败！";
				System.out.println("log--error元素" + map.get("xPath") + "赋值失败！");
			}
			break;
			
		case "checkVerify":
			try {
				Thread.sleep(2000);
				// System.out.println(driver.findElement(By.xpath(map.get("xPath").toString())));
				try{
					res = driver.findElement(By.xpath(map.get("xPath").toString())).getText().toString();
				}catch (Exception e1){
					res = "验证通过。";
				}
				System.out.println(res);
				if(res.contains(map.get("datas"))){
					for (int i=id;i<=index;i++)
						checkVerify(i);					
				}	
			} catch (Exception e) {
				s = "FAIL";
				actualRes = "元素获取失败！";
				System.out.println("log--error元素" + map.get("xPath") + "获取失败！");
			}
			break;

		default:
			s = "FAIL";
			actualRes = "暂时不支持该关键字操作";
			System.out.println("log--error:暂时不支持该关键字" + map.get("optionss") + "操作网页元素！");
		}

		switch (map.get("checkMethod")) { // UI校验方法和实现
		case "NULL":
			actualRes = res;
			break;
		case "null":
			actualRes = res;
			break;
		case "equel":
			if (res.equals(map.get("expectedRes").toString()))
				s = "PASS";
			else
				s = "FAIL";
			actualRes = res;
			break;
		case "contain":
			if (res.contains(map.get("expectedRes").toString()))
				s = "PASS";
			else
				s = "FAIL";
			actualRes = res;
			break;
		default:
			s = "FAIL";
			actualRes = res;
			System.out.println("log--error:暂时不支持该关键字：" + map.get("checkMethod") + "处理结果！");
		}

		runable = true;
		return s;
	}

	private static void getParameter() {// 获取系统参数和json参数
		int l = 0;
		int r = 0;
		String datas = "";
		String str = map.get("datas");
		int flag = 0;
		try {
			while (str != null && str.length() > 0) {
				flag = 0;
				if (str.contains("{") || str.contains("[")) {
					if (str.indexOf("[") > 0 && str.indexOf("{") > 0) {
						if (str.indexOf("{") > str.indexOf("[")) {
							l = str.indexOf("[");
							r = str.indexOf("]") + 1;
							flag = 1;
						} else {
							l = str.indexOf("{");
							r = str.indexOf("}") + 1;
							flag = 2;
						}
					} else {
						if (str.indexOf("[") > 0) {
							l = str.indexOf("[");
							r = str.indexOf("]") + 1;
							flag = 1;
						} else {
							l = str.indexOf("{");
							r = str.indexOf("}") + 1;
							flag = 2;
						}
					}

					if (r > 0) {
						String str1 = str.substring(l + 1, r - 1);
						// System.out.println("???"+str);
						if (flag == 1)
							datas += str.substring(0, l) + responseList.jsonObject.get(str1);
						else if (flag == 2)
							datas += str.substring(0, l) + responseList.parameter.get(str1);
						str = str.substring(r, str.length());
					} else {
						System.out.println("log：参数引用格式错误！！");
						break;
					}

				} else
					break;
			}

			if (datas.length() > 0) {
				datas += str;
				map.put("datas", datas);
			}
			// System.out.println(map.get("datas"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void closeBrowser(String B) {

		if (B.contains("IE") || B.contains("ie"))
			B = "IEDriver.exe";
		else if (B.contains("Chrome") || B.contains("chrome"))
			B = "chromedriver.exe";
		else {
			System.out.println("log::info:没有" + Browser + "的浏览器driver！");
			return;
		}
		String cmd_str = "cmd /c start ";
		cmd_str += property.url;
		cmd_str += "process.vbs " + B;
		// System.out.println(cmd_str);
		runCmd(cmd_str);
	}

	public static void runCmd(String cmd) {
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec(cmd);
		} catch (Exception e) {
			System.out.println("Error!");
		}
	}

	@SuppressWarnings("resource")
	public static String readLine(String Path) {
		File file = new File(Path);
		String encoding = "UTF-8";
		if (file.isFile() && file.exists()) { // 判断文件是否存在
			try {
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				return bufferedReader.readLine();
			} catch (Exception e) {
				System.out.println("文件读取错误！");
				e.printStackTrace();
			}
		} else {
			System.out.println("找不到指定的文件");
		}
		return null;
	}
}
