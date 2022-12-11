package utilities.sort;

import database.Movie;

import java.util.ArrayList;

public class SortByDurationRating{
    private final String durationSortType;
    private final String ratingSortType;

    public SortByDurationRating(final String durationSortType, final String ratingSortType) {
        this.durationSortType = durationSortType;
        this.ratingSortType = ratingSortType;
    }

    public void doSort(ArrayList<Movie> movies) {
        movies.sort((o1, o2) -> {
            if (o1.getDuration() > o2.getDuration()) {
                return durationSortType.equals("increasing") ? 1 : -1;
            } else if (o1.getDuration() < o2.getDuration()) {
                return durationSortType.equals("increasing") ? -1 : 1;
            } else {
                return ratingSortType.equals("increasing")
                       ? Double.compare(o1.getRating(), o2.getRating())
                       : Double.compare(o2.getRating(), o1.getRating());
            }
        });
    }
}
