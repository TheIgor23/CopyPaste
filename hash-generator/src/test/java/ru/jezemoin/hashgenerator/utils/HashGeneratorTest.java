package ru.jezemoin.hashgenerator.utils;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.jezemoin.hashgenerator.utls.Base64HashGenerator;
import ru.jezemoin.hashgenerator.utls.HashGenerator;


public class HashGeneratorTest {

    HashGenerator hashGenerator = new Base64HashGenerator();

    @Test
    void shouldGetHashOfCorrectLength() {
        Assertions.assertEquals(6, hashGenerator.generateHash(1).length());
        Assertions.assertEquals(6, hashGenerator.generateHash(1000).length());
        Assertions.assertEquals(6, hashGenerator.generateHash(Integer.MAX_VALUE).length());
        Assertions.assertEquals(6, hashGenerator.generateHash(0).length());
        Assertions.assertEquals(6, hashGenerator.generateHash(-1).length());
    }

    @Test
    void shouldGenerateUniqueHashes() {
        String hash1 = hashGenerator.generateHash(1);
        String hash2 = hashGenerator.generateHash(-1);

        Assertions.assertNotEquals(hash1, hash2);
    }

    @Test
    void shouldHandleNegativeValues() {
        String hash = hashGenerator.generateHash(-1);
        Assertions.assertEquals(6, hash.length());
    }
}
