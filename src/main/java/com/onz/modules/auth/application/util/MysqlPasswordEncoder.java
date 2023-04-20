package com.onz.modules.auth.application.util;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 출처: https://7942yongdae.tistory.com/164 [프로그래머 YD:티스토리]
 */
@Deprecated
public class MysqlPasswordEncoder implements PasswordEncoder {

    private static MessageDigest DIGEST = null;

    {
        try {
            DIGEST = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String encode(CharSequence rawPassword) {
        if (rawPassword == null) {
            throw new IllegalArgumentException("rawPassword cannot be null");
        }

        return toString(hashing(getBytes(rawPassword)));
    }

    private byte[] getBytes(CharSequence rowPassword) {
        byte[] result = new byte[rowPassword.length()];

        for (int i = 0; i < rowPassword.length(); i++) {
            result[i] = (byte) (rowPassword.charAt(i) & 0xff);
        }

        return result;
    }

    private byte[] hashing(byte[] bytes) {
        return DIGEST.digest(DIGEST.digest(bytes));
    }

    private String toString(byte[] bytes) {
        StringBuffer buffer = new StringBuffer(41);
        buffer.append("*");

        for (int i = 0; i < bytes.length; i++) {
            padding(buffer, Integer.toHexString(bytes[i] & 0xff).toUpperCase());
        }

        return buffer.toString();
    }

    private void padding(StringBuffer buffer, String hex) {
        if (hex.length() < 2) {
            buffer.append("0");
        }

        buffer.append(hex);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (rawPassword == null || encodedPassword.isEmpty()) {
            return false;
        }

        if (!encodedPassword.equals(encode(rawPassword))) {
            return false;
        }

        return true;
    }

}

