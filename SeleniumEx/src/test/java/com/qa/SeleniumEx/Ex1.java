package com.qa.SeleniumEx;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Ex1 {
	WebDriver driver;
	String input = "opensesame";
	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver",
	    		"C:\\Users\\Admin\\Desktop\\chromedriver_win32/chromedriver.exe");
	    driver = new ChromeDriver();
	    driver.manage().window().maximize();

	}
	@Test
	public void test1() {
		driver.get("http://thedemosite.co.uk/addauser.php");
		WebElement wEUserSet = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[1]/td[2]/p/input"));
		wEUserSet.sendKeys(input);
		WebElement wEPassSet = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[2]/td[2]/p/input"));
		wEPassSet.sendKeys(input);
		WebElement wESave = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[3]/td[2]/p/input"));
		wESave.click();
		
        driver.get("http://thedemosite.co.uk/login.php");
		WebElement wEUser = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[1]/td[2]/p/input"));
		wEUser.sendKeys(input);
		WebElement wEPass = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/p/input"));
		wEPass.sendKeys(input);
		WebElement wESubmit = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[3]/td[2]/p/input"));
		wESubmit.click();
		
		WebElement wELogin = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b"));
		assertEquals("**Successful Login**", wELogin.getText());
	}
	@After
	public void tearDown() {
		driver.quit();
	}
}