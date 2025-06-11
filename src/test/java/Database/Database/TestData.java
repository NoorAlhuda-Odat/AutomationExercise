package Database.Database;

import java.time.Duration;
import java.util.Random;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TestData {
	
	WebDriver driver = new ChromeDriver();
	String URL ="https://www.automationexercise.com";
	Random rand = new Random();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
//For BeforeTest	
	 public void setup() {
		  driver.get(URL);
		  driver.manage().window().maximize();
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		}
 
//For test 1
	 String expectedTitle = "Automation Exercise";
	 
//For test 2
	//Enter name and email address
	 String[] names = {"Noor","Ragad","Shahed","Malak","Zain"};
	 int randomIndex = rand.nextInt(5);
	 
	 String randomName =names[randomIndex];
	 String randomLastName = names [randomIndex];
	 
	 int randomIndexForTheEmail = rand.nextInt(11111);
	 String randomEmail = randomName+randomIndexForTheEmail+"@gmail.com";
	 
//For test 3
	 String expectedMessage = "Logged in as " + randomName;
	 
//For test 4
	 String[] productName = {"Top", "Tshirt", "Dress"};
	 int randomProductIndex = rand.nextInt(3);
	 String randomProductName = productName[randomProductIndex];
	 String expectedProductName = randomProductName.toLowerCase();
	 

}
