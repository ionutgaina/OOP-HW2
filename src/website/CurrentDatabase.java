package website;

import database.Database;
import lombok.Data;

@Data
public final class CurrentDatabase {
    private static CurrentDatabase instance = null;

    private Database database;

    private CurrentDatabase() {
    }

    /**
     * @return instance of currentDatabase ( singleton )
     */
    public static CurrentDatabase getInstance() {
        if (instance == null) {
            instance = new CurrentDatabase();
        }
        return instance;
    }

    /**
     * initialize the data with the current database
     */
    public void init(final Database inputDatabase) {
        this.database = inputDatabase;
    }
}
