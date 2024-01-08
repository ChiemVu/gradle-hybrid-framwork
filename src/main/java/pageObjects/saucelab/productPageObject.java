package pageObjects.saucelab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.saucelab.ProductPageUI;

public class productPageObject extends BasePage {
	WebDriver driver;

	public productPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void selectItemInProductSortDropdown(String textItem) {
		waitForElementClickable(driver, ProductPageUI.PRODUCT_CONTENER_DROPDOWN);
		selectItemInDefaultDropdown(driver, ProductPageUI.PRODUCT_CONTENER_DROPDOWN, textItem);
	}

	public boolean isProductNameSortByAscending() {
		// Khai báo ra 1 arrayList để chứa product name trên UI
		// List<String> productUIsList= new ArrayList<String>();
		ArrayList<String> productUIsList = new ArrayList<String>();

		// Lấy ra hết tất cả textname
		List<WebElement> productNameText = getListWebElement(driver, ProductPageUI.PRODUCT_NAME_TEXT);

		// Dùng vòng lặp để getText và add vào ArayList trên
		for (WebElement productName : productNameText) {
			productUIsList.add(productName.getText());
		}

		// Tạo ra 1 arrayList mới để sort dữ liệu
		ArrayList<String> productSortList = new ArrayList<String>();
		for (String productName : productUIsList) {
			productSortList.add(productName);
		}

		// Sort cái productSortList
		Collections.sort(productSortList);

		// So sanh 2 list đã bằng nhau hay chưa
		return productSortList.equals(productUIsList);
	}

	public boolean isProductNameSortByDescending() {
		// Khai báo ra 1 arrayList để chứa product name trên UI
		// List<String> productUIsList= new ArrayList<String>();
		ArrayList<String> productUIsList = new ArrayList<String>();

		// Lấy ra hết tất cả textname
		List<WebElement> productNameText = getListWebElement(driver, ProductPageUI.PRODUCT_NAME_TEXT);

		// Dùng vòng lặp để getText và add vào ArayList trên
		for (WebElement productName : productNameText) {
			productUIsList.add(productName.getText());
		}

		// Tạo ra 1 arrayList mới để sort dữ liệu
		ArrayList<String> productSortList = new ArrayList<String>();
		for (String productName : productUIsList) {
			productSortList.add(productName);
		}

		// Sort cái productSortList
		Collections.sort(productSortList);

		// Revert lại cái productSortList
		Collections.reverse(productSortList);

		// So sanh 2 list đã bằng nhau hay chưa
		return productSortList.equals(productUIsList);
	}

	public boolean isProductPriceSortByAscending() {
		ArrayList<Float> productUIsList = new ArrayList<Float>();

		List<WebElement> productPriceText = getListWebElement(driver, ProductPageUI.PRODUCT_PRICE_TEXT);

		for (WebElement productPrice : productPriceText) {
			productUIsList.add(Float.parseFloat(productPrice.getText().replace("$", "")));
//			String priceText = productPrice.getText();
//
//			//Xóa kí tự $ đi
//			priceText.replace("$", "");
//			//Convert qua kiểu float
//			Float priceTextFloat = Float.parseFloat(priceText);
//			productUIsList.add(priceTextFloat);
		}

		ArrayList<Float> productSortList = new ArrayList<Float>();
		for (Float productPrice : productUIsList) {
			productSortList.add(productPrice);
		}

		Collections.sort(productSortList);

		return productSortList.equals(productUIsList);
	}

	public boolean isProductPriceSortByDescending() {
		ArrayList<Float> productUIsList = new ArrayList<Float>();

		List<WebElement> productPriceText = getListWebElement(driver, ProductPageUI.PRODUCT_PRICE_TEXT);

		for (WebElement productPrice : productPriceText) {
			productUIsList.add(Float.parseFloat(productPrice.getText().replace("$", "")));
		}

		ArrayList<Float> productSortList = new ArrayList<Float>();
		for (Float productPrice : productUIsList) {
			productSortList.add(productPrice);
		}

		Collections.sort(productSortList);

		// Revert lại cái productSortList
		Collections.reverse(productSortList);

		return productSortList.equals(productUIsList);
	}

}