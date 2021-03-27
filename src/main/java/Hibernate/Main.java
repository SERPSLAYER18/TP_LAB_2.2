package Hibernate;

import Hibernate.DataSets.UserData;

public class Main {
    public static void main(String[] args) {
        DBService dbService = new DBService();
        dbService.printConnectInfo();
        long userId = dbService.saveUser(new UserData("Liz","Levdanskaya",19));
        System.out.println("Added user id: " + userId);

        UserData dataSet = dbService.getUser(userId);
        System.out.println("User data set: " + dataSet);


    }
}
