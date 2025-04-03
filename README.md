# JUnit5 Quality Assurance 

This repository contains the solution for the Quality Assurance homework using JUnit5. It demonstrates various JUnit5 features like testing with parallel execution, parameterized tests, exception testing, and timeouts.

## Key Features in the Tests

- **Parameterized Tests**: Use of `@ParameterizedTest` and `@CsvSource` for testing with different sets of input.
- **Timeout Tests**: Timeout applied on certain tests to ensure they complete within the given time.
- **Test Lifecycle Hooks**: Use of `@BeforeAll`, `@AfterAll`, `@BeforeEach`, and `@AfterEach` to set up and tear down resources for tests.
- **Test Ordering**: Tests are ordered using the `@Order` annotation to ensure specific execution order.
- **Parallel Test Execution**: Parallel execution enabled with `@Execution(ExecutionMode.CONCURRENT)` to speed up tests.
- **Test Coverage**: EclEmma (or equivalent) used to measure the code coverage and ensure adequate testing of each class.

### Prerequisites
- **Java 11+** (Make sure to install the required JDK).
- **JUnit 5** (Included as dependencies in the project).
- **Maven/Gradle** (Optional, for dependency management).

### Running Tests via IDE

1. **Import the project** into your IDE (e.g., IntelliJ IDEA, Eclipse).
2. **Run the tests** directly from the IDE. JUnit 5 should automatically run the tests with the appropriate configuration.


### Parallel Execution

Parallel execution is enabled for this test class by using the `@Execution(ExecutionMode.CONCURRENT)` annotation. You can configure parallel execution in your `junit-platform.properties` file with the following settings:

```properties
junit.jupiter.execution.parallel.enabled=true
junit.jupiter.execution.parallel.mode.default=concurrent
junit.jupiter.execution.parallel.config.strategy=dynamic
```

This configuration ensures that tests run concurrently, improving performance, especially for independent test cases.

### Test Ordering

Test execution order is controlled using the `@TestMethodOrder(MethodOrderer.OrderAnnotation.class)` annotation. The tests are executed in the order specified by the `@Order` annotation, which ensures that certain tests run before others.

## Contributing

If you'd like to contribute to this repository, feel free to fork the project, make changes, and submit a pull request.

## Notes

- **Intentionally Failing Test**: The `testIntentionallyFailing` in `ProductTest` is intentionally disabled using `@Disabled` with an explanation on how to fix it.
- **Test Suite**: The `TestSuite.java` file uses `@Suite` to run all tests in a single suite.

## Authors

- **Renad Iwidat** - [GitHub](https://github.com/renad-iwidat)








