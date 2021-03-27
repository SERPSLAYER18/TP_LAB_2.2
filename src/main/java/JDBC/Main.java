package JDBC;//

import JDBC.DataSets.UserData;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException {
        DBService dbService = new DBService();
       // dbService.cleanUpUsers();

        ArrayList<UserData> list = dbService.getUsers(userData -> true);
        for( var user:list)
        {
            System.out.println(user.toString());
        }
    }
}








































