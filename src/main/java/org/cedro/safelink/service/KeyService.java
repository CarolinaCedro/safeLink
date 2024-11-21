package org.cedro.safelink.service;

import org.cedro.safelink.util.FilesCreate;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class KeyService {

    private FilesCreate filesCreate = new FilesCreate();

    public KeyPair generateRSAKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);

        // Gera o par de chaves
        KeyPair keyPair = keyGen.generateKeyPair();

        // Garante que o arquivo existe
        this.filesCreate.createFile();

        // Salva as chaves no arquivo
        saveKeyPairToFile(keyPair);

        return keyPair;
    }

    private void saveKeyPairToFile(KeyPair keyPair) {
        try {
            // Caminho do arquivo
            Path filePath = Path.of("D:/data/keyRsa.txt");

            // Converte as chaves para Base64
            String publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
            String privateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());

            // Formata o conteúdo para salvar
            String content = "-----BEGIN PUBLIC KEY-----\n" +
                    publicKey +
                    "\n-----END PUBLIC KEY-----\n\n" +
                    "-----BEGIN PRIVATE KEY-----\n" +
                    privateKey +
                    "\n-----END PRIVATE KEY-----\n";

            // Escreve no arquivo
            Files.writeString(filePath, content, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Chaves RSA salvas em: " + filePath);

        } catch (IOException e) {
            System.err.println("Erro ao salvar as chaves no arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public SecretKey generateAESKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256); // Tamanho da chave AES
        return keyGen.generateKey();
    }


    public String convertKeyToPem(SecretKey secretKey) {
        String keyBase64 = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        return "-----BEGIN AES KEY-----\n" +
                keyBase64 +
                "\n-----END AES KEY-----";
    }


}
