package com.arkson.incidentreport.api.controllers;

import com.arkson.incidentreport.api.services.UploadFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1/incidents/upload")
@RequiredArgsConstructor
public class UploadFileControllerImpl implements UploadFileController {

    private final UploadFileService uploadFileService;

    @Override
    public void uploadFile(String id, MultipartFile file) {
        uploadFileService.uploadFile(id, file);
    }
}
