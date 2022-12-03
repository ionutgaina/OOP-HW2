package website;

import database.Action;
import website.commands.Command;

import java.util.LinkedList;

enum UnloggedEnum {
    LOGIN,
    REGISTER;
}

enum LoggedEnum {
    MOVIES,
    UPGRADES,
    SEE_DETAILS,
    LOGOUT;
}

public class Invoker {
    private final LinkedList<Command> history = new LinkedList<>();

    public void command(Action action) {
        String actionType = action.getType();
        switch (actionType) {
            case "change page" -> {
                System.out.println("change");
            }
            case "on page" -> {
                System.out.println("on");
            }
            default -> System.out.println("nu exista comanda: " + actionType);
        }
    }

    private void canChangePage(String page) {
        CurrentPage currentPage = CurrentPage.getInstance();

    }
}
