package org.acme.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.acme.model.databases.DatabaseFile;
import org.acme.response.PageResponse;
import org.acme.service.DatabaseFilesService;

@Path("/databases")
public class DatabaseFilesResource {

    private final DatabaseFilesService databaseFilesService;

    public DatabaseFilesResource(final DatabaseFilesService databaseService) {
        this.databaseFilesService = databaseService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PageResponse<DatabaseFile> getDatabaseFiles() {
        return databaseFilesService.getDatabaseFiles();
    }
}