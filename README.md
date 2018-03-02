# calu-4
Demo application for CodeApp LevelUp 4

# Setting up a Spring project
There are two ways to set up a Spring Boot 2 application project.
1. [Spring Initializr](https://start.spring.io/) (cool way to select what you need and Spring will do the rest)
2. The old-fashioned way, which is building the POM manually, getting the same result as the Spring Initializr

Either way, we need to do several things to create a Spring Boot application.
1. Create a Maven project
2. Add Spring as parent
3. Import Spring Boot dependencies
4. Create a starting point for Spring
5. Run the application

### Create a Maven project
Open your favourite editor and create a new project (Maven or blank project). If you create a blank project,
create a `pom.xml` file and add the `<project>` tag with some Maven XML namespaces. Also, keeping to the
maven file structure, create the following folder path: `src/main/java`.

### Add Spring as parent
Spring has created some dependencies for us using the `starter` modules. To ease the usage, we need to set our project
to use Spring as a parent dependency. To do this add the `spring-boot-starter-parent` dependency as the project's parent.

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.0.0.RC2</version>
</parent>
```
We are using Spring Boot **2.0.0.RC2** version, which is the latest at this time. But you can use any Spring Boot 
version, but keep in mind that some functionalites are not available in version 1.x. 

### Import Spring Boot dependencies
By adding Spring Boot as parent we didn't do much, just making the Spring dependencies available. To use them,
we need to import them in our project. For now we will need `web`, `thymeleaf` and `security`, so we will add them.
We will also need `lombok`, to help us generating getters/setters, some constructors.
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>
```

### Create a starting point for Spring
In a standard version of Spring, we would usually build the project as a `war` package, which then it would
be deployed on a web container, such as Tomcat, Jetty, JBoss, or any other Java container. But, Spring Boot
offers running the application as an executable JAR. This JAR contains embedded tomcat instance. But to use it
we need to define a starting point for Spring. To do this we need to simply create a class that will contain 
a `main` method, and call the `SpringApplication#run` method.

```java
@SpringBootApplication
public class Calu4Demo {
  public static void main(String[] args) {
    SpringApplication.run(Calu4Demo.class, args);
  }
}
```
Don't forget the `@SpringBootApplication` annotation.

That's it! Now you can run the application from your favourite editor and you will see a log that will say that the 
application is running on a http port. 
```
 .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::            (v2.0.0.RC2)

2018-02-25 20:41:51.729  INFO 6364 --- [           main] com.netcetera.calu4.demo.Calu4Demo       : Starting Calu4Demo on NB5214 with PID 6364 (C:\data\private\calu-4\target\classes started by dmladeno in C:\data\private\calu-4)
2018-02-25 20:41:51.733  INFO 6364 --- [           main] com.netcetera.calu4.demo.Calu4Demo       : No active profile set, falling back to default profiles: default
2018-02-25 20:41:51.846  INFO 6364 --- [           main] ConfigServletWebServerApplicationContext : Refreshing org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@3b2cf7ab: startup date [Sun Feb 25 20:41:51 CET 2018]; root of context hierarchy
2018-02-25 20:41:53.153  INFO 6364 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
...
2018-02-25 20:41:54.039  INFO 6364 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2018-02-25 20:41:54.039  INFO 6364 --- [           main] com.netcetera.calu4.demo.Calu4Demo       : Started Calu4Demo in 2.826 seconds (JVM running for 3.93)
```
# Creating controllers and endpoint handlers
Controllers in Spring provide access to the application behavior that you typically define through services.
Controllers interpret the user input, process the request and return a model that is represented to the user through a view. 

To define a controller, just annotate a class with the `@Controller` annotation. But this is not enough. You will need to define
a method that will do something when some URL is called. This is called routing. To define routes you need to create a method
that will be executed then the URL is called. Next annotate the method with `@RequestMapping` and define a value
to which endpoint it is mapped.

```java
@Controller
public class HomeController {

  @RequestMapping(value = "/hello")
    public String sayHello(Model model) {
      model.addAttribute("message", "Hey there! Do you like Spring?");
      return "hello";
    }
}
```

# Adding views and view engines
Setting up view engines has never been easier with Spring Boot. With a standard Spring application, you will need
to add the view engine as a dependency, create a bean that will return the template engine instance, add settings
for the engine... With Spring Boot, you just add the `spring-boot-starter-<engine>` dependency and Spring will do
the rest. This package contains some default autoconfiguration for the engine and it will create an instance with some
standard configuration, so the developer just adds the dependency and follows the engine's standards for creating views.

For this demo we will use **Thymeleaf** (`spring-boot-starter-thymeleaf`).
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

### Creating a Thymeleaf template
After adding the dependency, create the views in `src/main/resources/templates` and Thymeleaf will do the rest.

Here is the **hello.html** template
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
  <meta charset="UTF-8">
  <title>Say hello</title>
</head>
<body>
  <div th:text="${message}" />
</body>
</html>
```

# Implement JPA and databases
The best way to use databases in Spring is to use JPA (Java Persistence API). To implement this, we need to add JPA 
dependency, and add a database connector. For the purposes of the demo we will use H2, but for a test or production
environment, it is recommended to use some other database provider (MySQL, MS SQL, PostgreSQL,...). H2 is a compact
database, that can be used as an in-memory or file database, and is commonly used for testing or local development.

### Add dependencies
We will need to add two dependencies: **H2** (`com.h2database:h2`, already provided with Spring Boot), and 
**JPA** (along with the Spring autoconfiguration: `com.springframework.boot:spring-boot-starter-data-jpa`).
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```
### Configure the database connection
Next we need to tell JPA to configure the database connection. This means specifying a type of the database, 
or database driver, the connection URL to the database, and optionally, some other configuration for JPA and Hibernate.
```properties
spring.datasource.url:jdbc:h2:tcp:localhost/./database/sampledb;AUTO_SERVER=TRUE

# Use H2 DB Driver
spring.datasource.driverClassName:org.h2.Driver

#spring.jpa.hibernate.ddl-auto=update
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.hibernate.format_sql=true
```
### Create the table models
Cool thing about JPA is that it can create the tables for us, just by specifying some parameters to the model. But we 
will need to create the model. A model is simply a representation of a SQL table as a Java class. As an example, here is
a model for a `User`.
```java
@Entity
public class User {
  @Id
  @GeneratedValue
  private Long id;
  private String username;
  private String password;
}
```
### Create the repository
The model is a representation of the SQL table, but what about fetching data? This can be done by 
creating a repository. This repository will create a connection to the database, and perform operations on the data
(fetch data, add new rows, delete rows, update rows). JPA offers some predefined methods, but the cool thing
is that by using specific keywords in the naming of the methods, we can create custom queries. For example, 
if we want to get the created users ordered by created date, we can create a method called
`findAllOrderByDateCreated` and when called it will execute the query `SELECT * FROM Users ORDER BY dateCreated`. 
The `JpaRepository` interface is configured with two parameters (`<User, Long>`). The first one is to specify to which
model/table is this repository connected, and the second one is to specify the type of the primary key.
```java
public interface UsersRepository extends JpaRepository<User, Long> {
  User findByName(String username);
}
```
# Spring Security
Some applications require for the users to be authenticated if they can manipulate with the apoplication's content.
The most recognisable way to implement security is implementing authentication with username/password. You can use 
`spring-boot-starter-security`, but that can be left for you to implement. Now, we will implement simple authentication 
check, to check if the username and password can be found in the database. If they exist, then write down the username 
in a session property. We alreay have a repository to fetch the data, we will just need to implement a method that will 
check it.

### Check if user can be authenticated
To check if a user can be authenticated, find the user in the database, set the session property if exists and redirect 
to a page.
```java
@RequestMapping(value = "login", method = RequestMethod.POST)
  public String doLogin(@ModelAttribute User user, HttpSession session /*, ModelMap map*/) {
    if (user != null && user.getUsername() != null) {
      // perform check
      User foundUser = userRepository.findByName(user.getUsername());
      if (foundfoundUser.getPassword().equals(user.getPassword())) {
        session.setAttribute("loggedInUser", foundUser);
        return "success";
      }
    }
    // user failed to authenticate
    return "forbidden";
  }
```
### Check if a user is logged in before executing the endpoint
To check if a user is logged in, just get the user from the session. If it is null, then the user is not
logged in, and should be redirected to the login page. 
```java
if (httpSession.getAttribute("loggedInUser") != null) {
  // user is logged in, perform intended function
} else {
  // user is not logged in, redirect to login page
}
```