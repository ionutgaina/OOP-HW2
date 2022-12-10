package website;

import database.Database;
import lombok.Data;

@Data
public class CurrentDatabase {
    private static CurrentDatabase instance = null;

    private Database database;

    private CurrentDatabase() { }

    public static CurrentDatabase getInstance() {
        if (instance == null) {
            instance = new CurrentDatabase();
        }
        return instance;
    }

    public void init(Database database) {
        this.database = database;
    }
}
