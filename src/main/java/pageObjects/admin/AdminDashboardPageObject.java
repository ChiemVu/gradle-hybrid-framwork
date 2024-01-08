package pageObjects.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.admin.AdminDashboardPageUI;

public class AdminDashboardPageObject extends BasePage {
	WebDriver driver;

	public AdminDashboardPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public AdminLoginPageObject clickToLogoutLink() {
		waitForElementClickable(driver, AdminDashboardPageUI.LOGOUT_LINK);
		clickToElement(driver, AdminDashboardPageUI.LOGOUT_LINK);
		return PageGeneratorManager.getAdminLoginPage(driver);
	}

}
