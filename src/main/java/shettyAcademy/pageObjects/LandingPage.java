package shettyAcademy.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import shettyAcademy.AbstractComponents.AbstractComponant;

public class LandingPage extends AbstractComponant {
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver); // Initialize AbstractComponent
		this.driver = driver;
		PageFactory.initElements(driver, this); // Initialize web elements
	}

	@FindBy(id = "userEmail")
	WebElement userEmail; // Email input field

	@FindBy(id = "userPassword")
	WebElement userPassword; // Password input field

	@FindBy(id = "login")
	WebElement submit; // Login button
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;

	public String getErrorMessage() {
		waitForWebelementToAppear(errorMessage);
		return errorMessage.getText();

	}

	public ProductCateloge loginApplication(String email, String pwd) {
		userEmail.sendKeys(email); // Enter email
		userPassword.sendKeys(pwd); // Enter password
		submit.click(); // Click login
		return new ProductCateloge(driver); // Return product catalog page object
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/"); // Navigate to URL
	}
}