package ru.jezemoin.restservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.jezemoin.util.MinioUtil;

import java.io.InputStream;
import java.util.Objects;


@Service
public class RestService {

    private final MinioUtil minioUtil;

    private final ru.jezemoin.service.HashClient hashClient;

    public RestService(MinioUtil minioUtil, ru.jezemoin.service.HashClient hashClient) {
        this.minioUtil = minioUtil;
        this.hashClient = hashClient;
    }


    public String uploadFile(MultipartFile file) {

        String hash = hashClient.getHash();

        String fileName = Objects.requireNonNull(hash);
        minioUtil.putObject(file, fileName);

        return fileName;
    };

    public InputStream getFile(String fileName) {

        return minioUtil.getObject(fileName);

    };
}


