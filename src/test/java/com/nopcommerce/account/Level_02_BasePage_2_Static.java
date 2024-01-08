package com.nopcommerce.account;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;

public class Level_02_BasePage_2_Static {
	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	private BasePage basePage = BasePage.getBasePage();

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void Register_01_Empty_Data() {
		basePage.openPageUrl(driver, "https://demo.nopcommerce.com/");

		basePage.clickToElement(driver, "//a[@class='ico-register']");
		basePage.clickToElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
	}

	@Test
	public void Register_02_Invalid_Email() {
		basePage.openPageUrl(driver, "https://demo.nopcommerce.com/");
		basePage.clickToElement(driver, "//a[@class='ico-register']");

		basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "John");
		basePage.sendkeyToElement(driver, "//input[@id='LastName']", "Kennedy");
		basePage.sendkeyToElement(driver, "//input[@id='Email']", "john@kennedy@us");
		basePage.sendkeyToElement(driver, "//input[@id='Password']", "123456");
		basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");

		basePage.clickToElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
	}

	@Test
	public void Register_03_Invalid_Password() {
		basePage.openPageUrl(driver, "https://demo.nopcommerce.com/");
		basePage.clickToElement(driver, "//a[@class='ico-register']");

		basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "John");
		basePage.sendkeyToElement(driver, "//input[@id='LastName']", "Kennedy");
		basePage.sendkeyToElement(driver, "//input[@id='Email']", "john@kennedy@us");
		basePage.sendkeyToElement(driver, "//input[@id='Password']", "123");
		basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123");

		basePage.clickToElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Password-error']"), "Password must meet the following rules:\nmust have at least 6 characters");
	}

	@Test
	public void Register_04_Incorrect_Confirm_Password() {
		basePage.openPageUrl(driver, "https://demo.nopcommerce.com/");
		basePage.clickToElement(driver, "//a[@class='ico-register']");

		basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "John");
		basePage.sendkeyToElement(driver, "//input[@id='LastName']", "Kennedy");
		basePage.sendkeyToElement(driver, "//input[@id='Email']", "john@kennedy@us");
		basePage.sendkeyToElement(driver, "//input[@id='Password']", "123456");
		basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "654321");

		basePage.clickToElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
	}

	@Test
	public void Register_05_Success() {
		basePage.openPageUrl(driver, "https://demo.nopcommerce.com/");
		basePage.clickToElement(driver, "//a[@class='ico-register']");

		basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "John");
		basePage.sendkeyToElement(driver, "//input[@id='LastName']", "Kennedy");
		basePage.sendkeyToElement(driver, "//input[@id='Email']", getEmailRandom());
		basePage.sendkeyToElement(driver, "//input[@id='Password']", "123456");
		basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");

		basePage.clickToElement(driver, "//button[@id='register-button']");
		
		Assert.assertEquals(basePage.getElementText(driver, "//div[@class='result']"), "Your registration completed");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public String getEmailRandom() {
		Random rand = new Random();
		return "john" + rand.nextInt(99999) + "@kennedy.us";
	}

}
