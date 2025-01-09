package ru.jezemoin.util;


import io.minio.*;
import io.minio.errors.ErrorResponseException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.jezemoin.config.MinioClientConfig;
import ru.jezemoin.exceptions.NotFoundException;

import java.io.InputStream;
import java.util.Objects;

@Component
@Slf4j
public class MinioUtil {

    private final MinioClient minioClient;

    private final MinioClientConfig minioClientConfig;

    public MinioUtil(MinioClient minioClient, MinioClientConfig minioClientConfig) {
        this.minioClient = minioClient;
        this.minioClientConfig = minioClientConfig;
    }

    @SneakyThrows
    public void putObject(MultipartFile multipartFile, String filename) {
        log.info("MinioUtil put object: {}", filename);

        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(minioClientConfig.getBucketName())
                        .object(filename)
                        .stream(multipartFile.getInputStream(), -1, minioClientConfig.getFileSize())
                        .build()
        );
    }


    @SneakyThrows
    public InputStream getObject(String objectName) {
        log.info("MinioUtil get object: {}", objectName);
        try {
           return minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(minioClientConfig.getBucketName())
                            .object(objectName)
                            .build()
            );
        } catch (ErrorResponseException ex) {
            if (Objects.equals(ex.errorResponse().code(), "NoSuchKey")) {
                throw new NotFoundException(String.format("Object with name %s not found", objectName));
            }
            throw ex;
        }

    }
}
