package org.cedro.safelink.controller;

import org.cedro.safelink.service.SignatureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.PrivateKey;

@RestController
@RequestMapping("/api/v1/safelink/signature")
public class SignatureController {

    private final SignatureService signatureService;

    public SignatureController(SignatureService signatureService) {
        this.signatureService = signatureService;
    }


    @PostMapping("/signData")
    public ResponseEntity<byte[]> signData(byte[] data, PrivateKey privateKey) throws Exception {
        return ResponseEntity.ok(this.signatureService.signData(data, privateKey));
    }

}
