Airline booking
---
It' an air ticket reservation system. All it' documentation can be found in the `docs` dir.

Technologies
---
This project was built with the following technologies:

  - [Java 8](http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html)
  - [Maven 3.3.9](https://maven.apache.org/download.cgi)
  - [Lombok](https://projectlombok.org/)
  - [H2 - InMemory database](http://www.h2database.com/html/main.html)
  - [Embedded Tomcat](http://tomcat.apache.org/)
  
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