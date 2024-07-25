# AmazonWebAutomation-BDDFramework
## About
This project is designed to test multiple user-flows and real-world scenarios of Amazon's shopping website. This project leverages Behavior-Driven Development (BDD) with Cucumber Framework to deliver robust, scalable, and maintainable automation scripts.

## Demo
Refer to "DemoVideos" folder to see the working demos

## Test Automation Technologies Used
1. **Java:** The primary programming language used for writing the automation scripts.
2. **Cucumber:** BDD framework used to write readable and executable specifications.
3. **Selenium WebDriver:** Web automation framework used for browser interaction.
4. **TestNG:** Testing framework for managing and running the test suites.
5. **Maven:** Build automation tool used for managing project dependencies and build lifecycle.
## Key Features
1. **Simple BDD with Cucumber:** This framework utilizes Cucumber to write human-readable scenarios that capture the behavior of Amazon's shopping processes, making tests easier to understand and manage.

2. **Comprehensive Test Coverage:** It includes detailed test cases for various user interactions such as product search, cart management, and checkout, ensuring comprehensive test coverage for critical functionalities.

3. **Reusability:** This Framework is designed using Page Object Model(POM) pattern, which makes it easy to re-use and replace web elements and action methods based on the requirements.

4. **Scalability:** Designed with scalability in mind, this framework can be easily extended to accommodate new features and functionalities, adapting to the evolving needs of the application.


## Project Structure
### Directory Layout

1. **src/main/java/:**

        a. org.amazon: Contains the Page Object Model (POM) classes representing different pages of the Amazon Shopping website.
        b. BrowserUtilities: Contains browser utility classes for handling  browser setup.
        
2. **src/test/java/:**
   
        a. org.amazon: Contains the step definitions that link the Gherkin steps to the underlying test code.
        b. runner: Contains the test runner classe for executing the Cucumber test suites.
        c. resources/features: Contains the feature files written in Gherkin syntax, outlining the test scenarios.
   
3. **test-reports/:**

        Contains the generated in-built cucumber HTML test reports for each test scenario after the execution of the test suites.
   
4. **target/:**
   
        Contains advanced Cucumber Reports, compiled and other files generated during the build and test execution process.

5. **pom.xml:**

        Maven POM XML files used to install and manage project dependencies, plugins, config. etc.


## Components
1. **Page Object Model (POM)**:
The project follows the Page Object Model design pattern to represent web pages as classes, encapsulating the interaction logic for each page. This enhances code reusability and maintainability.

2. **Feature files**:
These are written in Gherkin syntax and describe the test scenarios in a human-readable format using Given-When-Then templates. Each feature file corresponds to a specific functionality of the Amazon website.
Step Definitions

3. **Step definitions**:
Implement the Gherkin steps in the feature files to the underlying Java code. These definitions contain the logic for interacting with the web elements and performing the necessary actions.
Test Runner

4. **Test runner class**:
Responsible for executing the Cucumber test suites. They configure the test execution settings, such as the feature files to run and the report generation options.

5. **Test Reporting**:
The Project uses in-built Cucumber Plugins to generate both indepth and simple overview reports 


## Pre-requisites
1. Java JDK 8 or above
2. Maven Dependencies
3. IDE (IntelliJ, Eclipse, etc.)
4. Cucumber Plugins
5. TestNG (necessary for parallel execution)


## Helpful Links
1. Cucumber Documentation: https://cucumber.io/docs/cucumber/
2. Selenium Documentation: https://www.selenium.dev/documentation/webdriver/
3. TestNG Documentation: https://testng.org/
4. Maven Documentation: https://maven.apache.org/guides/index.html


## Contributing
Feel free to fork this repository and contribute by submitting pull requests.
