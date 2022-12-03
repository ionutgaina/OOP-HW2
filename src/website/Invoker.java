package website;

import database.Action;
import website.commands.Command;

import java.util.Arrays;
import java.util.LinkedList;

public class Invoker {
    private final LinkedList<Command> history = new LinkedList<>();

    public void command(Action action) {
        String actionType = action.getType();
        switch (actionType) {
            case "change page" -> {
                System.out.println("change");
                changePage(action.getPage());
            }
            case "on page" -> {
                System.out.println("on");
            }
            default -> System.out.println("nu exista comanda: " + actionType);
        }
    }

    private void changePage(String page) {
        CurrentPage currentPage = CurrentPage.getInstance();
        boolean resultCurrentPage;

        resultCurrentPage = Arrays
                .stream(unLoggedEnum.values())
                .map(unLoggedEnum::getPage)
                .anyMatch(currentPage.getCurrentPage()::contains);

        if (resultCurrentPage) {
            switch (page) {
                case "login":
                case "register":
                    System.out.println("nelogat");
                    // somethind unlogged
                    return;
                default:
                    System.out.println("eroare nelogat");
                    // error
                    return;
            }
        }

        resultCurrentPage = Arrays.stream(loggedEnum.values())
                .map(loggedEnum::getPage)
                .anyMatch(currentPage.getCurrentPage()::contains);

        if (resultCurrentPage) {
            switch (page) {
                case "upgrades":
                case "movies":
                    System.out.println("logat");
                    // somethind unlogged
                    return;
                case "logout":
                    System.out.println("ma deloghez");
                    // do something
                    return;

                case "see details":
                    if (currentPage.getCurrentPage().equals("see details"))
                    {
                        //error
                        System.out.println("details error");
                        return;
                    }

                    return;
                default:
                    // error
                    System.out.println("eroare logat");
            }
        }
    }

    private enum unLoggedEnum {
        UNLOGGED("unlogged"),
        LOGIN("login"),
        REGISTER("register");

        private final String page;

        unLoggedEnum(String page) {
            this.page = page;
        }

        public String getPage() {
            return page;
        }
    }

    private enum loggedEnum {
        LOGGED("logged"),
        MOVIES("movies"),
        UPGRADES("upgrades"),
        SEE_DETAILS("see details"),
        LOGOUT("logout");

        private final String page;

        loggedEnum(String page) {
            this.page = page;
        }

        public String getPage() {
            return page;
        }
    }
}
