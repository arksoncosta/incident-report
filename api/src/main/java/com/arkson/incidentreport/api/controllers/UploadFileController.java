package com.arkson.incidentreport.api.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface UploadFileController {

    @PostMapping
    void uploadFile(@RequestParam("id") String id, @RequestParam(value = "file", required = false) MultipartFile file);

}
