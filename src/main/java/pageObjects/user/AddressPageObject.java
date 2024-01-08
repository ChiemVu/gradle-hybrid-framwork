package pageObjects.user;

import org.openqa.selenium.WebDriver;

public class AddressPageObject extends MyAccountSideBarPageObject {
	WebDriver driver;

	public AddressPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
}
