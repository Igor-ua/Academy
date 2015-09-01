Available: [EN](https://github.com/Igor-ua/Academy/blob/master/README.md) [RU](https://github.com/Igor-ua/Academy/blob/master/README_RU.md)

# Academy

The Project "Academy" has been developed by me in the course of learning the **Java** language.
The "Academy" is an accounting data system for the educational institutions (database with an access interface).
<br>This system provides management (CRUD - create/read/update/delete + search) on such criteria as: students, groups, teachers, marks, schedules, etc.
<br>Spring-branch of this project is deployed (openshift.com VPS/Linux) and available here: [http://academy-spring.ddns.net/](http://academy-spring.ddns.net/)
<br>This project contains such technologies as:

<p><strong>DB connectivity / ORM</strong></p>
<ul>
  <li>JDBC</li>
  <li>Hibernate</li>
  <li>Spring DATA JPA</li>
</ul>

<p><strong>Build management tools</strong></p>
<ul>
  <li>Maven</li>
</ul>

<p><strong>Databases</strong></p>
<ul>
  <li>MySQL</li>
  <li>H2</li>
</ul>

<p><strong>Logging</strong></p>
<ul>
  <li>Log4j</li>
  <li>Log4j2</li>
  <li>Slf4j</li>
</ul>

<p><strong>Spring technologies</strong></p>
<ul>
  <li>Spring Boot</li>
  <li>Spring MVC</li>
  <li>Spring Security</li>
  <li>Spring DATA REST</li>
</ul>

<p><strong>User interfaces</strong></p>
<ul>
  <li>Console interface</li>
  <li>WEB interface</li>
  <ul>
       <li>Thymeleaf</li>
       <li>HTML</li>
       <li>CSS</li>
       <li>JavaScript</li>
       <li>jQuery</li>
       <li>BootStrap</li>
  </ul>
</ul>

<p><strong>Additional libraries</strong></p>
<ul>
  <li>Google guava</li>
  <li>Apache commons</li>
</ul>

Parts of the project that <i>have been implemented</i>, but for various reasons <i>have not been included</i> in this assembly:

<ul>
  <li>Swing interface</li>
  <li>Servlets, JSP</li>
  <li>Spring DATA MongoDB</li>
</ul>

##Getting Started

This project is divided into 3 parts: **JDBC**, **Hibernate**, **Spring**. Each part of the project is being launched separately through it's own `main` class that is in `org.mydomain.academy.main` package
 
 + `AcademyJDBCConsole.class`
 + `AcademyHibernateConsole.class`
 + `AcademySpringBootWeb.class`
 
Each of the parts has it's own configuration that is in it's own property / class file:

 + `JDBC.properties` - JDBC settings
 + `HibernateSettings.class (package org.mydomain.academy.db.utils)` - Hibernate settings
 + `Application.properties` - SpringBoot settings

**JDBC** implementation depends on an external database. Database connection settings should be changed(updated) before launching an application in `jdbc.properties` file.<br>
**Hibernate** and **SpringBoot** implementations run H2 database TCP server `org.mydomain.academy.db.H2Server` independently at start. Database connection settings and other internal settings are in the corresponding files.<br>
**JDBC** and **Hibernate** implementations use console interface when **SpringBoot** uses WEB-interface that is available on [http://localhost:8181/](http://localhost:8181/) или [http://127.0.0.1:8181/](http://127.0.0.1:8181/) by default.

##Database diagram
![DB diagram](https://raw.githubusercontent.com/Igor-ua/Academy/master/Academy_diagram.png "Academy db diagram")