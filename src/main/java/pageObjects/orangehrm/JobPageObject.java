package pageObjects.orangehrm;

import org.openqa.selenium.WebDriver;

public class JobPageObject extends BaseActions {
	WebDriver driver;

	public JobPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

}
