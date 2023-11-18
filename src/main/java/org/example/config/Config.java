package org.example.config;

import org.example.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class Config {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            try{
                Configuration configuration = new Configuration()
                        .addAnnotatedClass(Car.class)
                        .addAnnotatedClass(PassengerCar.class)
                        .addAnnotatedClass(Truck.class)
                        .addAnnotatedClass(Part.class)
                        .addAnnotatedClass(Owner.class);
                Properties settings = new Properties();
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
                settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/hw2test");
                settings.put(Environment.DRIVER, "org.postgresql.Driver");
                settings.put(Environment.USER, "postgres");
                settings.put(Environment.PASS, "root");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.HBM2DDL_AUTO, "update");
                configuration.setProperties(settings);

//                configuration.addAnnotatedClass(Car.class);


                ServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory( serviceRegistry);
                return sessionFactory;
            }catch (Exception e) {
                e.printStackTrace();
            }
        }return sessionFactory;
    }
}
