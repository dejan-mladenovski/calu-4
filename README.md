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
  public String sayHello() {
    return "view_say_hello";
  }
}
```