Airline booking
---
It' an air ticket reservation system. All it' documentation can be found in the `docs` dir.

Technologies
---
This project was built with the following technologies:

  - [Java - 8](http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html)
  - [Maven - 3.3.9](https://maven.apache.org/download.cgi)
  - [Spring Boot - 1.4.0](http://projects.spring.io/spring-boot/)
  - [Flyway](https://flywaydb.org/)
  - [Spring Cloud - Brixton](http://projects.spring.io/spring-cloud/)
  - [Lombok](https://projectlombok.org/)
  - [H2 - InMemory database](http://www.h2database.com/html/main.html)
  - [Embedded Tomcat](http://tomcat.apache.org/)
  - [JaCoCo](http://www.eclemma.org/jacoco/)
  
Adding Project Lombok Agent
---

This project uses [Project Lombok](http://projectlombok.org/features/index.html)
to generate getters and setters etc. Compiling from the command line this
shouldn't cause any problems, but in an IDE you need to add an agent
to the JVM. Full instructions can be found in the Lombok website. The
sign that you need to do this is a lot of compiler errors to do with
missing methods and fields.  

Build
---
This application will generate an executable jar file, to build and run **ensure you have Java 8 and Maven 3 installed** 
and execute the following commands on the terminal:

```
$ mvn clean package
```

>**Note**: *Make sure you're on project root dir*.

Database
---
To access the database reach `http://$HOST:$PORT/console` from the browser and fill the fields with the following values:

 - Driver Class: `org.h2.Driver`
 - JDBC URL: `jdbc:h2:mem:testdb`
 - User Name: `sa`
 - Password: *empty value*
 
>**Note**: There will be two service with available databases: authserver and booking-service. Make sure you'e reaching the correct $HOST and $PORT