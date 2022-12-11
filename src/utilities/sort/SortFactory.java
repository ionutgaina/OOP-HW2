package utilities.sort;

public class SortFactory {
    public ISort createSort(final String sortCriteria, final String sortType) {
        return switch (sortCriteria) {
            case "rating" -> new SortByRating(sortType);
            case "duration" -> new SortByDuration(sortType);
            case "likes" -> new SortByLikes(sortType);
            default -> null;
        };
    }
}
