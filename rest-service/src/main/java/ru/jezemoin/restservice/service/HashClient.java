package ru.jezemoin.service;


import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.util.InternalException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.jezemoin.common.dto.HashResponseDTO;

import java.util.Objects;
import java.util.Optional;

@Service
public class HashClient {

    private final RestTemplate restTemplate;

    @Value("${hash-generator.url}")
    private String hashGeneratorUrl;

    private String GENERATE_HASH_PATH;

    @PostConstruct
    private void SetPath() {
        GENERATE_HASH_PATH = hashGeneratorUrl + "/generate";
    }


    public HashClient() {
        this.restTemplate = new RestTemplate();
    }


    public String getHash() {
        HashResponseDTO responseDTO = restTemplate.getForObject(GENERATE_HASH_PATH, HashResponseDTO.class);

        return Objects.requireNonNull(responseDTO).getHash();
    }


}
