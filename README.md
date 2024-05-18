# CoreBankX

## Introduction

Welcome to the CoreBankX project repository!

### Overview

The CoreBankX project is a sample core banking application designed to provide a hands-on learning experience in developing robust and feature-rich applications using Spring Boot. This project aims to incorporate various tools, technologies, and best practices commonly used in enterprise-level Java development, making it an ideal practice ground for both beginners and experienced developers.

### Project Goals

The primary goal of CoreBankX is to serve as a comprehensive learning resource for mastering Spring Boot and related technologies, while also covering essential concepts and practices frequently encountered in real-world projects. By working on this project, you will:

- Gain practical experience in building a full-fledged web application from scratch.
- Learn how to implement user authentication, authorization, and session management.
- Understand the principles of CRUD (Create, Read, Update, Delete) operations and layered architecture.
- Explore advanced topics such as multithreading, transaction management, and logging.
- Practice writing unit tests, integration tests, and automated testing using popular frameworks.
- Familiarize yourself with Continuous Integration/Continuous Deployment (CI/CD) pipelines.
- Acquire hands-on experience in database interactions using both in-memory databases and relational databases like Oracle.

### Why CoreBankX?

CoreBankX is designed with simplicity and comprehensiveness in mind. By focusing on a core banking scenario, we aim to provide a familiar context for developers to apply their skills and explore various features of Spring Boot in a structured manner. Whether you're a Java enthusiast looking to deepen your understanding of Spring Boot or a job seeker preparing for interviews, CoreBankX offers a practical and engaging learning journey.

### Getting Started

To get started with CoreBankX, please refer to the project documentation and follow the setup instructions provided in the README.md file. We encourage you to explore the codebase, experiment with different features, and contribute to the project by sharing your insights and improvements.

Happy coding, and welcome aboard the CoreBankX journey!






### Detailed Outline

1. **Folder Structure**
   - **Explanation**: Organizing project folders in a way that separates concerns and enhances maintainability.
   - **Structure**:
     - `controller`: Contains the REST controllers.
     - `service`: Contains the business logic.
     - `repository`: Contains database access logic.
     - `model`: Contains data models or entities.
     - `config`: Contains configuration files.

2. **Layered Programming (MVC)**
   - **Explanation**: Model-View-Controller architecture separates the application into three interconnected components.
   - **Components**:
     - **Model**: Represents the data structure.
     - **View**: Represents the user interface.
     - **Controller**: Handles user input and interacts with the model to render the final output.

3. **Annotations and Concepts**
   - **Multithreading**:
     - **Explanation**: Parallel execution of tasks to improve performance.
     - **Key Annotations**: `@Async`, `@Scheduled`
     - **Implementation**: Using `ExecutorService`, `CompletableFuture`
   - **Transactional**:
     - **Explanation**: Ensures that a series of operations either all succeed or all fail.
     - **Key Annotation**: `@Transactional`
   - **User Authentication & Spring Security**:
     - **Explanation**: Protecting resources from unauthorized access.
     - **Components**: Authentication providers, User details service, Filters
     - **Key Annotations**: `@EnableWebSecurity`, `@PreAuthorize`
   - **Rollback & Savepoints**:
     - **Explanation**: Reverting a transaction to a previous state in case of an error.
     - **Key Annotation**: `@Transactional` with `rollbackFor`, `savepoint`
   - **Isolation Levels**:
     - **Explanation**: Controls the visibility of changes made by one transaction to other concurrent transactions.
     - **Key Annotation**: `@Transactional(isolation = Isolation.SERIALIZABLE)`
   - **Logging**:
     - **Explanation**: Recording application behavior for monitoring and debugging.
     - **Frameworks**: SLF4J, Logback
   - **CI/CD**:
     - **Explanation**: Automating the software release process.
     - **Tools**: Jenkins, Travis CI, GitLab CI

4. **Writing Tests**
   - **Unit Tests**:
     - **Explanation**: Testing individual components in isolation.
     - **Tools**: JUnit, Mockito
   - **Integration Tests**:
     - **Explanation**: Testing the interaction between different components.
     - **Tools**: Spring Boot Test, TestRestTemplate
   - **Mocking Endpoints**:
     - **Explanation**: Simulating external services for testing.
     - **Tools**: Mockito, Spring MockMVC
   - **Automated Testing**:
     - **Explanation**: Integrating tests into CI/CD pipelines.
     - **Tools**: Jenkins, Travis CI

### Core Banking System Scenario

**Scenario**: Develop a core banking system with the following features:
- User registration and authentication
- Session management
- CRUD operations for accounts and transactions
- Testing endpoints using Postman
- Using JWT for authentication
- Incorporating streams and new Java 20 features
- Using in-memory database for development and Oracle for production with stored procedures

#### Detailed Implementation

1. **User Registration and Authentication**
   - **Registration**: Implement a REST API for user registration.
     - **Model**: User
     - **Controller**: `UserController` with `register` endpoint
     - **Service**: `UserService` handling business logic
     - **Repository**: `UserRepository` for database access
   - **Authentication**: Implement JWT for user authentication.
     - **Controller**: `AuthController` with `login` endpoint
     - **Service**: `AuthService` to generate JWT tokens
     - **Configuration**: `SecurityConfig` to configure Spring Security

2. **Session Management**
   - **Explanation**: Using JWT to manage user sessions.
   - **Implementation**: Store JWT tokens client-side, validate on each request.

3. **CRUD Operations**
   - **Accounts**: Create, Read, Update, Delete bank accounts.
     - **Model**: Account
     - **Controller**: `AccountController`
     - **Service**: `AccountService`
     - **Repository**: `AccountRepository`
   - **Transactions**: Create, Read, Update, Delete transactions.
     - **Model**: Transaction
     - **Controller**: `TransactionController`
     - **Service**: `TransactionService`
     - **Repository**: `TransactionRepository`

4. **Writing Tests**
   - **Controller Layer Tests**: Using MockMVC to test API endpoints.
   - **Service Layer Tests**: Using Mockito to test business logic.
   - **Repository Layer Tests**: Using H2 in-memory database for testing.

5. **Testing Using Postman**
   - **Create Collections**: Organize requests for user registration, login, account management, and transactions.
   - **Automate Tests**: Use Postman's testing features to write test scripts.

6. **Implementing JWT Authentication**
   - **Dependencies**: Include `spring-boot-starter-security`, `jjwt`
   - **Configuration**: Set up `SecurityConfig` to handle JWT tokens.
   - **Endpoints**: Secure API endpoints using `@PreAuthorize`.

7. **Using Streams and Java 20 Features**
   - **Streams**: Use Java Streams for efficient data processing.
   - **New Java 20 Features**: Incorporate features like pattern matching for better code clarity.

8. **Database Setup**
   - **Development**: Use H2 in-memory database for development.
   - **Production**: Switch to Oracle, practice using stored procedures for complex queries.

#### Example Code Snippets

**User Registration Controller**:
```java
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User newUser = userService.registerUser(user);
        return ResponseEntity.ok(newUser);
    }
}
```

**UserService**:
```java
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        // Additional logic like password encoding
        return userRepository.save(user);
    }
}
```

**UserRepository**:
```java
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
```

**JWT Configuration**:
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests().antMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
```

## Dependencies

IntelliJ IDEA makes it easy to bootstrap a Spring Boot project using its Spring Initializr integration. When creating a new Spring Boot project in IntelliJ IDEA, you'll be prompted to specify dependencies through the Spring Initializr interface. Here's a breakdown of some common dependencies you might want to consider adding and why:

1. **Spring Web**: This dependency is essential for building web applications with Spring MVC. It includes features like HTTP message converters, annotation-based controllers, and RESTful web services.

2. **Spring Data JPA**: If your application needs to interact with a relational database, Spring Data JPA provides a powerful and convenient way to do so. It abstracts away much of the boilerplate code associated with database operations, allowing you to work with entities and repositories.

3. **Spring Security**: For securing your application with features like authentication, authorization, and protection against common security vulnerabilities, Spring Security is indispensable. It integrates seamlessly with Spring Boot, offering robust security configurations out of the box.

4. **Spring Boot Actuator**: Actuator provides production-ready features to help you monitor and manage your application. It exposes various endpoints for gathering metrics, monitoring health, and managing beans, among other things.

5. **Spring Boot DevTools**: DevTools enhance the development experience by providing features like automatic application restarts, live reloading, and enhanced error reporting. These tools can significantly improve your productivity during development.

6. **Spring Boot Test**: This dependency includes the necessary tools for testing Spring Boot applications, such as JUnit, Mockito, and Spring Test. It enables you to write and run tests for your application's components, ensuring its reliability and correctness.

7. **Lombok**: Lombok is a library that helps reduce boilerplate code in Java applications by providing annotations like `@Getter`, `@Setter`, and `@Builder`. It can streamline your codebase and make it more readable and maintainable.

8. **MapStruct**: If your project involves mapping between DTOs (Data Transfer Objects) and entities, MapStruct can be a valuable addition. It generates mapping code at compile time, offering type safety and performance benefits compared to traditional mapping approaches.

9. **H2 Database (or other database drivers)**: If you're using an embedded database like H2 for development and testing purposes, you'll need to include the corresponding database driver dependency. For production, you'll replace this with the driver for your chosen database (e.g., MySQL, PostgreSQL, Oracle).

These are just a few examples of common dependencies you might want to include in your Spring Boot project. The specific dependencies you choose will depend on the requirements of your application. It's a good idea to start with the essentials and add additional dependencies as needed during the development process.

