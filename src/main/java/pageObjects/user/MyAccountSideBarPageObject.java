package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BaseElement;
import commons.PageGeneratorManager;
import pageUIs.user.MyAccountSideBarPageUI;

public class MyAccountSideBarPageObject extends BaseElement {
	WebDriver driver;

	public MyAccountSideBarPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public AddressPageObject openAddressPage() {
		waitForElementClickable(driver, MyAccountSideBarPageUI.ADDRESS_LINK_TEXT);
		clickToElement(driver, MyAccountSideBarPageUI.ADDRESS_LINK_TEXT);
		return PageGeneratorManager.getAddressPage(driver);
	}

	public OrderPageObject openOrderPage() {
		waitForElementClickable(driver, MyAccountSideBarPageUI.ORDER_LINK_TEXT);
		clickToElement(driver, MyAccountSideBarPageUI.ORDER_LINK_TEXT);
		return PageGeneratorManager.getOrderPage(driver);
	}

	public RewardPointPageObject openRewardPointPage() {
		waitForElementClickable(driver, MyAccountSideBarPageUI.REWARD_POINT_LINK_TEXT);
		clickToElement(driver, MyAccountSideBarPageUI.REWARD_POINT_LINK_TEXT);
		return PageGeneratorManager.getRewardPointPage(driver);
	}

	public CustomerPageObject openCustomerPage() {
		waitForElementClickable(driver, MyAccountSideBarPageUI.CUSTOMER_INFO_LINK_TEXT);
		clickToElement(driver, MyAccountSideBarPageUI.CUSTOMER_INFO_LINK_TEXT);
		return PageGeneratorManager.getCustomerPage(driver);
	}

	public MyAccountSideBarPageObject openDynamicSideBarPage(String pageName) {
		waitForElementClickable(driver, MyAccountSideBarPageUI.DYNAMIC_SIDEBAR_LINK_TEXT, pageName);
		clickToElement(driver, MyAccountSideBarPageUI.DYNAMIC_SIDEBAR_LINK_TEXT, pageName);

		switch (pageName) {
		case "Customer info":
			return PageGeneratorManager.getCustomerPage(driver);
		case "Addresses":
			return PageGeneratorManager.getAddressPage(driver);
		case "Orders":
			return PageGeneratorManager.getOrderPage(driver);
		case "Reward points":
			return PageGeneratorManager.getRewardPointPage(driver);
		default:
			new RuntimeException("Sidebar page name incorrect.");
			return null;
		}
	}

	public void openDynamicSideBarPageByName(String pageName) {
		waitForElementClickable(driver, MyAccountSideBarPageUI.DYNAMIC_SIDEBAR_LINK_TEXT, pageName);
		clickToElement(driver, MyAccountSideBarPageUI.DYNAMIC_SIDEBAR_LINK_TEXT, pageName);
	}

}
