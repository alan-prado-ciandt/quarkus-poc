package org.acme.request;

import java.io.Serializable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

public abstract class PageRequest implements Serializable {

    @QueryParam("page")
//    @PositiveOrZero
    private int page;
    @QueryParam("pageSize")
//    @Max(message="Page maximum size", value=100)
//    @Min(message="Page minimum size", value=10)
    @DefaultValue("10")
    private int pageSize;

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}

