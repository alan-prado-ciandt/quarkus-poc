package org.acme.service;

import java.time.Duration;
import java.util.Arrays;
import javax.enterprise.context.ApplicationScoped;
import org.acme.model.databases.DatabaseFile;
import org.acme.response.PageResponse;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import software.amazon.awssdk.core.SdkField;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

@ApplicationScoped
public class DatabaseFilesService {

    private final S3Presigner presigner;
    private final String bucketName;

    public DatabaseFilesService(S3Presigner presigner, @ConfigProperty(name = "BUCKET_NAME") String bucketName) {
        this.presigner = presigner;
        this.bucketName = bucketName;
    }

    public PageResponse<DatabaseFile> getDatabaseFiles() {
        var database = new DatabaseFile();
        String vendorId = "123";
        database.setFileUrl(signUrl(vendorId));
        return new PageResponse<>(Arrays.asList(database));
    }

    private String signUrl(String vendorId) {
        try {
            GetObjectRequest request = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key("vendedor-" + vendorId + "/produtos_db.zip")
                    .responseContentDisposition("attachment")
                    .build();

            GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                    .signatureDuration(Duration.ofMinutes(10))
                    .getObjectRequest(request)
                    .build();

            PresignedGetObjectRequest presignedRequest = presigner.presignGetObject(presignRequest);
            String url = presignedRequest.url().toString();
            return url;
        } catch (S3Exception e) {
            throw new RuntimeException(e);
        }
    }
}
