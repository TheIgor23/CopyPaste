package ru.jezemoin.hashgenerator.service;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.jezemoin.hashgenerator.store.repository.HashRepository;

@SpringBootTest
@ActiveProfiles("test")
public class HashGeneratorServiceTest {

    @Autowired
    private HashGeneratorService hashGeneratorService;

    @Autowired
    private HashRepository hashRepository;

    @AfterEach
    public void cleanDatabase() {
        hashRepository.deleteAll();
    }

    @Test
    public void testGenerate1000Hashes() {
        int numberOfEntities = 1000;

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < numberOfEntities; i++) {
            String hash = hashGeneratorService.getUniqueHash();
            Assertions.assertNotNull(hash);
        }

        long endTime = System.currentTimeMillis();

        long duration = endTime - startTime;

        System.out.println("Time to generate " + numberOfEntities + " hashes: " + duration + " ms");

        Assertions.assertEquals(numberOfEntities, hashRepository.count());

        hashRepository.deleteAll();
    }


}
