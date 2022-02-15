package com.arkson.incidentreport.api.utils;

import lombok.SneakyThrows;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Objects;

public final class FileUtils {

    @SneakyThrows
    public static File multiPartFileToFile(MultipartFile file) {
        File convertedFile = new File("/tmp/"+Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fileOutputStream = new FileOutputStream(convertedFile);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();

        return convertedFile;
    }

}
