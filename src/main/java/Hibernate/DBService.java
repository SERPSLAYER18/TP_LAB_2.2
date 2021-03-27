package Hibernate;

import Hibernate.DAO.UsersDAO;
import Hibernate.DataSets.UserData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBService {
    public static final String hibernate_show_sql = "true";
    public static final String hibernate_hbm2ddl_auto = "create";
    public static final String JDBC_DRIVER = "org.h2.Driver";
    public static final String DB_URL = "jdbc:h2:./myH2DataBase";
    public static final String USER = "SA";
    public static final String PASS = "";
    private final SessionFactory sessionFactory;

    public DBService() {
        Configuration configuration = getH2Configuration();
        sessionFactory = createSessionFactory(configuration);
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    private Configuration getH2Configuration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UserData.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class", JDBC_DRIVER);
        configuration.setProperty("hibernate.connection.url", DB_URL);
        configuration.setProperty("hibernate.connection.username", USER);
        configuration.setProperty("hibernate.connection.password", PASS);
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
        return configuration;
    }

    public UserData getUser(long id) {
        Session session = sessionFactory.openSession();
        UsersDAO dao = new UsersDAO(session);
        UserData dataSet = dao.get(id);
        session.close();
        return dataSet;

    }

    public long saveUser(UserData user) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UsersDAO dao = new UsersDAO(session);
        long id = dao.insertUser(user);
        transaction.commit();
        session.close();
        return id;

    }

    public void updateUser(UserData oldUser, UserData newUser) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.evict(oldUser);
        oldUser = newUser;
        session.update(oldUser);

        transaction.commit();
        session.close();
    }

    public void deleteUser(UserData user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(user);

        transaction.commit();
        session.close();
    }

    public ArrayList<UserData> getAllUsers() {
        Session session = sessionFactory.openSession();
        UsersDAO dao = new UsersDAO(session);
        ArrayList<UserData> list = dao.getAllUsers();
        session.close();
        return list;
    }

    public void cleanUpUsers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UsersDAO dao = new UsersDAO(session);
        List<UserData> users = dao.getAllUsers();
        for (UserData user : users)
            session.delete(user);
        transaction.commit();
        session.close();


    }

    public void printConnectInfo() {
        try {
            SessionFactoryImpl sessionFactoryImpl = (SessionFactoryImpl) sessionFactory;
            Connection connection = sessionFactoryImpl.getConnectionProvider().getConnection();
            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("Autocommit: " + connection.getAutoCommit());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
