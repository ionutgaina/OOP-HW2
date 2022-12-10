package website;

import database.User;
import lombok.Data;

@Data
public final class CurrentUser {
    public static CurrentUser instance = null;

    private User user;

    private CurrentUser() { }

    public static CurrentUser getInstance() {
        if ( instance == null ) {
            instance = new CurrentUser();
        }
        return instance;
    }

    public void init() {
        user = null;
    }
}
