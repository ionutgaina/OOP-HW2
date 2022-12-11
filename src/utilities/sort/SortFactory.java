package utilities.sort;

public final class SortFactory {


    /**
     * Here we have the factory method that returns the sort type
     *
     * @param sortCriteria - the criteria by which we sort
     * @param sortType     - the type of sort (increasing or decreasing)
     * @return - the corresponding sort by criteria and type
     */
    public ISort createSort(final String sortCriteria, final String sortType) {
        return switch (sortCriteria) {
            case "rating" -> new SortByRating(sortType);
            case "duration" -> new SortByDuration(sortType);
            case "likes" -> new SortByLikes(sortType);
            default -> null;
        };
    }
}
