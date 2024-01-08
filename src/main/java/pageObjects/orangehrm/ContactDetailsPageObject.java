package pageObjects.orangehrm;

import org.openqa.selenium.WebDriver;

public class ContactDetailsPageObject extends BaseActions {
	WebDriver driver;

	public ContactDetailsPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

}
