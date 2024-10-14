package ru.jezemoin.hashgenerator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.jezemoin.hashgenerator.dto.HashResponse;
import ru.jezemoin.hashgenerator.service.HashGeneratorService;

@RestController
public class HashController {

    public static final String GENERATE_HASH = "/generate";

    private final HashGeneratorService generatorService;

    public HashController(HashGeneratorService generatorService) {
        this.generatorService = generatorService;
    }

    @GetMapping(GENERATE_HASH)
    public HashResponse generateHash() {
        String hash = generatorService.getUniqueHash();
        return HashResponse.makeDefault(hash);
    }

}
