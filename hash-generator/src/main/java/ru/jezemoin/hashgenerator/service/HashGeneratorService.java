package ru.jezemoin.hashgenerator.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.jezemoin.hashgenerator.store.entity.HashEntity;
import ru.jezemoin.hashgenerator.store.repository.HashRepository;

import java.nio.ByteBuffer;
import java.util.Base64;

@Service
public class HashGeneratorService {

    private final HashRepository hashRepository;

    public HashGeneratorService(HashRepository hashRepository) {
        this.hashRepository = hashRepository;
    }

    @Transactional
    public String getUniqueHash() {
        HashEntity hashEntity = new HashEntity();
        HashEntity dbHash = hashRepository.save(hashEntity);

        String strHash = generateHash(dbHash.getId());
        hashEntity.setHash(strHash);

        hashRepository.save(hashEntity);

        return strHash;
    }

    private String generateHash(Integer value) {
        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
        buffer.putInt(value);
        byte[] byteArray = buffer.array();

        String base64Hash = Base64.getUrlEncoder().withoutPadding().encodeToString(byteArray);

        return base64Hash.length() > 8 ? base64Hash.substring(0, 8) : base64Hash;
    }
}
