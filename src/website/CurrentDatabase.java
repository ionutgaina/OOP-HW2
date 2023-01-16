package website;

import database.Database;
import database.Movie;
import database.Notification;
import database.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.stream.Collectors;

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
