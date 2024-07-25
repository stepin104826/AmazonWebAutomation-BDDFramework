# AmazonWebAutomation-BDDFramework
## Objective

This project aims to automate the testing of various functionalities of the Amazon Shopping Website using the Behavior-Driven Development (BDD) approach with the Cucumber framework. The goal is to ensure the quality and reliability of the website's features through comprehensive and automated test scenarios.
Project Structure
Directory Layout

1.src/main/java/:
        org.amazon.pages: Contains the Page Object Model (POM) classes representing different pages of the Amazon website.
        org.amazon.utils: Contains utility classes for handling common functions like browser setup, configuration management, and more.

2. src/test/java/:
        org.amazon.stepdefs: Contains the step definitions that link the Gherkin steps to the underlying test code.
        org.amazon.runner: Contains the test runner classe for executing the Cucumber test suites.
        org.amazon.features: Contains the feature files written in Gherkin syntax, outlining the test scenarios.

3. test-reports/:
        Contains the generated in-built cucumber HTML test reports for each test scenario after the execution of the test suites.

4. target/:
        Contains advanaced Cucumber Reports, compiled and other files generated during the build and test execution process.

## Test Automation Technologies Used

    Java: The primary programming language used for writing the automation scripts.
    Cucumber: BDD framework used to write readable and executable specifications.
    Selenium WebDriver: Web automation framework used for browser interaction.
    TestNG: Testing framework for managing and running the test suites.
    Maven: Build automation tool used for managing project dependencies and build lifecycle.
    ExtentReports: Reporting library used for generating comprehensive test execution reports.

## Components
1.** Page Object Model (POM)**

The project follows the Page Object Model design pattern to represent web pages as classes, encapsulating the interaction logic for each page. This enhances code reusability and maintainability.
Feature Files

2. **Feature files**
These are written in Gherkin syntax and describe the test scenarios in a human-readable format. Each feature file corresponds to a specific functionality of the Amazon website.
Step Definitions

3. **Step definitions**
Implement the Gherkin steps in the feature files to the underlying Java code. These definitions contain the logic for interacting with the web elements and performing the necessary actions.
Test Runner

4. **Test runner class**
Responsible for executing the Cucumber test suites. They configure the test execution settings, such as the feature files to run and the report generation options.
Getting Started
Prerequisites

## Pre-requisites
1. Java JDK 8 or above
2. Maven Dependencies
3. IDE (IntelliJ, Eclipse, etc.)
4. Cucumber Plugins
5. TestNG (necessary for parallel execution)
