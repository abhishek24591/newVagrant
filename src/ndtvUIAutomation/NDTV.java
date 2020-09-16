package ndtvUIAutomation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import util.Utility;

public class NDTV {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.gecko.driver", "F://Gecko//geckodriver//geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		
		String config_file = System.getProperty("user.dir") + "//src//config//config.properties";
		String xpathfile = System.getProperty("user.dir") + "//src//config//object_repo.properties";
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		
		driver.get(Utility.getProperty(config_file,"url"));
		
	    Utility.clickUsingXpath(driver, xpathfile,"signin_btn_xpath");
		
		Utility.typeUsingXpath(driver, xpathfile,"username_textbox_xpath", config_file, "login_username");
		
		Utility.typeUsingXpath(driver, xpathfile, "pwd_textbox_xpath", config_file, "login_password");
		
		Utility.clickUsingXpath(driver, xpathfile, "go_btn_xpath");
		
		
		
		
		
		

	}

}
