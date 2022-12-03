package website;

import database.User;

public final class CurrentUser {
    public static CurrentUser instance = null;

    private User currentUser = null;

    private CurrentUser() { }

    public static CurrentUser getInstance() {
        if ( instance == null ) {
            instance = new CurrentUser();
        }
        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void clear() {
        currentUser = null;
    }
}
