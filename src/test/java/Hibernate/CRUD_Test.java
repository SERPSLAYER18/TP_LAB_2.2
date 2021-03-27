package Hibernate;

import Hibernate.DataSets.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class CRUD_Test {

    @Test
    public void testCRUD() {


        ArrayList<UserData> list = new ArrayList<>();
        list.add(new UserData(1, "Kirill", "Lyahnovich", 18));
        list.add(new UserData(2, "Liz", "Levdanskaya", 19));
        list.add(new UserData(3, "Arsen", "Borsukov", 18));


        //CREATE
        DBService dbService = new DBService();
        dbService.cleanUpUsers();
        for (
                UserData user : list)
            dbService.saveUser(user);

        ArrayList<UserData> listFromDB = dbService.getAllUsers();
        Assertions.assertEquals(list, listFromDB);


        //UPDATE
        var user = list.get(1);
        var newUser =  user;
        newUser.setFirst("Elizabeth");
        newUser.setAge(18);
        list.set(1, newUser);
        dbService.updateUser(user, newUser);
        listFromDB = dbService.getAllUsers();
        Assertions.assertEquals(list, listFromDB);

        //DELETE
        dbService.deleteUser(user);
        listFromDB = dbService.getAllUsers();
        list.remove(user);
        Assertions.assertEquals(list, listFromDB);
    }
}
