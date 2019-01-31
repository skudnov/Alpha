package com.config;

import com.dao.DBCrud;
import com.dao.ICrud;
import com.entity.KeyEssence;
import com.entity.ValueEssence;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@ComponentScan("com")
@EnableTransactionManagement
public class DBConfig {
    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
        lsfb.setDataSource(getDataSource());
        lsfb.setHibernateProperties(hibernateProperties());
        lsfb.setAnnotatedClasses(KeyEssence.class,ValueEssence.class);
        try {
            lsfb.afterPropertiesSet();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lsfb.getObject();
    }

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/AlphaBase");
        dataSource.setUsername("postgres");
        dataSource.setPassword("213141a");
        return dataSource;
    }



//    @Bean
//    public HibernateTemplate hibernateTemplate() {
//        return new HibernateTemplate(sessionFactory());
//    }

    @Bean
    public HibernateTransactionManager hibTransMan(){
        return new HibernateTransactionManager(sessionFactory());
    }
    public Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.show_sql", "false");
        return properties;
    }


    @Bean("DBCrud")
    public ICrud CrudConfig() {
        return new DBCrud();
    }

}
