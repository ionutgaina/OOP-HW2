package website;

import database.User;
import lombok.Data;

@Data
public final class CurrentUser {
    private static CurrentUser instance = null;

    private User user;

    private CurrentUser() {
    }

    /**
     * @return the instance of CurrentUser ( singleton )
     */
    public static CurrentUser getInstance() {
        if (instance == null) {
            instance = new CurrentUser();
        }
        return instance;
    }

    /**
     * reset the user to null
     */
    public void init() {
        user = null;
    }
}
