package org.cedro.safelink.controller;

import org.cedro.safelink.service.EncryptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.security.PublicKey;

@RestController
@RequestMapping("/api/v1/safelink/encryption")
public class EncryptionController {

    private final EncryptionService encryptionService;

    public EncryptionController(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @PostMapping("encryptAESKey")
    public ResponseEntity<byte[]> encryptAESKey(SecretKey aesKey, PublicKey publicKey) throws Exception {
        return ResponseEntity.ok(this.encryptionService.encryptAESKey(aesKey, publicKey));
    }
}
