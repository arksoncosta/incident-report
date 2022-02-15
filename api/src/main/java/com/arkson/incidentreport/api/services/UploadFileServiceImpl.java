package com.arkson.incidentreport.api.services;

import com.arkson.incidentreport.api.utils.FileUtils;
import com.arkson.incidentreport.core.model.Incident;
import com.arkson.incidentreport.coreelasticsearch.components.ElasticSearchQuery;
import com.arkson.incidentreport.coreelasticsearch.service.ElasticSearchUpdateService;
import com.arkson.incidentreport.cores3.components.S3BucketUpload;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UploadFileServiceImpl implements UploadFileService {

    private final ElasticSearchUpdateService elasticSearchUpdateService;
    private final ElasticSearchQuery elasticSearchQuery;
    private final S3BucketUpload s3BucketUpload;

    @SneakyThrows
    @Override
    public void uploadFile(String id, MultipartFile file) {
        String objectUrl = s3BucketUpload.uploadFile(id, FileUtils.multiPartFileToFile(file));

        Incident incident = elasticSearchQuery.searchById(id);
        incident.setObjectUrl(objectUrl);
        elasticSearchUpdateService.updateElasticSearch(incident);
    }
}
