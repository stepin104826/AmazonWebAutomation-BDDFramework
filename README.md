# AmazonWebAutomation-BDDFramework
Objective

This project aims to automate the testing of various functionalities of the Amazon Shopping Website using the Behavior-Driven Development (BDD) approach with the Cucumber framework. The goal is to ensure the quality and reliability of the website's features through comprehensive and automated test scenarios.
Project Structure
Directory Layout

    src/main/java/:
        org.amazon.pages: Contains the Page Object Model (POM) classes representing different pages of the Amazon website.
        org.amazon.utils: Contains utility classes for handling common functions like browser setup, configuration management, and more.

    src/test/java/:
        org.amazon.stepdefs: Contains the step definitions that link the Gherkin steps to the underlying test code.
        org.amazon.runners: Contains the test runner classes for executing the Cucumber test suites.
        org.amazon.features: Contains the feature files written in Gherkin syntax, outlining the test scenarios.

    test-reports/:
        Contains the generated test reports after the execution of the test suites.

    target/:
        Contains compiled output and other files generated during the build and test execution process.

Technologies Used

    Java: The primary programming language used for writing the automation scripts.
    Cucumber: BDD framework used to write readable and executable specifications.
    Selenium WebDriver: Web automation framework used for browser interaction.
    TestNG: Testing framework for managing and running the test suites.
    Maven: Build automation tool used for managing project dependencies and build lifecycle.
    ExtentReports: Reporting library used for generating comprehensive test execution reports.

Components
Page Object Model (POM)

The project follows the Page Object Model design pattern to represent web pages as classes, encapsulating the interaction logic for each page. This enhances code reusability and maintainability.
Feature Files

Feature files are written in Gherkin syntax and describe the test scenarios in a human-readable format. Each feature file corresponds to a specific functionality of the Amazon website.
Step Definitions

Step definitions link the Gherkin steps in the feature files to the underlying Java code. These definitions contain the logic for interacting with the web elements and performing the necessary actions.
Test Runner

Test runner classes are responsible for executing the Cucumber test suites. They configure the test execution settings, such as the feature files to run and the report generation options.
Getting Started
Prerequisites

    Java JDK 8 or above
    Maven
    IDE (IntelliJ, Eclipse, etc.)
