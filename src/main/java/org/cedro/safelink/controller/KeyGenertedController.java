package org.cedro.safelink.controller;

import org.cedro.safelink.service.KeyService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/v1/safelink/chaves")
public class KeyGenertedController {

    private final KeyService keyService;

    public KeyGenertedController(final KeyService keyService) {
        this.keyService = keyService;
    }


    @PostMapping("/rsa")
    public ResponseEntity<String> generatedRSA() throws NoSuchAlgorithmException {
        this.keyService.generateRSAKeyPair();
        return ResponseEntity.ok("Chave RSA criada com sucesso !");
    }


    @PostMapping("/aes")
    public ResponseEntity<String> generatedAESKey() throws NoSuchAlgorithmException {
        SecretKey aesKey = keyService.generateAESKey();
        String keyPem = keyService.convertKeyToPem(aesKey);

        // Salva a chave AES no sistema de arquivos para download
        try {
            Path path = Paths.get("D:/data/aesKey.pem");
            Files.write(path, keyPem.getBytes());
            return ResponseEntity.ok("Chave AES gerada e salva com sucesso!");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Erro ao salvar a chave AES.");
        }
    }

    // Endpoint para baixar a chave AES em formato .pem
    @GetMapping("/download/aes")
    public ResponseEntity<byte[]> downloadAESKey() throws IOException {
        Path path = Paths.get("D:/data/aesKey.pem");
        byte[] keyContent = Files.readAllBytes(path);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=aesKey.pem");

        return ResponseEntity.ok()
                .headers(headers)
                .body(keyContent);
    }


}
