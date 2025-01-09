package ru.jezemoin.hashgenerator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.jezemoin.hashgenerator.utls.Base64HashGenerator;
import ru.jezemoin.hashgenerator.utls.HashGenerator;

@Configuration
public class HashGeneratorConfig {

    @Bean
    public HashGenerator hashGenerator() {
        return new Base64HashGenerator();
    }


}
