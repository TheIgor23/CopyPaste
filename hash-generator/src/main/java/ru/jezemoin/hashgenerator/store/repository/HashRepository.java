package ru.jezemoin.hashgenerator.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.jezemoin.hashgenerator.store.entity.HashEntity;

public interface HashRepository extends JpaRepository<HashEntity, Integer> {

}
