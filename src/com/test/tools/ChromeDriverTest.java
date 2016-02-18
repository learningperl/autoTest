package com.test.tools;

import java.io.File;
import junit.framework.TestCase;

import org.openqa.selenium.WebDriver;

public class ChromeDriverTest extends TestCase {	//单元测试

	public String cpCmd1;
	public String cpCmd2;
	private ChromeDriver d;
	private WebDriver w;

	public ChromeDriverTest() {
	}

	public void setUp() throws Exception {
		String path;
		try {
			File directory = new File("");
			path = directory.getCanonicalPath();
		} catch (Exception e) {
			path = "";
		}
		cpCmd1 = "wscript " + path
				+ "\\WebContent\\WEB-INF\\lib\\Selenium\\process.vbs "
				+ "\"chromedriver.exe\"";
		cpCmd2 = "wscript " + path
				+ "\\WebContent\\WEB-INF\\lib\\Selenium\\process.vbs "
				+ "\"chrome.exe\"";
	}

	public void tearDown() throws Exception {
		Runtime.getRuntime().exec(cpCmd1);
		Runtime.getRuntime().exec(cpCmd2);
	}

	public WebDriver testgetdriver(String pro, String driver) {
		w = null;
		System.out.println("初始化driver。");
		d = new ChromeDriver(pro, driver);
		w = d.getdriver();
		if(w.equals(""))
			System.out.println("null");
		return w;
	}

	// 测试driver目录错误
	public void test1() {
		System.out.println("test1");
		String pro = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
		String driver = "E:\\workspace\\Static\\Selenium\\chromedriver.exe";
		assertNull(testgetdriver(pro, driver));
	}

	// 测试浏览器目录错误
	public void test2() {
		System.out.println("test2");
		try {
			w.quit();
			Runtime.getRuntime().exec(cpCmd2);
		} catch (Exception e) {
			
			// e.printStackTrace();
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		String pro = "C:\\Program Files\\Google\\Chrome\\Application\\IE.exe";
		String driver = "D:\\eclipse_4.5.0\\workspace\\Static\\Selenium\\chromedriver.exe";
		assertNotNull(testgetdriver(pro, driver));
	}

	// 测试driver目录无效
	public void test3() {
		System.out.println("test3");
		try {
			w.quit();
			Runtime.getRuntime().exec(cpCmd2);
		} catch (Exception e) {
			
			// e.printStackTrace();
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		String pro = "C:\\Program Files\\Google\\Chrome\\Application\\IE.exe";
		String driver = "";
		assertNull(testgetdriver(pro, driver));
	}

	// 测试浏览器目录无效
	public void test4() {
		System.out.println("test4");
		try {
			w.quit();
			Runtime.getRuntime().exec(cpCmd2);
		} catch (Exception e) {
			
			// e.printStackTrace();
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		String pro = "";
		String driver = "D:\\eclipse_4.5.0\\workspace\\Static\\Selenium\\chromedriver.exe";
		assertNotNull(testgetdriver(pro, driver));
	}

	// 测试浏览器driver目录无效
	public void test5() {
		System.out.println("test5");
		try {
			w.quit();
			Runtime.getRuntime().exec(cpCmd2);
		} catch (Exception e) {
			
			// e.printStackTrace();
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		String pro = "";
		String driver = "";
		assertNull(testgetdriver(pro, driver));
	}

	// 测试正常
	public void test6() {
		System.out.println("test6");
		try {
			w.quit();
			Runtime.getRuntime().exec(cpCmd2);
		} catch (Exception e) {
			
			// e.printStackTrace();
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		String pro = "C:\\Program Files\\Google\\Chrome\\Application\\Chrome.exe";
		String driver = "D:\\eclipse_4.5.0\\workspace\\Static\\Selenium\\chromedriver.exe";
		assertNotNull(testgetdriver(pro, driver));
	}

	// 测试浏览器路径不合法
	public void test7() {
		System.out.println("test7");
		try {
			w.quit();
			Runtime.getRuntime().exec(cpCmd2);
		} catch (Exception e) {
			
			// e.printStackTrace();
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		String pro = "es\\Google\\Chrome\\Application\\Chrome.exe";
		String driver = "D:\\eclipse_4.5.0\\workspace\\Static\\Selenium\\chromedriver.exe";
		assertNotNull(testgetdriver(pro, driver));
	}

	// 测试浏览器路径不合法
	public void test8() {
		System.out.println("test8");
		try {
			w.quit();
			Runtime.getRuntime().exec(cpCmd2);
		} catch (Exception e) {
			
			// e.printStackTrace();
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		String pro = "C:\\Program Files\\Google\\Chrome\\Application\\Chrome.exe";
		String driver = "www.baidu.com";
		assertNull(testgetdriver(pro, driver));
	}
}
