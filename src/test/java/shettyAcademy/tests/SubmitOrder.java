package shettyAcademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import shettyAcademy.TestComponents.BaseTest;
import shettyAcademy.TestComponents.Retry;
import shettyAcademy.pageObjects.CartPage;
import shettyAcademy.pageObjects.CheckoutPage;
import shettyAcademy.pageObjects.ConfiramationPage;
import shettyAcademy.pageObjects.LandingPage;
import shettyAcademy.pageObjects.OrderPage;
import shettyAcademy.pageObjects.ProductCateloge;

public class SubmitOrder extends BaseTest {
	//Jenking 8080 kishorkuyate1 Selenium@123 http://localhost:8080/
	String productName = "ZARA COAT 3";
	//Stand Alone Test 
 	//changes made for stand alone test
	@Test(dataProvider="getData",groups= {"purchaseOrder"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		// landingpage is already initialized by @BeforeMethod in BaseTest
		ProductCateloge productcatelog = landingpage.loginApplication(input.get("email"), input.get("pwd"));

		List<WebElement> products = productcatelog.getProductList();
		productcatelog.addProductToCart(input.get("productN"));

		CartPage cartpage = productcatelog.goToCartPage();
		Boolean match = cartpage.VerifyProductDisplay(input.get("productN"));

		CheckoutPage checkoutPage = cartpage.goToCheckout();
		checkoutPage.selectCountry("india");

		ConfiramationPage confiramationPage = checkoutPage.submitOrder();

		Thread.sleep(2000);
		String confirmmsg = confiramationPage.getConfirmationmsg();

		Assert.assertTrue(confirmmsg.equalsIgnoreCase("Thankyou for the order."));
		System.out.println(confirmmsg);
	}
	
	@Test(dependsOnMethods= {"submitOrder"},retryAnalyzer=Retry.class)
	public void OrderHistoryTest() {
		// landingpage is already initialized by @BeforeMethod in BaseTest
		ProductCateloge productcatelog = landingpage.loginApplication("kishor222@gmail.com", "Udemy@222");
		OrderPage orderPage=productcatelog.goToOrderPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
	}
	
	@DataProvider 
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJSONDataToMap("C:\\U"
				+ "sers\\kisho\\Eclipse workspace 2\\SeleniumFrameworkDesign\\src\\test\\java\\shettyAcademy\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}

}
