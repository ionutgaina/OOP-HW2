package database.filters;

public final class Filters {
    private Sort sort;
    private Contains contains;

    public Filters() { }

    public Filters(Sort sort, Contains contains) {
        this.sort = sort;
        this.contains = contains;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public Contains getContains() {
        return contains;
    }

    public void setContains(Contains contains) {
        this.contains = contains;
    }
}
