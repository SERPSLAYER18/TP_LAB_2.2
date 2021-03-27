package Migrations;

import JDBC.DBService;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Migrations {

    public static int LATEST_SCHEMA_VERSION = 2;

    @Test
    public void migrate() {
        Flyway flyway = Flyway.configure().dataSource(DBService.DB_URL, DBService.USER, DBService.PASS).load();
        flyway.baseline();
        flyway.migrate();

        //Не вылезло никаких ошибок
        Assertions.assertTrue(true);
    }

}
