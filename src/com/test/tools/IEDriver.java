package com.test.tools;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IEDriver {			//IE浏览器驱动类
	public WebDriver driver;

	public IEDriver(String propath, String driverpath) {
		// 设置 IE 的路径
		System.setProperty("webdriver.ie.driver", propath);
		// 创建一个 IEDriver 的接口，用于连接 IE
		// @SuppressWarnings("deprecation")
		InternetExplorerDriverService service = null;
		try {
			service = new InternetExplorerDriverService.Builder()
					.usingDriverExecutable(new File(driverpath))
					.usingAnyFreePort().build();
			service.start();
			// service.stop();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("log--error：service启动错误！");
		}

		try {
			// 创建一个 IE 的浏览器实例
			this.driver = new RemoteWebDriver(service.getUrl(),
					DesiredCapabilities.internetExplorer());
			// 让浏览器访问 云猴
			driver.get("about:blank");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("log--error：创建driver失败！！");
		}
	}

	public WebDriver getdriver() {
		return this.driver;
	}
}