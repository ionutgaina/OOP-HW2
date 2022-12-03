package website;

public final class CurrentPage {
    private static CurrentPage instance = null;

    private String currentPage = "unlogged";

    private CurrentPage() { }

    public static CurrentPage getInstance() {
        if (instance == null )
        {
            instance = new CurrentPage();
        }
        return instance;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }
}
