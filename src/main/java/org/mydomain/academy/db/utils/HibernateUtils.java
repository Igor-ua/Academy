package org.mydomain.academy.db.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtils implements AutoCloseable {

	private SessionFactory sessionFactory;
	private Session session;
	private Configuration configuration;
	private StandardServiceRegistryBuilder builder;
	private HibernateSettings hs;

	public HibernateUtils() {
		hs = new HibernateSettings();
		configuration = new Configuration();
		hs.configure(configuration);
		hs.addMappings(configuration);
		builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
		sessionFactory = configuration.buildSessionFactory(builder.build());
	}

	public Session getSession() {
		if ((session == null) || (!session.isOpen())) {
			session = sessionFactory.openSession();
		}
		return session;
	}

	@Override
	public void close() {
		sessionFactory.close();
		session = null;
	}
}