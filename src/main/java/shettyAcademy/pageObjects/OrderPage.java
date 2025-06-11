package shettyAcademy.pageObjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import shettyAcademy.AbstractComponents.AbstractComponant;

public class OrderPage extends AbstractComponant {
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".totalRow button")
	WebElement checkoutEle; // Checkout button

	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> productNames; // List of product titles in cart

	public Boolean VerifyOrderDisplay(String productName) {
		// Check if product is displayed in cart
		return productNames.stream().
		anyMatch(product -> product.getText().equalsIgnoreCase(productName));
	}
}
