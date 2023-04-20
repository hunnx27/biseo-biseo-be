package com.onz.modules.auth.application.util;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.tomcat.util.buf.HexUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

//TODO 로그처리... 테스트[2022.07.05]
//TODO 로그처리... 테스트[2022.07.05]
//TODO 로그처리... 테스트[2022.07.05]
public class MysqlAESUtil {

    /**
     *
     * @param key
     * @param text 암호화할 데이터
     */
    public static String encrypto(String key, String text) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        final Cipher encryptCipher = Cipher.getInstance("AES");
        encryptCipher.init(Cipher.ENCRYPT_MODE, generateMySQLAESKey(key, "UTF-8"));
        char[] encode = Hex.encodeHex(encryptCipher.doFinal(text.getBytes("UTF-8")));
//       String encode = Base64.encodeBase64String(encryptCipher.doFinal(text.getBytes("UTF-8")));

        String result = new String(encode).toUpperCase();

        return result;
    }

    /**
     *
     * @param key
     * @param text 암호화할 데이터
     */
    public static byte[] encryptoByte(String key, String text) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        final Cipher encryptCipher = Cipher.getInstance("AES");
        encryptCipher.init(Cipher.ENCRYPT_MODE, generateMySQLAESKey(key, "UTF-8"));

        return encryptCipher.doFinal(text.getBytes("UTF-8"));
    }

    /**
     * @param key
     * @param text 암호화 풀 데이터
     */
    public static String decrypto(String key, String text) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, DecoderException {
        final Cipher decryptCipher = Cipher.getInstance("AES");
        decryptCipher.init(Cipher.DECRYPT_MODE, generateMySQLAESKey(key, "UTF-8"));
        String result = new String(decryptCipher.doFinal(Hex.decodeHex(text.toCharArray())));
//       String result = new String(decryptCipher.doFinal(Base64.decodeBase64(text.getBytes())));

        return result;
    }

    public static SecretKeySpec generateMySQLAESKey(final String key, final String encoding) {
        try {
            final byte[] finalKey = new byte[16];
            int i = 0;
            System.out.println(key.getBytes(encoding));
            for(byte b: key.getBytes(encoding)) {
                finalKey[i++%16] ^= b;
            }
            return new SecretKeySpec(finalKey, "AES");
        }catch(UnsupportedEncodingException e){
            throw new RuntimeException();
        }
    }


}