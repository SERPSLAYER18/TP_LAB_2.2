package JDBC;

import JDBC.DataSets.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

public class CRUD_Test {

    @Test
    public void testCRUD() throws SQLException {

        ArrayList<UserData> list = new ArrayList<>();
        list.add(new UserData(1, "Kirill", "Lyahnovich", 18));
        list.add(new UserData(2, "Liz", "Levdanskaya", 19));
        list.add(new UserData(3, "Arsen", "Borsukov", 18));


        //CREATE
        DBService dbService = new DBService();
        dbService.cleanUpUsers();
        for (UserData user : list)
            dbService.saveUser(user);

        ArrayList<UserData> listFromDB = dbService.getUsers(userData -> true);
        Assertions.assertEquals(list, listFromDB);


        //UPDATE
        var user = list.get(1);
        dbService.updateUser(user, new String[]{"2", "Elizabeth", "Levdanskaya", "18"});
        listFromDB = dbService.getUsers(userData -> true);
        user.setFirst("Elizabeth");
        user.setAge(18);
        list.set(1, user);
        Assertions.assertEquals(list, listFromDB);

        //DELETE
        dbService.deleteUser(user);
        listFromDB = dbService.getUsers(userData -> true);
        list.remove(user);
        Assertions.assertEquals(list, listFromDB);


    }
}
