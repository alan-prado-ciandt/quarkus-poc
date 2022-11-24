package org.acme.model.databases;

import java.io.Serializable;

public class DatabaseFile {

    private String fileUrl;

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileUrl() {
        return fileUrl;
    }
}
