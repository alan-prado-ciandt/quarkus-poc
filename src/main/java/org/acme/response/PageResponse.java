package org.acme.response;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class PageResponse<T> implements Serializable {
    private Collection<T> items;

    public PageResponse(List<T> items) {
        this.items = items;
    }

    public Collection<T> getItems() {
        return items;
    }
}
