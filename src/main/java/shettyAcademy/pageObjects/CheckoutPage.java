package shettyAcademy.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import shettyAcademy.AbstractComponents.AbstractComponant;

public class CheckoutPage extends AbstractComponant {
    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".action__submit")
    WebElement submit;

    @FindBy(css = "[placeholder='Select Country']")
    WebElement countryInput;

    // Selectors
    By countryResults = By.cssSelector(".ta-results");
    By lastCountryOption = By.cssSelector(".ta-item:last-child");

    public void selectCountry(String countryName) {
        // Type the country name in the input field
        Actions actions = new Actions(driver);
        actions.sendKeys(countryInput, countryName).build().perform();

        // Wait for the country suggestion box to appear
        waitForElementToAppear(countryResults);

        // Wait for the last suggestion to appear and then click it
        waitForElementToAppear(lastCountryOption);
        driver.findElement(lastCountryOption).click();
    }

    public ConfiramationPage submitOrder() {
        submit.click();
        return new ConfiramationPage(driver);
    }
}
