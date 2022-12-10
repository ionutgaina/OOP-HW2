package website;

import database.Movie;
import lombok.Data;

import java.util.ArrayList;

@Data
public final class CurrentPage {
    private static CurrentPage instance = null;

    private String page;
    private ArrayList<Movie> currentMoviesList;
    

    private CurrentPage() {
    }

    public static CurrentPage getInstance() {
        if (instance == null) {
            instance = new CurrentPage();
        }
        return instance;
    }

    public String getCurrentPage() {
        return page;
    }

    public void setCurrentPage(String currentPage) {
        this.page = currentPage;
    }

    public void init() {
        page = "home";
        currentMoviesList = new ArrayList();
    }
}
