package lib;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.orm.hibernate5.HibernateTransactionManager;

class HibernateUtil {
    private static final SessionFactory sessionFactory= configureSessionFactory();

    public HibernateTransactionManager transactionManager(SessionFactory s) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);
        return txManager;
    }

    private static SessionFactory configureSessionFactory()
            throws HibernateException {
        Configuration configuration = new Configuration()
                .setProperty( "hibernate.connection.driver_class",
                        "org.postgresql.Driver" )
                .setProperty( "hibernate.connection.url",
                        "jdbc:postgresql://localhost:5432/AlphaBase" )
                .setProperty( "hibernate.connection.username",
                        "postgres" )
                .setProperty( "hibernate.connection.password",
                        "213141a" )
                .setProperty( "hibernate.connection.pool_size", "1" )
                .setProperty( "hibernate.connection.autocommit", "false" )
                .setProperty( "hibernate.cache.provider_class",
                        "org.hibernate.cache.NoCacheProvider" )
                .setProperty( "hibernate.cache.use_second_level_cache",
                        "false" )
                .setProperty( "hibernate.cache.use_query_cache", "false" )
                .setProperty( "hibernate.dialect",
                        "org.hibernate.dialect.PostgreSQLDialect" )
                .setProperty( "hibernate.show_sql","true" )
                .setProperty( "hibernate.current_session_context_class",
                        "thread" )
                .setProperty("hibernate.hbm2ddl.auto", "update")
                .addAnnotatedClass(KeyEssence.class)
                .addAnnotatedClass(ValueEssence.class)
                ;
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
