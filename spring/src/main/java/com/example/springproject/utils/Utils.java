package com.example.springproject.utils;

import java.nio.ByteBuffer;
import java.util.UUID;

public final class Utils {
    private Utils(){}

    public static byte[] UuidToBytes(UUID uuid){
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[16]);
        byteBuffer.putLong(uuid.getMostSignificantBits());
        byteBuffer.putLong(uuid.getLeastSignificantBits());
        return byteBuffer.array();
    }

    public static UUID BytesToUuid(byte[] bytes){
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        return new UUID(byteBuffer.getLong(), byteBuffer.getLong());
    }

}
