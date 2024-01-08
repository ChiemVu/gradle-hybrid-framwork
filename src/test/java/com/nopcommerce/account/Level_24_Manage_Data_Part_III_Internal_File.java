package com.nopcommerce.account;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.nopcommerce.data.UserData;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.user.HomePageObject;
import pageObjects.user.RegisterPageObject;
import reportConfig.ExtentTestManager;

public class Level_24_Manage_Data_Part_III_Internal_File extends BaseTest {
	private WebDriver driver;
	private String browserName;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private String emailAddress;
	private String firstName, lastName, password;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		this.browserName = browserName;
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);

		registerPage = homePage.clickToRegisterLink();
		
		firstName = UserData.Register.FIRST_NAME;
		lastName = UserData.Register.LAST_NAME;
		emailAddress = UserData.Register.EMAIL_ADDRESS + getRandomNumber() + "@gmail.com";
		password = UserData.Register.PASSWORD;
	}

	@Test
	public void User_02_Register_Success(Method method) {
		ExtentTestManager.startTest(method.getName() + " - Run on " + browserName.toUpperCase(), "User_02_Register_Success");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 01: Enter to First Name textbox with value is " + firstName);
		registerPage = homePage.clickToRegisterLink();
		registerPage.enterToFirstNameTextbox(firstName);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 02: Enter to Last Name textbox with value is " + lastName);
		registerPage.enterToLastNameTextbox(lastName);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 03: Enter to Email Address textbox with value is " + emailAddress);
		registerPage.enterToEmailTextbox(emailAddress);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 04: Enter to Password textbox with value is " + password);
		registerPage.enterToPasswordTextbox(password);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 05: Enter to Confirm Password textbox with value is " + password);
		registerPage.enterToConfirmPasswordTextbox(password);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 06: Click to Register button");
		registerPage.clickToRegisterButton();

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 07: Verify success message is displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessageText(), "Your registration completed");
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowser();
	}
	
}