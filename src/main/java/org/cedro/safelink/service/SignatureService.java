package org.cedro.safelink.service;

import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.security.Signature;

@Service
public class SignatureService {

    public byte[] signData(byte[] data, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(data);
        return signature.sign();
    }
}
