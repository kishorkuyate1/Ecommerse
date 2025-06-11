package shettyAcademy.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import shettyAcademy.AbstractComponents.AbstractComponant;

public class ConfiramationPage extends AbstractComponant {
	WebDriver driver;

	public ConfiramationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".hero-primary")
	WebElement confirmationMessage; // Confirmation message element

	public String getConfirmationmsg() throws InterruptedException {
		Thread.sleep(5000); // Static wait (could improve)
		return confirmationMessage.getText(); // Return confirmation text
	}
}
