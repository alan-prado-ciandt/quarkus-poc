package org.acme.config;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Produces;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

public class AwsConfig {

    @Produces
    @ApplicationScoped
    S3Presigner createS3Presigner() {
        return S3Presigner.create();
    }

}
