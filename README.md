Airline booking
---
It' an airline ticket reservation system. All it' documentation can be found in the `docs` dir.

Technologies
---
This project was built with the following technologies:

  - [Java - 8](http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html)
  - [Maven - 3.3.9](https://maven.apache.org/download.cgi)
  - [Spring Boot - 1.4.0](http://projects.spring.io/spring-boot/)
  - [Spring Cloud - Brixton](http://projects.spring.io/spring-cloud/)
  - [Lombok](https://projectlombok.org/)
  - [H2 - InMemory database](http://www.h2database.com/html/main.html)
  - [Embedded Tomcat](http://tomcat.apache.org/)
  - [JaCoCo](http://www.eclemma.org/jacoco/)
  - [AngularJS - 1](https://angularjs.org/)
  - [MySQL - 5.7.14](https://www.mysql.com/)
  - [Redis - 3.2.3](http://redis.io/)
  
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
and execute the following command on the terminal:

```
$ mvn clean package
```

>**Note**: *Make sure you're on project root dir*.

Database
---
This project will be using [MySQL - 5.7.14](https://www.mysql.com/) as database. There will be two available schemas: *oauth* and *booking*.
The database scripts is located at dir `docs/database`:

 - oauth_schema.sql
 - oauth_data.sql
 - booking_schema.sql
 - booking_data.sql
 
First execute the scripts with *\_schema.sql*. 

>**Note**: To access the database use your favorite SQL client and configure properly the connection params.
  
Code Coverage
---
This project uses [JaCoCo](http://www.eclemma.org/jacoco/) for code coverage. To check it's result execute:

```
$ mvn clean package
```

When the tests finishes the result you'll see a message like this:
```
[INFO] Airline Booking .................................... SUCCESS [  0.679 s]
[INFO] auth-service ....................................... SUCCESS [ 17.671 s]
[INFO] backoffice-front-ui ................................ SUCCESS [ 13.575 s]
[INFO] booking-service .................................... SUCCESS [ 16.881 s]
[INFO] config-service ..................................... SUCCESS [ 12.269 s]
[INFO] customer-service ................................... SUCCESS [ 12.101 s]
[INFO] discovery-service .................................. SUCCESS [  9.955 s]
[INFO] gateway-service .................................... SUCCESS [ 13.826 s]
[INFO] search-service ..................................... SUCCESS [ 15.255 s]
[INFO] storefront-ui ...................................... SUCCESS [ 13.028 s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

The JaCoCo result will be available on dirs: `$module-name\target\site\jacoco\index.html`.

>**Note**: change the `module-name` to each module name in this project to find out the JaCoCo result.

API Docs
---
This project