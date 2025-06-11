package shettyAcademy.pageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import shettyAcademy.AbstractComponents.AbstractComponant;

public class ProductCateloge extends AbstractComponant {
    WebDriver driver;

    public ProductCateloge(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".mb-3")
    List<WebElement> products;               // List of products displayed

    By productBy = By.cssSelector(".mb-3"); // Locator for product elements
    By addtoCart = By.cssSelector(".card-body button:last-of-type");  // Add to cart button
    By toastMessage = By.cssSelector("#toast-container");             // Toast notification

    public List<WebElement> getProductList() throws InterruptedException {
        //waitForWebelementToAppear(productBy);  // Wait until products appear
        
    	Thread.sleep(1000);
    	return products;                     // Return product list
    }

    public WebElement getProductName(String productName) throws InterruptedException {
        // Filter product list by name
        WebElement prod = getProductList().stream()
            .filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName))
            .findAny().orElse(null);
        return prod;
    }

    public void addProductToCart(String productName) throws InterruptedException {
        WebElement prod = getProductName(productName);      // Get product by name
        prod.findElement(addtoCart).click();
        Thread.sleep(5000);
        // Click add to cart button
       // waitForWebelementToAppear(toastMessage);                // Wait for toast to appear
        waitForElementToDisappear(toastMessage);             // Wait for toast to disappear
    }
}
