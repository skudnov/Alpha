package lib;

import java.util.*;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;



public class DBCrud implements ICrud {

    private Map<String, String> alpha = new HashMap<>();
    private List<String> ListFile = new ArrayList<>();
    private static SessionFactory sessionFactory;
    private DriverManagerDataSource dataSource;


    @Override
    public String read()  {

        Configuration configuration = new Configuration();
        sessionFactory =

        return  "";

    }

    @Override
    public String create(String key, String value, int i) {
        return null;
    }

    @Override
    public String delete(String key, int i) {
        return null;
    }

    @Override
    public Map<String, String> getAlphabet() {
        return new HashMap<>(alpha);
    }

    @Override
    public String getValue(String key) {
        return null;
    }

    @Override
    public List<String> getAlphabetNames() {
        return ListFile;
    }


    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {

                Configuration configuration = new Configuration();
                configuration.addAnnotatedClass(Alphabet.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }


}
