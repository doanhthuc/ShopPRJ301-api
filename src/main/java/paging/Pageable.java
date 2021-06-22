package paging;

import sort.Sorter;

public interface Pageable {
    Integer getPage();
    Integer getOffset();
    Integer getLimit();
    Sorter getSorter();
    void setSorter(Sorter sorter);
}
