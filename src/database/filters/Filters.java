package database.filters;

public final class Filters {
    private Sort sort;
    private Contains contains;

    public Filters() {
    }

    public Filters(final Sort sort, final Contains contains) {
        this.sort = sort;
        this.contains = contains;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(final Sort sort) {
        this.sort = sort;
    }

    public Contains getContains() {
        return contains;
    }

    public void setContains(final Contains contains) {
        this.contains = contains;
    }
}
