package ru.jezemoin.config;


import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Data
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinioClientConfig {


    /** *  It's a URL, domain name ,IPv4 perhaps IPv6 Address ") */
    private String endpoint;

    /** * //"TCP/IP Port number " */
    private Integer port;

    /** * //"accessKey Similar to user ID, Used to uniquely identify your account " */
    private String accessKey;

    /** * //"secretKey It's the password for your account " */
    private String secretKey;

    /** * //" If it is true, It uses https instead of http, The default value is true" */
    private boolean secure;

    /** * //" Default bucket " */
    private String bucketName;

    /** *  The maximum size of the picture  */
    private long imageSize;

    /** *  Maximum size of other files  */
    private long fileSize;


    @Bean
    @SneakyThrows
    public MinioClient minioClient() {
        MinioClient minioClient = MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();

        boolean found =
                minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }

        return minioClient;
    }

}
