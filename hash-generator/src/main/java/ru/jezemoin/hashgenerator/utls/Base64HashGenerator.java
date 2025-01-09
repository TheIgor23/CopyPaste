package ru.jezemoin.hashgenerator.utls;

import java.nio.ByteBuffer;
import java.util.Base64;

public class Base64HashGenerator implements HashGenerator{
    @Override
    public String generateHash(Integer value) {
        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
        buffer.putInt(value);
        byte[] byteArray = buffer.array();

        return Base64.getUrlEncoder().withoutPadding().encodeToString(byteArray);
    }
}
