package com.jquery.upload;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jquery.PageGeneratorManager;
import pageObjects.jquery.UploadPageObject;

public class Level_14_Upload_File extends BaseTest {
	WebDriver driver;
	UploadPageObject uploadPage;

	String dalatCity = "Da Lat.jpg";
	String hagiangCity = "Ha Giang.jpg";
	String haiphongCity = "Hai Phong.jpg";

	String[] fileNames = { dalatCity, hagiangCity, haiphongCity };

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
		uploadPage = PageGeneratorManager.getUploadPage(driver);
	}

	public void TC_01_Upload_Single_File() {
		uploadPage.uploadMultipleFiles(driver, dalatCity);
		uploadPage.sleepInSecond(2);

		uploadPage.uploadMultipleFiles(driver, hagiangCity);
		uploadPage.sleepInSecond(2);

		uploadPage.uploadMultipleFiles(driver, haiphongCity);
		uploadPage.sleepInSecond(2);

		Assert.assertTrue(uploadPage.isFileLoadedSuccess(dalatCity));
		Assert.assertTrue(uploadPage.isFileLoadedSuccess(hagiangCity));
		Assert.assertTrue(uploadPage.isFileLoadedSuccess(haiphongCity));

		uploadPage.clickStartButtonOnEachFile();

		Assert.assertTrue(uploadPage.isFileUploadedSuccess(dalatCity));
		Assert.assertTrue(uploadPage.isFileUploadedSuccess(hagiangCity));
		Assert.assertTrue(uploadPage.isFileUploadedSuccess(haiphongCity));
	}

	public void TC_02_Upload_Multiple_File() {
		uploadPage.refreshCurrentPage(driver);

		uploadPage.uploadMultipleFiles(driver, fileNames);

		Assert.assertTrue(uploadPage.isFileLoadedSuccess(dalatCity));
		Assert.assertTrue(uploadPage.isFileLoadedSuccess(hagiangCity));
		Assert.assertTrue(uploadPage.isFileLoadedSuccess(haiphongCity));

		uploadPage.clickStartButtonOnEachFile();

		Assert.assertTrue(uploadPage.isFileUploadedSuccess(dalatCity));
		Assert.assertTrue(uploadPage.isFileUploadedSuccess(hagiangCity));
		Assert.assertTrue(uploadPage.isFileUploadedSuccess(haiphongCity));
	}

	@Test
	public void TC_03_Upload_GoFile() {
		uploadPage.openPageUrl(driver, "https://gofile.io/uploadFiles");

		Assert.assertTrue(uploadPage.isLoadingIconAtMainContentDisappear());

		uploadPage.uploadMultipleFiles(driver, fileNames);

		Assert.assertTrue(uploadPage.isLoadingIconAtMainUploadDisappear());

		Assert.assertTrue(uploadPage.isMultipleProgressBarIconDisappear());

		Assert.assertTrue(uploadPage.isSuccessMessageDisplayed("Your files have been successfully uploaded"));

		uploadPage.clickToSuccessLink();

		Assert.assertTrue(uploadPage.isContentTableDisplayed());

		Assert.assertTrue(uploadPage.isDownloadButtonDisplayed(dalatCity));
		Assert.assertTrue(uploadPage.isDownloadButtonDisplayed(hagiangCity));
		Assert.assertTrue(uploadPage.isDownloadButtonDisplayed(haiphongCity));

		Assert.assertTrue(uploadPage.isPlayButtonDisplayed(dalatCity));
		Assert.assertTrue(uploadPage.isPlayButtonDisplayed(hagiangCity));
		Assert.assertTrue(uploadPage.isPlayButtonDisplayed(haiphongCity));
	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
