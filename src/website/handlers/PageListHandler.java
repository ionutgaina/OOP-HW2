package website.handlers;

import database.Action;
import utilities.Output;
import website.CurrentPage;

import java.util.LinkedList;

public class PageListHandler {
    private static PageListHandler instance = null;
    private LinkedList<Action> pageChangeActions = new LinkedList<>();
    private boolean extraUndo = false;

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

        pageChangeActions.push(action);
        action.setCurrentPage(CurrentPage.getInstance().getPage());
        extraUndo = true;
        ChangePageHandler.handle(action);
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

        Action action = pageChangeActions.pop();
        CurrentPage.getInstance().setPage(action.getCurrentPage());

        if (extraUndo) {
            extraUndo = false;
            undo();
            return;
        }
        ChangePageHandler.handle(action);
    }

    public void init() {
        pageChangeActions = new LinkedList<>();
        extraUndo = false;
    }
}
