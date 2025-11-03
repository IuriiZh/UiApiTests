# UI & API Test Automation Framework

A comprehensive test automation framework for both UI and API testing, built with Java, Selenide, REST Assured, and JUnit 5, with integrated Allure reporting.

## Tech Stack

- **Java 17** - Programming language
- **Gradle 8.14** - Build automation tool
- **Selenide 7.11.1** - UI testing framework
- **REST Assured 5.5.6** - API testing framework
- **JUnit 5** - Test execution framework
- **Allure 2.29.1** - Test reporting
- **Lombok 1.18.30** - Code generation library
- **Jackson 2.20.0** - JSON processing
- **Log4j** - Logging framework

## Project Structure

```
UiApiTests/
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── api/
│   │       │   ├── model/           # API data models
│   │       │   │   ├── requests/    # Request POJOs
│   │       │   │   └── responses/   # Response POJOs
│   │       │   └── setup/           # API request specifications
│   │       ├── pages/               # Page Object Model classes
│   │       │   ├── CartPage.java
│   │       │   ├── CheckoutInformationPage.java
│   │       │   ├── CheckoutOverviewPage.java
│   │       │   ├── LandingPage.java
│   │       │   ├── LoginPage.java
│   │       │   └── ProductsPage.java
│   │       └── utils/               # Utility classes
│   │           ├── PropertyReader.java
│   │           ├── UIBaseTest.java
│   │           └── WebEventListener.java
│   └── test/
│       └── java/
│           └── tests/
│               ├── api/             # API test cases
│               │   ├── LoginTests.java
│               │   ├── ResourceTests.java
│               │   ├── UserdataTests.java
│               │   └── UserTests.java
│               └── ui/              # UI test cases
│                   ├── UiCart.java
│                   ├── UiLanding.java
│                   └── UiLogin.java
├── build/
│   ├── allure-results/             # Raw Allure test results
│   └── reports/
│       └── allure-report/          # Generated Allure HTML reports
├── build.gradle                     # Gradle build configuration
└── README.md
```

## Features

- **Hybrid Testing Framework**: Supports both UI and API testing
- **Page Object Model**: Clean separation of test logic and page structure
- **Allure Integration**: Beautiful test reports with @Epic, @Story, @Feature annotations
- **Parallel Execution**: Support for running tests in parallel
- **Code Quality Tools**:
  - Checkstyle for code style enforcement
  - JaCoCo for code coverage
  - OWASP Dependency Check for security vulnerabilities
- **Flexible Test Execution**: Run tests by tags, individual tests, or entire suites
- **Custom Event Listener**: WebEventListener for enhanced debugging

## Prerequisites

- Java Development Kit (JDK) 17 or higher
- Gradle 8.14 or higher (or use included Gradle wrapper)
- Chrome/Firefox browser for UI tests

## Installation

1. Clone the repository:
```bash
git https://github.com/IuriiZh/UiApiTests.git
cd UiApiTests
```

2. Build the project:
```bash
./gradlew build
```

## Running Tests

### Run All Tests
```bash
./gradlew test
```

### Run Tests with Parallel Execution
```bash
./gradlew test -Dthreads=4
```

### Run Tests by Tag
```bash
./gradlew orderTags -Dtag=api
./gradlew orderTags -Dtag=ui
```

### Run Specific Test Class
```bash
./gradlew test --tests "tests.api.LoginTests"
./gradlew test --tests "tests.ui.UiLogin"
```

## Test Reporting

### Generate and View Allure Report

After running tests, generate the Allure report:

```bash
./gradlew allureReport
./gradlew allureServe
```

The Allure report provides:
- Test execution statistics
- Test cases organized by Epic, Story, and Feature
- Test severity levels
- Detailed test steps and logs
- Screenshots for UI tests
- API request/response details

## Configuration

Test configuration is managed through the `PropertyReader` utility class. Configure test properties such as:
- API base URLs
- Test credentials
- Browser settings
- Environment-specific configurations

## Code Quality

### Run Checkstyle
```bash
./gradlew checkstyleMain
./gradlew checkstyleTest
```

### Generate Code Coverage Report
```bash
./gradlew test jacocoTestReport
```

### Run Security Dependency Check
```bash
./gradlew dependencyCheckAnalyze
```

## Test Cases

### API Tests
- **LoginTests**: Authentication scenarios including successful login and various error cases
- **UserTests**: User creation and management
- **UserdataTests**: User data retrieval and validation
- **ResourceTests**: Resource API endpoint testing

### UI Tests
- **UiLogin**: Login page functionality
- **UiCart**: Shopping cart operations
- **UiLanding**: Landing page interactions

## Allure Annotations

Tests are organized using Allure annotations:
- `@Epic`: High-level business requirements (e.g., "API Authentication")
- `@Story`: User stories (e.g., "As a user, I want to login with valid credentials")
- `@Feature`: Feature categories (e.g., "Authentication")
- `@Severity`: Test importance (BLOCKER, CRITICAL, NORMAL, MINOR, TRIVIAL)

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License.

## Contact

For questions or support, please contact the project maintainers.
