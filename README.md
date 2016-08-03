Airline Booking Flights
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
All backend services have a built-in documentation that can be accessed by the uri `/docs/api.html`.  

    - search-service
    - customer-service
    - booking-service
    - auth-service - `missing docs`
    
Distributed configuration
---
This project is built under microservices architectural styles and use a distributed configuration server to provide applications 
config by environment. Those configurations will be storage in a git repository like [this](https://github.com/marcosbarbero/airline-config-repo/blob/master/application.yml) 
and should be pointed to the file in `config-service\src\main\resources\bootstrap.yml` on property:

```
spring.cloud.config:
  server:
    git:
      uri: https://github.com/marcosbarbero/airline-config-repo.git
```

Any additional information can be found on it's [official docs](http://cloud.spring.io/spring-cloud-config/spring-cloud-config.html).  
You can find a config-repo sample files under the dir `docs\config-repo`.  

>**Note**:It's not mandatory to use the `configserver` to run this project. You can change manually each project configuration under in the
 file `src\main\resources\application.yml`
    
Not done in this project
---

    - Customers interface
    - Back office inventory system
    - Back office API
    - Staff team interface
    - Email alerts
    

Issues faced
---
The major issue here was the time frame proposed to complete this challenge with a good quality. Once I figured out I could not 
complete that I've focused to delivery the backend API, so any developer can build it's interface in any spec or device.

Assumptions
---
I assumed that this system should be made as an API once one of it's non-functional requirements is to the core
must be ready to serve mobile apps in future without any modification. Taking that in count I've also built it securely
using [OAuth2](http://oauth.net/2/) so any kinda of `client` can consume that without a major issues.  
Once the system core must be ready for mobile apps and single page apps I choose to make it under a microservices architectural style
to ensure it's scalability and high availability.  
For last but not least, I assumed also that the inventory system was already built.

Run
---
This section will cover how to run the services by hands in a local machine. I could not complete the cloud deployment doc requirement.    
After the project *build* you can run this project with a simple `java -jar` in the following sequence.

1. config-service
```
$ java -jar target/config-server.jar
```

2. discovery-service
```
$ java -jar target/discovery-service.jar
```

3. gateway-service
```
$ java -jar target/gateway-service.jar
```

4. auth-service
```
$ java -jar target/auth-service.jar
```

5. booking-service
```
$ java -jar target/booking-service.jar
```

6. customer-service
```
$ java -jar target/customer-service.jar
```

7. search-service
```
$ java -jar target/search-service.jar
```

Once all projects are running you can check it's list on `Eureka's dashboard` in http://localhost:8761/, the application names will become
 a route in the `gateway-service` that's running in port `8080`. Now you can reach any service documentation just reaching the following URIs.
 
  - http://localhost:8080/bookings/docs/api.html
  - http://localhost:8080/customers/docs/api.html
  - http://localhost:8080/search/docs/api.html

To consume the APIs you will need to generate an `access_token` and add it to the request header.
The default clientId is `booking-app` and it' secret is `secret`.  

Sample request:
```
$ curl -u booking-app:secret http://localhost:8080/uaa/oauth/token -d "grant_type=client_credentials"
```


>**Note**: Before run this project remember to configure properly the datasources in the `configserver` or in each project's `application.yml` file.

Feedback
---

It's a good approach to evaluate the architectural knowledge of a candidate because it goes in almost all layers of a
 real application. The bad part here is the time frame established to complete, I think that most of the appliers are currently
 working in any other job, with that in mind the time frame becomes very tight due the project size.