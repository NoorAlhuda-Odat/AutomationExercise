# AutomationExercise UI Test Automation

This repository contains an automated UI test suite for the [AutomationExercise](https://automationexercise.com/) website. The test scripts are written in **Java** using **Selenium WebDriver** and **TestNG**, and organized as a Maven project.


## ✅ Automated Test Cases

The following UI test scenarios were automated:

1. **User Registration**  
2. **Login with Valid Credentials**  
3. **Search for a Product**  
4. **View Products by Brand**  
5. **Add Random Product to Cart**  
6. **Submit Product Review**  
7. **Contact Us Form Submission**  
8. **Subscribe via Footer**  
9. **Proceed to Checkout**  
10. **Logout Functionality**

Each test is implemented in the `AppTest.java` class and annotated with `@Test(priority = X)` using the TestNG framework. Random data generation and configuration are handled in the `TestData.java` class.

## 🧪 Technologies Used

- Java   
- Selenium WebDriver  
- TestNG  
- Maven  
- ChromeDriver  
- Eclipse IDE

## 🧠 How to Run the Tests

1. Clone the repository
2. Open the project in **Eclipse** or any Java IDE
3. Make sure **ChromeDriver** is installed and configured in system path
4. Run the `AppTest.java` class as a **TestNG Suite**
5. View HTML report in `/test-output/index.html`

## 👩‍💻 Author

**Noor Alhuda Odat**  
QA Trainee | Automation Tester  
📧 nooralhuda.mohammad.odat@gmail.com  
🔗 [GitHub Profile](https://github.com/NoorAlhuda-Odat)

---

Feel free to fork this repo or contribute if you'd like to improve the test coverage or add CI/CD integration in the future.



