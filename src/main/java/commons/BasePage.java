package commons;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageUIs.user.BaseElementUI;

public class BasePage {

	public static BasePage getBasePage() {
		return new BasePage();
	}

	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	protected String getCurrentPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public Alert waitForAlertPresence(WebDriver driver) {
		return new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.alertIsPresent());
	}

	public void acceptToAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}

	public void cancelToAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	public String getTextInAlert(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	public void sendkeyToAlert(WebDriver driver, String keysToSend) {
		waitForAlertPresence(driver).sendKeys(keysToSend);
	}

	public void switchToWindowByID(WebDriver driver, String pageID) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id : allIDs) {
			if (!id.equals(pageID)) {
				driver.switchTo().window(id);
				sleepInSecond(1);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String pageTitle) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id : allIDs) {
			driver.switchTo().window(id);
			sleepInSecond(1);
			String actualPageTitle = driver.getTitle();
			if (actualPageTitle.equals(pageTitle)) {
				break;
			}
		}
	}

	public void closeAllWindowWithoutParentID(WebDriver driver, String parentID) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id : allIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Set<Cookie> getBrowserCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}

	public void setCookies(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			System.out.println(cookie);
			driver.manage().addCookie(cookie);
		}
	}

	public void deleteAllCookies(WebDriver driver) {
		driver.manage().deleteAllCookies();
	}

	public By getByLocator(String locatorValue) {
		By by = null;

		if (locatorValue.startsWith("xpath=") || locatorValue.startsWith("XPath=") || locatorValue.startsWith("XPATH=") || locatorValue.startsWith("Xpath=")) {
			by = By.xpath(locatorValue.substring(6));
		} else if (locatorValue.startsWith("css=") || locatorValue.startsWith("Css=") || locatorValue.startsWith("CSS=")) {
			by = By.cssSelector(locatorValue.substring(4));
		} else if (locatorValue.startsWith("id=") || locatorValue.startsWith("Id=") || locatorValue.startsWith("ID=")) {
			by = By.id(locatorValue.substring(3));
		} else if (locatorValue.startsWith("name=") || locatorValue.startsWith("Name=") || locatorValue.startsWith("NAME=")) {
			by = By.name(locatorValue.substring(5));
		} else if (locatorValue.startsWith("class=") || locatorValue.startsWith("Class=") || locatorValue.startsWith("CLASS=")) {
			by = By.className(locatorValue.substring(6));
		} else if (locatorValue.startsWith("tagname=") || locatorValue.startsWith("Tagname=") || locatorValue.startsWith("TAGNAME=")) {
			by = By.tagName(locatorValue.substring(8));
		} else {
			throw new RuntimeException("Locator type is not valid.");
		}
		return by;
	}

	public By getByXpath(String locator) {
		return By.xpath(locator);
	}

	public String getDynamicLocator(String locator, String... restParams) {
		return String.format(locator, (Object[]) restParams);
	}

	public WebElement getWebElement(WebDriver driver, String locator) {
		return driver.findElement(getByLocator(locator));
	}

	public List<WebElement> getListWebElement(WebDriver driver, String locator) {
		return driver.findElements(getByLocator(locator));
	}

	public List<WebElement> getListWebElement(WebDriver driver, String locator, String... restParams) {
		return driver.findElements(getByLocator(getDynamicLocator(locator, restParams)));
	}

	public void clickToElement(WebDriver driver, String locator) {
		getWebElement(driver, locator).click();
	}

	public void clickToElement(WebDriver driver, WebElement element) {
		element.click();
	}

	public void clickToElement(WebDriver driver, String locator, String... restParams) {
		getWebElement(driver, getDynamicLocator(locator, restParams)).click();
	}

	public void sendkeyToElement(WebDriver driver, String locator, String valueToSend) {
		getWebElement(driver, locator).clear();
		getWebElement(driver, locator).sendKeys(valueToSend);
	}

	public void sendkeyToElement(WebDriver driver, String locator, String valueToSend, String... restParams) {
		getWebElement(driver, getDynamicLocator(locator, restParams)).clear();
		getWebElement(driver, getDynamicLocator(locator, restParams)).sendKeys(valueToSend);
	}

	public void selectItemInDefaultDropdown(WebDriver driver, String locator, String itemValue) {
		new Select(getWebElement(driver, locator)).selectByVisibleText(itemValue);
	}

	public void selectItemInDefaultDropdown(WebDriver driver, String locator, String itemValue, String... restParams) {
		new Select(getWebElement(driver, getDynamicLocator(locator, restParams))).selectByVisibleText(itemValue);
	}

	public String getFirstSelectedTextInDefaultDropdown(WebDriver driver, String locator) {
		return new Select(getWebElement(driver, locator)).getFirstSelectedOption().getText();
	}

	public boolean isDefaultDropdownMultiple(WebDriver driver, String locator) {
		return new Select(getWebElement(driver, locator)).isMultiple();
	}

	public void selectItemInDropdown(WebDriver driver, String parentLocator, String childLocator, String expectedTextItem) {
		getWebElement(driver, parentLocator).click();
		sleepInSecond(1);

		List<WebElement> speedDropdownItems = new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocator)));
		for (WebElement tempItem : speedDropdownItems) {
			if (tempItem.getText().trim().equals(expectedTextItem)) {
				sleepInSecond(1);
				tempItem.click();
				break;
			}
		}
	}

	public String getElementText(WebDriver driver, String locator) {
		return getWebElement(driver, locator).getText();
	}

	public String getElementText(WebDriver driver, String locator, String... restParams) {
		return getWebElement(driver, getDynamicLocator(locator, restParams)).getText();
	}

	public String getElementAttribute(WebDriver driver, String locator, String attributeName) {
		return getWebElement(driver, locator).getAttribute(attributeName);
	}

	public String getElementAttribute(WebDriver driver, String locator, String attributeName, String... restParams) {
		return getWebElement(driver, getDynamicLocator(locator, restParams)).getAttribute(attributeName);
	}

	public String getElementCssValue(WebDriver driver, String locator, String cssPropertyName) {
		return getWebElement(driver, locator).getCssValue(cssPropertyName);
	}

	public String convertRGBAToHexaColor(WebDriver driver, String locator) {
		return Color.fromString(getElementCssValue(driver, locator, "background-color")).asHex();
	}

	public int getListElementSize(WebDriver driver, String locator) {
		return getListWebElement(driver, locator).size();
	}

	public int getListElementSize(WebDriver driver, String locator, String... restParams) {
		return getListWebElement(driver, getDynamicLocator(locator, restParams)).size();
	}

	// Default Checkbox/ Radio
	public void checkToElement(WebDriver driver, String locator) {
		if (!getWebElement(driver, locator).isSelected()) {
			getWebElement(driver, locator).click();
		}
	}

	// Default Checkbox/ Radio
	public void checkToElement(WebDriver driver, String locator, String... restParams) {
		if (!getWebElement(driver, getDynamicLocator(locator, restParams)).isSelected()) {
			getWebElement(driver, getDynamicLocator(locator, restParams)).click();
		}
	}

	// Default Checkbox
	public void uncheckToElement(WebDriver driver, String locator) {
		if (getWebElement(driver, locator).isSelected()) {
			getWebElement(driver, locator).click();
		}
	}

	// Case 01: Element hiển thị và có trong HTML
	// Case 02: Element ko hiển thị và có trong HTML
	public boolean isElementDisplayed(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isDisplayed();
	}

	public boolean isElementDisplayed(WebDriver driver, String locator, String... restParams) {
		return getWebElement(driver, getDynamicLocator(locator, restParams)).isDisplayed();
	}

	public void setImplicitWait(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
	}

	// Case 03: Element ko có trên UI và ko có trong HTML
	public boolean isElementUndisplayed(WebDriver driver, String locator) {
		// Trước khi tìm element thì set time ngắn thôi
		setImplicitWait(driver, shortTimeout);
		List<WebElement> elements = getListWebElement(driver, locator);
		// Trả lại timeout mặc định cho các step còn lại
		setImplicitWait(driver, longTimeout);

		if (elements.size() > 0 && elements.get(0).isDisplayed()) {
			System.out.println("01 - Element có trên UI và có trong DOM");
			return false;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("02 - Element ko có trên UI và có trong DOM");
			return true;
		} else {
			System.out.println("03 - Element ko có trên UI và ko có trong DOM");
			return true;
		}
	}

	public boolean isElementSelected(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isSelected();
	}
	
	public boolean isElementSelected(WebDriver driver, String locator, String... restParams) {
		return getWebElement(driver, getDynamicLocator(locator, restParams)).isSelected();
	}

	public boolean isElementEnabled(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isEnabled();
	}

	public void switchToIframe(WebDriver driver, String locator) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getByLocator(locator)));
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void hoverToElement(WebDriver driver, String locator) {
		new Actions(driver).moveToElement(getWebElement(driver, locator)).perform();
	}

	public void doubleClickToElement(WebDriver driver, String locator) {
		new Actions(driver).doubleClick(getWebElement(driver, locator)).perform();
	}

	public void rightClickToElement(WebDriver driver, String locator) {
		new Actions(driver).contextClick(getWebElement(driver, locator)).perform();
	}

	public void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator) {
		new Actions(driver).dragAndDrop(getWebElement(driver, sourceLocator), getWebElement(driver, targetLocator)).perform();
	}

	public void sendKeyBoardToElement(WebDriver driver, String locator, Keys key) {
		new Actions(driver).sendKeys(getWebElement(driver, locator), key).perform();
	}

	public Object executeForBrowser(WebDriver driver, String javaScript) {
		return ((JavascriptExecutor) driver).executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		return (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		String textActual = (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		((JavascriptExecutor) driver).executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(WebDriver driver, String locator) {
		WebElement element = getWebElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
	}
	
	public void clickToElementByJS(WebDriver driver, String locator, String... restParams) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, getDynamicLocator(locator, restParams)));
	}

	public void scrollToElementOnTop(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
	}

	public void scrollToElementOnDown(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locator));
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
	}

	public String getWebElementValidationMessage(WebDriver driver, String locator) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		return (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getWebElement(driver, locator));
	}

	public void waitForElementVisible(WebDriver driver, String locator) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
	}

	public void waitForElementVisible(WebDriver driver, String locator, String... restParams) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicLocator(locator, restParams))));
	}

	public void waitForListElementVisible(WebDriver driver, String locator) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.visibilityOfAllElements(getListWebElement(driver, locator)));
	}

	public boolean waitForElementInvisible(WebDriver driver, String locator) {
		return new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
	}

	public boolean waitForListElementInvisible(WebDriver driver, String locator) {
		return new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locator)));
	}

	protected void waitForElementClickable(WebDriver driver, String locator) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.elementToBeClickable(getWebElement(driver, locator)));
	}

	protected void waitForElementClickable(WebDriver driver, WebElement element) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.elementToBeClickable(element));
	}

	protected void waitForElementClickable(WebDriver driver, String locator, String... restParams) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.elementToBeClickable(getWebElement(driver, getDynamicLocator(locator, restParams))));
	}

	public boolean isPageLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
		String filePath = GlobalConstants.UPLOAD_PATH;
		String fullFileName = "";
		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}
		fullFileName = fullFileName.trim();
		getWebElement(driver, BaseElementUI.UPLOAD_FILE_TYPE).sendKeys(fullFileName);
	}

	private long shortTimeout = GlobalConstants.SHORT_TIMEOUT;
	private long longTimeout = GlobalConstants.LONG_TIMEOUT;
}
