import JDBC.DBService;
import org.flywaydb.core.Flyway;

public class Migrations {

    public static void migrate()
    {
        Flyway flyway = Flyway.configure().dataSource(DBService.DB_URL,DBService.USER,DBService.PASS).load();
        flyway.baseline();
        flyway.migrate();
    }

    public static void main(String[] args) {
        migrate();
    }
}
