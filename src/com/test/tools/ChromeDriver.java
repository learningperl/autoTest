package com.test.tools;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class ChromeDriver {		//Chrome浏览器驱动类
	private WebDriver driver=null;
	public ChromeDriver(String propath, String driverpath){
	        // 设置 chrome 的路径  
	        System.setProperty("webdriver.chrome.driver",propath);  
	        // 创建一个 ChromeDriver 的接口，用于连接 Chrome  
	        ChromeDriverService service = null;
	        try {
	        	service = new ChromeDriverService.Builder().usingDriverExecutable(new File(driverpath)).usingAnyFreePort().build();
				service.start();
				//service.stop();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("log--error：service启动错误！");
			}
 
	        try {
		        // 创建一个 Chrome 的浏览器实例 
	        	this.driver = new RemoteWebDriver(service.getUrl(),DesiredCapabilities.chrome());  
	        	// 让浏览器访问空白页
	        	driver.get("about:blank");
	        } catch (Exception e) {
	        	e.printStackTrace();
	        	System.out.println("log--error：创建driver失败！！");
	        }
	}
	public WebDriver getdriver(){
		return this.driver;
	}
}