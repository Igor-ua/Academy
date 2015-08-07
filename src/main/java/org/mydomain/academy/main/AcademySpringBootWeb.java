package org.mydomain.academy.main;

import org.mydomain.academy.SpringBoot.config.PropertyManager;
import org.mydomain.academy.db.utils.content.JPAContentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import static org.mydomain.academy.db.H2Server.H2ConsoleServer.startServer;

@SpringBootApplication
@EntityScan(basePackages = "org.mydomain.academy/db/entities")
@EnableJpaRepositories(basePackages = "org.mydomain.academy/db/DAO/JPAInterface")
@ComponentScan(basePackages = "org.mydomain.academy")

public class AcademySpringBootWeb extends SpringBootServletInitializer implements CommandLineRunner {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		PropertyManager pm = new PropertyManager();
//		startServer(pm.getPort());
		startServer(64000);
		return application.sources(AcademySpringBootWeb.class);
	}

	public static void main(String[] args) {
		PropertyManager pm = new PropertyManager();
		if (startServer(pm.getPort())) {
			ApplicationContext ctx = SpringApplication.run(AcademySpringBootWeb.class, args);
		}
	}

	@Override
	public void run(String... strings) throws Exception {

		//fills db with temp content
		contentManager.addContent();
	}

	@Autowired
	JPAContentManager contentManager;
}