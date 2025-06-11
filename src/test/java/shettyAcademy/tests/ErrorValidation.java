package shettyAcademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import shettyAcademy.TestComponents.BaseTest;
import shettyAcademy.TestComponents.Retry;
import shettyAcademy.pageObjects.CartPage;
import shettyAcademy.pageObjects.CheckoutPage;
import shettyAcademy.pageObjects.ConfiramationPage;
import shettyAcademy.pageObjects.ProductCateloge;

public class ErrorValidation extends BaseTest {

    @Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
    public void LoginErrorValidation() throws IOException, InterruptedException {
        // landingpage is initialized by BaseTest @BeforeMethod
        landingpage.loginApplication("kishor333@gmail.com", "Uemy@333");

        String actualError = landingpage.getErrorMessage();
        System.out.println("Error Message Displayed: " + actualError);

        Assert.assertEquals(actualError, "Incorrect email or password.");
    }

    @Test
    public void productErrorValidation() throws IOException, InterruptedException {
        String productName = "ZARA COAT 3";

        // landingpage is initialized by BaseTest @BeforeMethod
        ProductCateloge productcatelog = landingpage.loginApplication("kishorkuyate333@gmail.com", "Udemy@333");

        List<WebElement> products = productcatelog.getProductList();
        productcatelog.addProductToCart(productName);

        CartPage cartpage = productcatelog.goToCartPage();

        // Check for incorrect product intentionally to validate test
        Boolean match = cartpage.VerifyProductDisplay("ZARA COAT 33");

        // Expected to fail, use Assert.assertFalse for negative test
        Assert.assertFalse(match, "Product should NOT be present in the cart.");
    }
}
