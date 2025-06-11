package shettyAcademy.pageObjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import shettyAcademy.AbstractComponents.AbstractComponant;

public class CartPage extends AbstractComponant {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".totalRow button")
	WebElement checkoutEle; // Checkout button

	@FindBy(css = ".cartSection h3")
	List<WebElement> productTitles; // List of product titles in cart

	public Boolean VerifyProductDisplay(String productName) {
		// Check if product is displayed in cart
		return productTitles.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
	}

	public CheckoutPage goToCheckout() {
		checkoutEle.click(); // Click checkout button
		return new CheckoutPage(driver); // Navigate to checkout page
	}
}
