package org.mydomain.academy.main;

import org.mydomain.academy.db.utils.content.JPAContentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration(exclude = ErrorMvcAutoConfiguration.class)
@EntityScan(basePackages = "org.mydomain.academy/db/entities")
@EnableJpaRepositories(basePackages = "org.mydomain.academy/db/DAO/JPAInterface")
@ComponentScan(basePackages = "org.mydomain.academy")

public class AcademySpringBootWeb extends SpringBootServletInitializer implements CommandLineRunner {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AcademySpringBootWeb.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(AcademySpringBootWeb.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		contentManager.addContent();
	}

	@Autowired
	JPAContentManager contentManager;
}