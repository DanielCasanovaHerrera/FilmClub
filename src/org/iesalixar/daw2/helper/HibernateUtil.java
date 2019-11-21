package org.iesalixar.daw2.helper;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.service.ServiceRegistry;
import org.iesalixar.daw2.model.Category;
import org.iesalixar.daw2.model.User;
import org.iesalixar.daw2.model.Type;
import org.iesalixar.daw2.model.Product;
import org.iesalixar.daw2.model.Rent;
import org.iesalixar.daw2.model.Product_Category;
import org.iesalixar.daw2.model.Hystoric;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	public static synchronized void buildSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();
				configuration.configure();
				configuration.addAnnotatedClass(User.class);
				configuration.addAnnotatedClass(Category.class);
				configuration.addAnnotatedClass(Type.class);
				configuration.addAnnotatedClass(Product.class);
				configuration.addAnnotatedClass(Rent.class);
				configuration.addAnnotatedClass(Product_Category.class);
				configuration.addAnnotatedClass(Hystoric.class);
				configuration.setProperty("hibernate.current_session_context_class", "thread");
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void openSessionAndBindToThread() {
		Session session = sessionFactory.openSession();
		ThreadLocalSessionContext.bind(session);
	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			buildSessionFactory();
		}
		return sessionFactory;
	}

	public static void closeSessionAndUnbindFromThread() {
		Session session = ThreadLocalSessionContext.unbind(sessionFactory);
		if (session != null) {
			session.close();
		}
	}

	public static void closeSessionFactory() {
		if ((sessionFactory != null) && (sessionFactory.isClosed() == false)) {
			sessionFactory.close();
		}
	}

}

