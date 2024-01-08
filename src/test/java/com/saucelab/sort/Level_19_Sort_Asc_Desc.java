package com.saucelab.sort;

import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.BaseTest;
import commons.GlobalConstants;
import pageObjects.saucelab.PageGeneratorManager;
import pageObjects.saucelab.loginPageObject;
import pageObjects.saucelab.productPageObject;
import reportConfig.ExtentTestManager;

public class Level_19_Sort_Asc_Desc extends BaseTest {

	@Parameters({ "browser", "appUrl" })
	@BeforeClass
	public void beforeClass(String browserName, String saucelabUrl) {
		driver = getBrowserDriver(browserName, saucelabUrl);
		loginPage = PageGeneratorManager.getLoginPage(driver);

		loginPage.enterToUsernameTextbox(GlobalConstants.SAUCELAB_USERNAME);
		loginPage.enterToPasswordTextbox(GlobalConstants.SAUCELAB_PASSWORD);
		productPage = loginPage.clickToLoginButton();
	}

	@Test
	public void Sort_01_Name(Method method) {
		ExtentTestManager.startTest(method.getName(), "Sort_01_Name"); //start TC
		ExtentTestManager.getTest().log(Status.INFO, "Sort Name - Step 01: Ascending Name (A to Z)"); //add log
		productPage.selectItemInProductSortDropdown("Name (A to Z)");
		productPage.sleepInSecond(3);

		assertTrue(productPage.isProductNameSortByAscending());


		ExtentTestManager.getTest().log(Status.INFO, "Sort Name - Step 01: Descending Name (Z to A)");
		productPage.selectItemInProductSortDropdown("Name (Z to A)");

		assertTrue(productPage.isProductNameSortByDescending());
	}

	@Test
	public void Sort_02_Price(Method method) {
		ExtentTestManager.startTest(method.getName(), "Sort_02_Price"); //start TC
		ExtentTestManager.getTest().log(Status.INFO, "Sort Price - Step 01: Ascending Price (low to high)"); //add log
		productPage.selectItemInProductSortDropdown("Price (low to high)");

		assertTrue(productPage.isProductPriceSortByAscending());

		ExtentTestManager.getTest().log(Status.INFO, "Sort Price - Step 01: Descending Price (high to low)");
		productPage.selectItemInProductSortDropdown("Price (high to low)");

		assertTrue(productPage.isProductPriceSortByDescending());

	}

	@Test
	public void User_03_My_Account() {

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowser();
	}

	private WebDriver driver;
	private loginPageObject loginPage;
	private productPageObject productPage;
}