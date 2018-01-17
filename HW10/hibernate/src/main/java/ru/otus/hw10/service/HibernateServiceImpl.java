package ru.otus.hw10.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.otus.hw10.data.AddressDataSet;
import ru.otus.hw10.data.UserDataSet;
import ru.otus.hw10.dao.UserDao;

import java.util.List;
import java.util.function.Function;

public class HibernateServiceImpl implements DBService {
    private final SessionFactory sessionFactory;

    public HibernateServiceImpl() {
        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(UserDataSet.class);
        configuration.addAnnotatedClass(AddressDataSet.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/otus" + "?serverTimezone=UTC");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "root");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        configuration.setProperty("hibernate.connection.useSSL", "false");
        configuration.setProperty("hibernate.enable_lazy_load_no_trans", "true");

        sessionFactory = createSessionFactory(configuration);
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Override
    public void save(UserDataSet dataSet) {
        try (Session session = sessionFactory.openSession()) {
            UserDao dao = new UserDao(session);
            dao.save(dataSet);
        }
    }

    @Override
    public UserDataSet load(long id) {
        return runInSession(session -> {
            UserDao dao = new UserDao(session);
            return dao.load(id);
        });
    }

    @Override
    public List<UserDataSet> readAll() {
        return runInSession(session -> {
            UserDao dao = new UserDao(session);
            return dao.readAll();
        });
    }

    @Override
    public int getAmount() {
        return readAll().size();
    }

    private <R> R runInSession(Function<Session, R> function) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            R result = function.apply(session);
            transaction.commit();
            return result;
        }
    }

    @Override
    public void shutdown() {
        sessionFactory.close();
    }
}
