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

    /**
     * @return instance of currentPage ( singleton )
     */
    public static CurrentPage getInstance() {
        if (instance == null) {
            instance = new CurrentPage();
        }
        return instance;
    }

    public String getPage() {
        return page;
    }

    public void setPage(final String currentPage) {
        this.page = currentPage;
    }

    /**
     * initialize the data of the current page
     */
    public void init() {
        page = "home";
        currentMoviesList = new ArrayList<>();
    }
}
