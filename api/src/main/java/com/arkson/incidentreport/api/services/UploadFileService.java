package com.arkson.incidentreport.api.services;

import org.springframework.web.multipart.MultipartFile;

public interface UploadFileService {

    void uploadFile(String id, MultipartFile file);

}
