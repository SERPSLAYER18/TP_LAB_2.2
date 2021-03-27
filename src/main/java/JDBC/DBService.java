package JDBC;

import JDBC.DAO.UserDAO;
import JDBC.DataSets.UserData;
import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Predicate;

public class DBService {

    public static final String JDBC_DRIVER = "org.h2.Driver";
    public static final String DB_URL = "jdbc:h2:./myH2DataBase";
    public static final String USER = "SA";
    public static final String PASS = "";

    private Connection connection = null;
    private UserDAO userDAO;

    public DBService() throws SQLException {
        setupConnection();
        userDAO = new UserDAO(connection);
    }

    public UserData getUser(long id)
    {
        return userDAO.get(id);
    }

    public ArrayList<UserData> getUsers(Predicate<UserData> predicate) throws SQLException
    {
        return userDAO.get(predicate);
    }

    public void saveUser(UserData userData) throws SQLException{
        try {
            connection.setAutoCommit(false);
            userDAO.createTable();
            userDAO.save(userData);
            connection.commit();
        }
        catch (Exception e)
        {
            connection.rollback();
        }
        finally {
            connection.setAutoCommit(true);
        }

    }

    public void updateUser(UserData userData, String[] params) throws SQLException
    {
        try {
            connection.setAutoCommit(false);
            userDAO.update(userData,params);
            connection.commit();
        }
        catch (Exception e)
        {
            connection.rollback();
        }
        finally {
            connection.setAutoCommit(true);
        }

    }

    public void deleteUser(UserData userData) throws SQLException {
        try {
            connection.setAutoCommit(false);
            userDAO.delete(userData);
            connection.commit();
        }
        catch (Exception e)
        {
            connection.rollback();
        }
        finally {
            connection.setAutoCommit(true);
        }
    }

    public void cleanUpUsers() throws SQLException {
        try {
            connection.setAutoCommit(false);
            userDAO.dropTable();
            connection.commit();
        }
        catch (Exception e)
        {
            connection.rollback();
        }
        finally {
            connection.setAutoCommit(true);
        }
    }


    private void setupConnection() throws SQLException {

        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL(DB_URL);
        ds.setUser(USER);
        ds.setPassword(PASS);

        System.out.println("Connecting to database...");
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
        System.out.println("Database connection established");
    }
}
