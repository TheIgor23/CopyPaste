package ru.jezemoin.hashgenerator.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HashResponse {
    String hash;

    public static HashResponse makeDefault(String hash) {
        return builder()
                .hash(hash)
                .build();
    }
}
