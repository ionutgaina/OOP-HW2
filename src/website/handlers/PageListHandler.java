package website.handlers;

import database.Action;
import utilities.Output;
import website.CurrentPage;

import java.util.LinkedList;

public final class PageListHandler {
    private static PageListHandler instance = null;
    private LinkedList<Action> pageChangeActions = new LinkedList<>();

    private PageListHandler() {
    }

    /**
     * @return the instance of the class PageListHandler
     */
    public static PageListHandler getInstance() {
        if (instance == null) {
            instance = new PageListHandler();
        }
        return instance;
    }

    /**
     * execute the change page action
     * add an action to the list of actions
     *
     * @param action the action to be added
     */
    public void changePage(final Action action) {
        if (action.getPage().equals("logout")) {
            init();
            ChangePageHandler.handle(action);
            return;
        }

        if (action.getPage().equals("register") || action.getPage().equals("login")) {
            ChangePageHandler.handle(action);
            return;
        }

        String page = CurrentPage.getInstance().getPage();
        if (ChangePageHandler.handle(action)) {
            pageChangeActions.push(action);
            action.setCurrentPage(page);
        }
    }

    /**
     * undo the last action
     */
    public void undo() {
        if (pageChangeActions.isEmpty()) {
            Output.getInstance().getOutput()
                  .addPOJO(new ErrorHandler(true));
            return;
        }

        pageChangeActions.pop();
        if (pageChangeActions.isEmpty()) {
            CurrentPage.getInstance().setPage("home");
            return;
        }

        Action action = pageChangeActions.peek();
        CurrentPage.getInstance().setPage(action.getCurrentPage());

        ChangePageHandler.handle(action);
    }

    /**
     * initialize the list of actions
     */
    public void init() {
        pageChangeActions = new LinkedList<>();
    }
}
