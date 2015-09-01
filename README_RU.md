Доступно: [EN](https://github.com/Igor-ua/Academy/blob/master/README.md) [RU](https://github.com/Igor-ua/Academy/blob/master/README_RU.md)

# Академия

Проект "Академия" был разработан мной в ходе обучения языку **Java**.
"Академия" является системой учёта данных для учебного заведения (база данных с интерфейсом доступа).
<br>Данная система предоставляет ведение учёта (CRUD - создание/чтение/обновление/удаление + поиск) по таким критериям, как: студенты, группы, учители, оценки, расписание и др.
<br>Spring-ветка проекта развёрнута(openshift.com VPS/Linux) и доступна по адресу: [http://academy-spring.ddns.net/](http://academy-spring.ddns.net/)
<br>Реализация данного проекта содержит в себе такие технологии как:

<p><strong>Взаимодействие с БД / ORM</strong></p>
<ul>
  <li>JDBC</li>
  <li>Hibernate</li>
  <li>Spring DATA JPA</li>
</ul>

<p><strong>Сборщики проектов</strong></p>
<ul>
  <li>Maven</li>
</ul>

<p><strong>Базы данных</strong></p>
<ul>
  <li>MySQL</li>
  <li>H2</li>
</ul>

<p><strong>Логирование</strong></p>
<ul>
  <li>Log4j</li>
  <li>Log4j2</li>
  <li>Slf4j</li>
</ul>

<p><strong>Технологии Spring</strong></p>
<ul>
  <li>Spring Boot</li>
  <li>Spring MVC</li>
  <li>Spring Security</li>
  <li>Spring DATA REST</li>
</ul>

<p><strong>Пользовательские интерфейсы</strong></p>
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

<p><strong>Дополнительные библиотеки</strong></p>
<ul>
  <li>Google guava</li>
  <li>Apache commons</li>
</ul>


Части проекта, которые <i>были реализованы</i>, но по различным причинам <i>не были включены</i> в данную сборку:

<ul>
  <li>Swing interface</li>
  <li>Servlets, JSP</li>
  <li>Spring DATA MongoDB</li>
</ul>

##Запуск

Проект разделён на 3 части: **JDBC**, **Hibernate**, **Spring**. Каждая его часть запускается отдельно через свой `main` класс, каждый из которых находится в пакете `org.mydomain.academy.main`
 
 + `AcademyJDBCConsole.class`
 + `AcademyHibernateConsole.class`
 + `AcademySpringBootWeb.class`
 
Для каждой из частей существует возможность настройки параметров запуска через соответствующий property / class файл: 

 + `JDBC.properties` - JDBC settings
 + `HibernateSettings.class (package org.mydomain.academy.db.utils)` - Hibernate settings
 + `Application.properties` - SpringBoot settings

**JDBC** реализация зависит от внешней базы данных. Настройки для подключения к базе данных необходимо перед запуском прописать(поменять) в файле `jdbc.properties`.<br>
**Hibernate** и **SpringBoot** реализации самостоятельно при своём старте запускают TCP сервер `org.mydomain.academy.db.H2Server` базы данных H2. Настройки подключения к базе данных и другие внутренние настройки находятся в соответствующих файлах.<br>
**JDBC** и **Hibernate** реализации используют консольный интерфейс, в то время как **SpringBoot** использует WEB-интерфейс, который по умолчанию будет доступен по адресу [http://localhost:8181/](http://localhost:8181/) или [http://127.0.0.1:8181/](http://127.0.0.1:8181/)

##Диаграмма базы данных
![DB diagram](https://raw.githubusercontent.com/Igor-ua/Academy/master/Academy_diagram.png "Academy db diagram")