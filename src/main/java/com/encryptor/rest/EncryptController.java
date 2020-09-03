package com.encryptor.rest;

import com.encryptor.service.EncryptService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
public class EncryptController {

    private final EncryptService encryptService;

    public EncryptController(EncryptService encryptService) {
        this.encryptService = encryptService;
    }

    @PostMapping("/encrypt")
    public ResponseEntity<HttpEntity<byte[]>> encryptFile(HttpEntity<byte[]> httpEntity) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException {
        byte[] encryptedFile = encryptService.doCrypto(httpEntity, Cipher.ENCRYPT_MODE);

        return ResponseEntity.ok().body(new HttpEntity<>(encryptedFile));
    }

    @PostMapping("/decrypt")
    public ResponseEntity<HttpEntity<byte[]>> decryptFile(HttpEntity<byte[]> httpEntity) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException {
        byte[] decryptedFile = encryptService.doCrypto(httpEntity, Cipher.DECRYPT_MODE);

        return ResponseEntity.ok().body(new HttpEntity<>(decryptedFile));
    }
}
