package com.encryptor.service;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class EncryptService {

    private static final String ALGORITHM_AES = "AES";

    @Value("${encrypt.password}")
    public String encryptPassword;

    public byte[] doCrypto(HttpEntity<byte[]> httpEntity,int opMode) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(ALGORITHM_AES);
        SecretKeySpec aes = new SecretKeySpec(encryptPassword.getBytes(), ALGORITHM_AES);
        cipher.init(opMode, aes);

        byte[] base64decodedTokenArr = Base64.decodeBase64(getBytesFromHttpEntity(httpEntity));

        return cipher.doFinal(base64decodedTokenArr);
    }

    private byte[] getBytesFromHttpEntity(HttpEntity<byte[]> httpEntity) {
        return Optional.ofNullable(httpEntity)
                .map(HttpEntity::getBody)
                .orElse(new byte[]{});
    }
}
