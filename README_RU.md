Available: [EN](https://github.com/Igor-ua/Academy/blob/master/README.md) [RU](https://github.com/Igor-ua/Academy/blob/master/README_RU.md)

# Academy (readme is not finished yet)

Проект "Академия" был разработан мной в ходе обучения языку **Java**.
"Академия" является системой учёта данных для учебного заведения (база данных с интерфейсом доступа).
<br>Данная система предоставляет ведение учёта по таким критериям, как: студенты, группы, учители, оценки, расписание и др. 
<br>Реализация данного проекта содержит в себе такие технологии как:

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


Части проекта, которые <i>были реализованы</i>, но по различным причинам <i>не были включены</i> в данную сборку:

<ul>
  <li>Swing interface</li>
  <li>Servlets, JSP</li>
  <li>Spring DATA MongoDB</li>
</ul>

##Запуск

Проект разделён на 3 части: **JDBC**, **Hibernate**, **Spring**. Каждая его часть запускается отдельно через свой `main` класс, каждый из которых находится в пакете `org.mydomain.academy.main`
<ul>
  <li>AcademyJDBCConsole.class</li>
  <li>AcademyHibernateConsole.class</li>
  <li>AcademySpringBootWeb.class</li>
</ul>