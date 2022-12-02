package entities.pages;

public abstract class Page {
    private boolean changePage(final String page) {
        return false; // return false if fail
    }

    private boolean onPage(final String action) {
        return false; // return fail if fail
    }
}
