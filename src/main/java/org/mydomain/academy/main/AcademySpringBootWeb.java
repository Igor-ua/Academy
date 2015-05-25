package org.mydomain.academy.main;

import org.mydomain.academy.db.utils.content.JPAContentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EntityScan(basePackages = "org.mydomain.academy/db/entities")
@EnableJpaRepositories(basePackages = "org.mydomain.academy/db/DAO/JPAInterface")
@ComponentScan(basePackages = "org.mydomain.academy")

public class AcademySpringBootWeb extends WebMvcConfigurerAdapter implements CommandLineRunner {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(AcademySpringBootWeb.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

		//fills db with temp content
		contentManager.addContent();

		System.out.println("[!]   Spring Boot is ready");
	}

	@Autowired
	JPAContentManager contentManager;
}
