package com.test.tools;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ChromeDriver { // Chrome浏览器驱动类
	private WebDriver driver = null;

	public ChromeDriver(String propath, String driverpath) {
		// 设置 chrome 的路径
		System.setProperty("webdriver.chrome.driver", propath);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		ChromeOptions option = new ChromeOptions();
		// <span style="color:#ff0000;">去除Chrome浏览器上的黄色警告</span>
		option.addArguments("-test-type");
		// 最大化浏览器窗口
		option.addArguments("-start-maximized");

		// 创建一个 ChromeDriver 的接口，用于连接 Chrome

		capabilities.setCapability(ChromeOptions.CAPABILITY, option);
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
		capabilities.setCapability(CapabilityType.VERSION, "");
		capabilities.setCapability(CapabilityType.PLATFORM, "WINDOWS");
		ChromeDriverService service = null;
		try {
			service = new ChromeDriverService.Builder().usingDriverExecutable(new File(driverpath)).usingAnyFreePort()
					.build();
			service.start();
			// service.stop();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("log--error：service启动错误！");
		}

		try { // 创建一个 Chrome 的浏览器实例
			this.driver = new RemoteWebDriver(service.getUrl(), capabilities);
			// 让浏览器访问空白页
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