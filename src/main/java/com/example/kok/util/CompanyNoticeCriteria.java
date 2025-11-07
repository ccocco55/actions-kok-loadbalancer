package com.example.kok.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class CompanyNoticeCriteria {
    private int page;
    private int pageCount;
    private int startPage;
    private int endPage;
    private int rowCount;
    private int realEnd;
    private int offset;
    private boolean hasNextPage;
    private boolean hasPreviousPage;
    private boolean hasMore;
    private int count;

    public CompanyNoticeCriteria(int page, int total) {
        rowCount = 5;
        pageCount = 5;
        count = rowCount + 1;
        this.page = Math.max(1, page);
        endPage = (int)(Math.ceil(page / (double)pageCount) * pageCount);
        startPage = endPage - pageCount + 1;
        realEnd = (int)(Math.ceil(total / (double)rowCount));
        endPage = Math.min(realEnd, endPage);
        endPage = Math.max(1, endPage);
        offset = (page - 1) * rowCount;
        hasNextPage = endPage < realEnd;
        hasPreviousPage = startPage > 1;
    }
}
