package ndtvUIAutomation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.bytebuddy.utility.privilege.GetSystemPropertyAction;
import util.Utility;

public class NDTV {
	
	public static WebDriver driver = null;
	public static String xpathfile,config_file;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.gecko.driver", "F://Gecko//geckodriver//geckodriver.exe");
		driver = new FirefoxDriver();
		
		config_file = System.getProperty("user.dir") + "//src//config//config.properties";
		xpathfile = System.getProperty("user.dir") + "//src//config//object_repo.properties";
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		
		driver.get(Utility.getProperty(config_file,"url"));
		
	    Utility.clickUsingXpath(driver, xpathfile,"three_dots_on_UI");
	    
	    waitForVisibilityOfElementLocated("weather_button_xpath");
	    
	    Utility.clickUsingXpath(driver, xpathfile,"weather_button_xpath");
		
		Utility.typeUsingXpath(driver, xpathfile,"username_textbox_xpath", config_file, "login_username");
		
		Utility.typeUsingXpath(driver, xpathfile, "pwd_textbox_xpath", config_file, "login_password");
		
		Utility.clickUsingXpath(driver, xpathfile, "go_btn_xpath");
		

	}
	
	public static void waitForVisibilityOfElementLocated(String key) throws Exception{
		WebDriverWait w = new WebDriverWait(driver, 10);
	    w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperty(xpathfile,key))));
	}

}
