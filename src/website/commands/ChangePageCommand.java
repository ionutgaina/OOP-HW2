package website.commands;

import website.CurrentPage;

public class ChangePageCommand implements Command{

    private String thePage;
    public ChangePageCommand(String thePage) {
        this.thePage = thePage;
    }

    @Override
    public void execute() {
        CurrentPage currentPage = CurrentPage.getInstance();
        currentPage.setCurrentPage(this.thePage);
        // do something with the page
    }
}
