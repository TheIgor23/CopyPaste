package ru.jezemoin.hashgenerator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.jezemoin.common.dto.HashResponseDTO;
import ru.jezemoin.hashgenerator.service.HashGeneratorService;

@RestController
public class HashController {

    public static final String GENERATE_HASH = "/generate";

    private final HashGeneratorService generatorService;

    public HashController(HashGeneratorService generatorService) {
        this.generatorService = generatorService;
    }

    @GetMapping(GENERATE_HASH)
    public ResponseEntity<HashResponseDTO>  generateHash() {
        String hash = generatorService.getUniqueHash();
        return ResponseEntity.status(HttpStatus.CREATED).body(HashResponseDTO.builder().build());

    }

}
