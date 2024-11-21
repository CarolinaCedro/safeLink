package org.cedro.safelink.controller;

import org.cedro.safelink.service.HashService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/safelink/hash")
public class HashController {


    private static final Logger logger = LoggerFactory.getLogger(HashController.class);


    private final HashService hashService;

    public HashController(HashService hashService) {
        this.hashService = hashService;
    }

    @PostMapping(value = "/generateFileHash", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> generateFileHash(@RequestParam("file") MultipartFile file) throws IOException, NoSuchAlgorithmException {
        byte[] fileContent = file.getBytes();
        String hash = this.hashService.generateFileHash(fileContent);

        Map<String, String> response = new HashMap<>();
        response.put("fileName", file.getOriginalFilename());
        response.put("hash", hash);
        response.put("algorithm", "SHA-256");

        logger.info("Gerando hash para o arquivo: {}", file.getOriginalFilename());


        return ResponseEntity.ok(response);
    }

}

