package org.acme.request;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

public class SearchMovies extends PageRequest {

    @QueryParam("datasource")
    @DefaultValue("movies")
    private String datasource;

    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }
}
