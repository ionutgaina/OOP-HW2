package website.commands;

import website.CurrentPage;
import website.CurrentUser;

public class OnPageCommand implements Command{

    private String action;
    public OnPageCommand(String action) {
        this.action = action;
    }

    @Override
    public void execute() {
        CurrentPage currentPage = CurrentPage.getInstance();
        CurrentUser currentUser = CurrentUser.getInstance();
    }
}
