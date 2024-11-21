package org.cedro.safelink.service;

import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.security.PublicKey;

@Service
public class EncryptionService {

    public byte[] encryptAESKey(SecretKey aesKey, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(aesKey.getEncoded());
    }
}
