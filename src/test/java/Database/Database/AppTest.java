package Database.Database;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppTest extends TestData {

	@BeforeTest
	public void mySetup(){
		setup();
	}
	
	@Test(priority = 1)
	public void registerUserTest () {
		// Click on 'Signup / Login' button
		WebElement signupLoginButton = driver.findElement(By.linkText("Signup / Login"));
		signupLoginButton.click();
		
		//Enter name and email address
		WebElement name = driver.findElement(By.xpath("//input[@data-qa='signup-name']"));
		name.sendKeys(randomName);
		WebElement email = driver.findElement(By.xpath("//input[@data-qa='signup-email']"));
		email.sendKeys(randomEmail);
		
		//Click 'Signup' button
		WebElement signupButton =driver.findElement(By.xpath("//button[@data-qa='signup-button']"));
		signupButton.click();
		
		//Enter Account Information
		List<WebElement> titleRadios = driver.findElements(By.name("title"));
		titleRadios.get(1).click();
		
		WebElement password =driver.findElement(By.id("password"));
		password.sendKeys("P@ssw0rd");
		
		WebElement days = driver.findElement(By.id("days"));
		Select daySelect = new Select(days);
		List<WebElement> Option = daySelect.getOptions();
		int RandomDropdownIndex = rand.nextInt(Option.size());
			
		WebElement months= driver.findElement(By.id("months"));
		Select monthSelect = new Select(months);
		
		WebElement years = driver.findElement(By.id("years"));
		Select yearSelect = new Select(years);
		
		daySelect.selectByIndex(RandomDropdownIndex);
		monthSelect.selectByVisibleText("January");
		yearSelect.selectByValue("2000");
		
		//Address Information
		 WebElement firstNameField = driver.findElement(By.id("first_name"));
		 firstNameField.sendKeys(randomName);
		 
		 WebElement lastNameField = driver.findElement(By.id("last_name"));
		 lastNameField.sendKeys(randomLastName);
		 
		 WebElement companyField = driver.findElement(By.id("company"));
		 companyField.sendKeys("Al Hussein Technical University");
		 
		 WebElement address1Field = driver.findElement(By.name("address1"));
		 address1Field.sendKeys("King Hussein Business Park, QA Training");
		 
		 WebElement address2Field = driver.findElement(By.name("address2"));
		 address2Field.sendKeys("QA Department");
		 
		 WebElement countryDropdown = driver.findElement(By.id("country"));
			Select selectCountry = new Select(countryDropdown);
			selectCountry.selectByVisibleText("Canada");  
			
		 WebElement stateField = driver.findElement(By.name("state"));
		 stateField.sendKeys("Amman");
		 
		 WebElement cityField = driver.findElement(By.name("city"));
		 cityField.sendKeys("Amman");
		 
		 WebElement zipcodeField = driver.findElement(By.name("zipcode"));
		 zipcodeField.sendKeys("11941");
		 
		 WebElement mobileNumberField = driver.findElement(By.name("mobile_number"));
		 mobileNumberField.sendKeys("+962790000000");
		 
		 // Click on 'Create Account' button
		 WebElement createAccountButton = driver.findElement(By.xpath("//button[@data-qa='create-account']"));
		 createAccountButton.click();
		 
		 // Verify account created message is displayed
		 WebElement accountCreatedMsg = driver.findElement(By.xpath("//h2[@data-qa='account-created']"));
		 Assert.assertTrue(accountCreatedMsg.isDisplayed(), "Account was not created successfully!");
		 
		 WebElement continuebutton =driver.findElement(By.xpath("//a[@data-qa='continue-button']"));
		 continuebutton.click();
		 	
	}
	
	@Test(priority = 2)
	public void loginUserTest() {
		// Click on 'Logout' button
		WebElement logout = driver.findElement(By.linkText("Logout"));
		logout.click();
		
		// Enter valid login credentials
		WebElement email = driver.findElement(By.xpath("//input[@data-qa='login-email']"));
		email.sendKeys(randomEmail);
		
		WebElement password =driver.findElement(By.xpath("//input[@data-qa='login-password']"));
		password.sendKeys("P@ssw0rd");
		
		// Click Login button
		WebElement loginButton =driver.findElement(By.xpath("//button[@data-qa='login-button']"));
		loginButton.click();
		
		// Validate the "Logged in as [username]" message
		WebElement loggedInAs =driver.findElement(By.partialLinkText("Logged in as"));
		String actualMessage = loggedInAs.getText();
		System.out.println(actualMessage);
		Assert.assertEquals(actualMessage, expectedMessage, "Login message is incorrect!");
	    
	}
	
	@Test(priority = 3)
	public void productSearchTest() {
	    // Click on "Products" page
	    WebElement products = driver.findElement(By.xpath("//a[@href='/products']"));
	    products.click();
	    
	    // Enter product name in the search box
	    WebElement searchBox = driver.findElement(By.id("search_product"));
	    searchBox.sendKeys(randomProductName);

	    // Click on the "Search" button
	    WebElement searchButton = driver.findElement(By.id("submit_search"));
	    searchButton.click();
	    
	    // Wait for search results to appear
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

	    // Get the name of the displayed product
	    WebElement productTitle = driver.findElement(By.xpath("//div[@class='productinfo text-center']/p"));
	    String actualProductName = productTitle.getText().toLowerCase();

	    // Print expected and actual product names
	    System.out.println("Expected: " + expectedProductName);
	    System.out.println("Actual: " + actualProductName);

	    // Verify that the actual name contains the searched keyword
	    if (actualProductName.contains(expectedProductName)) {
	        Assert.assertTrue(true, "✅ Product name contains searched word.");
	    } else {
	        Assert.fail("❌ Product name does not contain the searched word.");
	    }
	}
	
	@Test(priority = 4)
	public void addRandomProductToCartTest() throws InterruptedException {
	    // Click on 'Products' button
	    WebElement products = driver.findElement(By.partialLinkText("Products"));
	    products.click();
	    Thread.sleep(3000);

	    // Get all product cards
	    List<WebElement> productCards = driver.findElements(By.xpath("//div[@class='product-image-wrapper']"));
	    Random random = new Random();
	    int randomIndex = random.nextInt(productCards.size());
	    WebElement randomCard = productCards.get(randomIndex);

	    // Scroll to the card
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", randomCard);
	    Thread.sleep(1000);

	    // Hover over the selected card with build().perform()
	    Actions actions = new Actions(driver);
	    actions.moveToElement(randomCard).build().perform();
	    Thread.sleep(1000);

	    // Find the "Add to cart" inside the overlay only
	    WebElement addToCartOverlayBtn = randomCard.findElement(By.xpath(".//div[@class='product-overlay']//a[contains(@class, 'add-to-cart')]"));

	    // Click it with JS to avoid interception
	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartOverlayBtn);
	    Thread.sleep(3000);

	    // Verify success message
	    WebElement confirmation = driver.findElement(By.xpath("//h4[@class='modal-title w-100']"));
	    Assert.assertTrue(confirmation.isDisplayed(), "❌ Product not added to cart!");
	    System.out.println("✅ Random product successfully added to cart from overlay.");

	    // Close modal
	    WebElement continueShoppingBtn = driver.findElement(By.xpath("//button[@data-dismiss='modal']"));
	    continueShoppingBtn.click();
	    Thread.sleep(1000);
	}

	@Test(priority = 5)
	public void proceedToCheckoutTest() {
		
		// Click on 'Cart' button
		WebElement cartBtn = driver.findElement(By.partialLinkText("Cart"));
		cartBtn.click();

	    // Click on the 'Proceed To Checkout' button
	    WebElement proceedToCheckoutBtn = driver.findElement(By.partialLinkText("Proceed To Checkout"));
	    proceedToCheckoutBtn.click();
	    
	    // Verify that the checkout page is displayed by checking Address Details and Order Review sections
	    WebElement addressDetails = driver.findElement(By.xpath("//h2[contains(text(),'Address Details')]"));
	    WebElement orderReview = driver.findElement(By.xpath("//h2[contains(text(),'Review Your Order')]"));

	    Assert.assertTrue(addressDetails.isDisplayed(), "❌ Address details section is not displayed.");
	    Assert.assertTrue(orderReview.isDisplayed(), "❌ Order review section is not displayed.");

	    System.out.println("✅ Proceed to Checkout page displayed successfully.");
	    
	}
	
	@Test(priority = 6)
	public void contactUsFormSubmissionTest() throws InterruptedException {
	    // Click on the "Contact us" button
	    WebElement contactUsBtn = driver.findElement(By.linkText("Contact us"));
	    contactUsBtn.click();

	    // Fill in the form fields
	    driver.findElement(By.name("name")).sendKeys(randomName);
	    driver.findElement(By.name("email")).sendKeys(randomEmail);
	    driver.findElement(By.name("subject")).sendKeys("Testing Contact Form");
	    driver.findElement(By.id("message")).sendKeys("This is a test message for QA automation.");

	    // Click on the "Submit" button
	    WebElement submitBtn = driver.findElement(By.name("submit"));
	    submitBtn.click();

	    // Handle alert if it appears (some forms show an alert confirmation)
	    driver.switchTo().alert().accept(); // Only if there's an alert popup
	    Thread.sleep(1000);

	    // Verify confirmation message is displayed
	    WebElement successMsg = driver.findElement(By.xpath("//div[@class='status alert alert-success']"));
	    Assert.assertTrue(successMsg.isDisplayed(), "❌ Contact form submission failed!");
	    
	    System.out.println("✅ Contact form submitted successfully.");
	    
	}
	
	@Test(priority = 7)
	public void subscriptionTest() throws InterruptedException {
		// Click on the "Home" button
	    WebElement  homeBtn = driver.findElement(By.linkText("Home"));
	    homeBtn.click();
	    
	    // Scroll down to footer
	    js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	    Thread.sleep(1000); 
	    
	    // Find the subscription input field and enter email
	    WebElement footer = driver.findElement(By.id("footer"));
	    WebElement emailInput = footer.findElement(By.id("susbscribe_email"));
	    emailInput.sendKeys(randomEmail);
	   
	    // Click the Subscribe button
	    WebElement subscribeBtn = driver.findElement(By.id("subscribe"));
	    subscribeBtn.click();
	    Thread.sleep(1000);
	    
	    // Verify that the subscription confirmation message is displayed
	    WebElement confirmationMsg = driver.findElement(By.xpath("//div[@class='alert-success alert']"));
	    Assert.assertTrue(confirmationMsg.isDisplayed(), "❌ Subscription confirmation message not displayed.");
	    
	    System.out.println("✅ Subscription completed successfully.");
	   
	}
	
	@Test(priority = 8)
	public void viewBrandProductsTest() throws InterruptedException {
		// Click on 'Products' button
		WebElement products = driver.findElement(By.partialLinkText("Products"));
		products.click();
		
		// Scroll down 
	    js.executeScript("window.scrollTo(document.body.scrollHeight,500);");
	    Thread.sleep(1000); 
	    
	    // Select random brand
	    List<WebElement> brands = driver.findElements(By.cssSelector(".brands-name"));
	    int randomIndex= rand.nextInt(brands.size());
	    brands.get(randomIndex).click();
	    Thread.sleep(2000);
	    
	    // Verify that the brand page is displayed
	    WebElement brandTitle = driver.findElement(By.cssSelector(".title.text-center"));
	    Assert.assertTrue(brandTitle.isDisplayed(), "❌ Brand title not displayed.");
	    
	}

	@Test(priority = 9)
	public void submitProductReviewTest() throws InterruptedException {
		// Click on 'Products' button
		WebElement products = driver.findElement(By.xpath("//a[@href='/products']"));
		products.click();
		
		// Click on 'View Product' of the first product
		WebElement viewProductBtn = driver.findElement(By.xpath("(//a[@href='/product_details/1'])[1]"));
		viewProductBtn.click();
			
		// Scroll down
	    ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,500)");
	    Thread.sleep(1000);
	    
	    // Fill in the form fields
	    driver.findElement(By.id("name")).sendKeys(randomName);
	    driver.findElement(By.id("email")).sendKeys(randomEmail);
	    driver.findElement(By.id("review")).sendKeys("This is a test review for the product");
	    
	    // Click on 'submit' button
	    WebElement submit = driver.findElement(By.id("button-review"));
	    submit.click();
	    Thread.sleep(1000);
	    
	    // Verify success message is displayed and contains thank you text
	    WebElement successMsg = driver.findElement(By.cssSelector(".alert-success.alert"));
	    Assert.assertTrue(successMsg.isDisplayed(), "Review confirmation failed");
	    
	}

	@Test(priority = 10)
	public void testLogoutFunctionality() {
		// Click on 'Logout' button
		WebElement logout = driver.findElement(By.linkText("Logout"));
		logout.click();
	
	    WebElement loginLink = driver.findElement(By.xpath("//a[contains(@href,'/login')]"));
	    Assert.assertTrue(loginLink.isDisplayed() , "Logout verification failed");
	    
	}
	/*
	@AfterTest
	public void tearDown() {
	    driver.quit();
	}
*/
}
