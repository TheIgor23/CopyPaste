package ru.jezemoin.hashgenerator.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.jezemoin.hashgenerator.store.entity.HashEntity;
import ru.jezemoin.hashgenerator.store.repository.HashRepository;
import ru.jezemoin.hashgenerator.utls.HashGenerator;


@Service
public class HashGeneratorService {

    private final HashRepository hashRepository;

    private final HashGenerator hashGenerator;

    public HashGeneratorService(HashRepository hashRepository, HashGenerator hashGenerator) {
        this.hashRepository = hashRepository;
        this.hashGenerator = hashGenerator;
    }


    @Transactional
    public String getUniqueHash() {
        HashEntity hashEntity = new HashEntity();
        hashEntity = hashRepository.save(hashEntity);

        String strHash = hashGenerator.generateHash(hashEntity.getId());

        hashEntity.setHashData(strHash);
        hashRepository.save(hashEntity);

        return strHash;
    }


}
