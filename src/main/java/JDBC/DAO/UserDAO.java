package JDBC.DAO;

import JDBC.DataSets.UserData;
import JDBC.SQL_EXECUTOR.DataSetAction;
import JDBC.SQL_EXECUTOR.SQLExecutor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Predicate;

public class UserDAO implements DAO<UserData> {
    DataSetAction<UserData> getUsersList = resultSet -> {
        ArrayList<UserData> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(new UserData(resultSet));
        }
        return list;
    };
    private SQLExecutor executor;


    public UserDAO(Connection connection) throws SQLException {
        executor = new SQLExecutor(connection);
        createTable();
    }

    @Override
    public UserData get(long id) {
        try {
            return executor.sqlQuery("select * from users where id = " + id, getUsersList).get(0);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<UserData> get(Predicate<UserData> predicate) throws SQLException {

        return executor.sqlQuery("select * from users", resultSet -> {
                    ArrayList<UserData> list = new ArrayList<>();
                    while (resultSet.next()) {
                        UserData userData = new UserData(resultSet);
                        if (predicate.test(userData))
                            list.add(userData);
                    }
                    return list;
                }
        );

    }

    @Override
    public void save(UserData userData) throws SQLException {
        executor.sqlUpdate(String.format("insert into users (first,last,age) values ('%s','%s',%d)",
                userData.getFirst(),
                userData.getLast(),
                userData.getAge()));

    }

    @Override
    public void update(UserData userData, String[] params) throws SQLException {
        // %s, last = %s, age = %d where id = %d
        executor.sqlUpdate(String.format("update users set (first,last,age) = ('%s','%s','%s') where id = '%s'",
                params[1],
                params[2],
                params[3],
                params[0]));
    }

    @Override
    public void delete(UserData userData) throws SQLException {
        executor.sqlUpdate(String.format("delete from users where id = %d", userData.getId()));

    }

    @Override
    public void createTable() throws SQLException {
        executor.sqlUpdate("CREATE  TABLE  IF NOT EXISTS users  (id bigint  auto_increment primary key ,first VARCHAR(255), last VARCHAR(255),  age int )");
    }

    @Override
    public void dropTable() throws SQLException {
        executor.sqlUpdate("drop table users ");
    }

    @Override
    public void clearTable() throws SQLException {
        executor.sqlUpdate("delete from users ");
    }
}
