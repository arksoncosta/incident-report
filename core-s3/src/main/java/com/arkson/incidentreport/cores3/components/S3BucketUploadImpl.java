package com.arkson.incidentreport.cores3.components;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@RequiredArgsConstructor
@Slf4j
public class S3BucketUploadImpl implements S3BucketUpload {

    private final AmazonS3 amazonS3;

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    @Value("${aws.s3.objectUrl}")
    private String objectUrl;

    @Retryable(maxAttemptsExpression = "${aws.retry.maxAttempts}",
            backoff = @Backoff(delayExpression = "${aws.retry.backoffDelay}"))
    @Override
    public String uploadFile(String folderName, File file) {
        String objectName = String.format("%s/%s", folderName, file.getName());
        amazonS3.putObject(new PutObjectRequest(bucketName, objectName, file));
        log.info("File upload successfuly");

        return String.format("%s/%s", this.objectUrl, objectName);
    }
}
