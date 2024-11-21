package org.cedro.safelink.service;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class HashService {


    public String generateFileHash(byte[] fileContent) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(fileContent);
        return Base64.getEncoder().encodeToString(hashBytes);
    }

}
