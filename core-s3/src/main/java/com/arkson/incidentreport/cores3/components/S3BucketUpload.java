package com.arkson.incidentreport.cores3.components;

import java.io.File;

public interface S3BucketUpload {

    String uploadFile(String folderName, File file);

}
