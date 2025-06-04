package Database.Database;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppTest extends TestData {

	@BeforeTest
	public void mySetup(){
		setup();
	}
	
	@Test(priority = 1,enabled = false)
	public void verifyHomepageLoadsSuccessfully() {
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle, "Home page did not load correctly!");
	}
	
	@Test(priority = 2,enabled = false)
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
	
	@Test(priority = 3,enabled = false)
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
	
	@Test(priority = 4)
	public void productSearchTest() {
	    WebElement products = driver.findElement(By.xpath("//a[@href='/products']"));
	    products.click();
	    
	    WebElement searchBox = driver.findElement(By.id("search_product"));
	    searchBox.sendKeys(randomProductName);

	    WebElement searchButton = driver.findElement(By.id("submit_search"));
	    searchButton.click();
	    
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

	    WebElement productTitle = driver.findElement(By.xpath("//div[@class='productinfo text-center']/p"));
	    String actualProductName = productTitle.getText().toLowerCase();

	    System.out.println("Expected: " + expectedProductName);
	    System.out.println("Actual: " + actualProductName);

	    if (actualProductName.contains(expectedProductName)) {
	        Assert.assertTrue(true, "✅ Product name contains searched word.");
	    } else {
	        Assert.fail("❌ Product name does not contain the searched word.");
	    }
	}

	
}
