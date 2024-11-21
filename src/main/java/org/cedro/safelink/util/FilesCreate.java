package org.cedro.safelink.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesCreate {

    public void createFile() {
        // Inicializa o objeto Path para o arquivo
        Path path = Paths.get("D:/data/keyRsa.txt");
        try {
            // Garante que o diretório existe
            Path directory = path.getParent(); // Obtém o diretório pai do arquivo
            if (directory != null && !Files.exists(directory)) {
                Files.createDirectories(directory);
                System.out.println("Diretório criado em: " + directory);
            }

            // Cria o arquivo se ele não existir
            if (!Files.exists(path)) {
                Path createdFilePath = Files.createFile(path);
                System.out.println("Arquivo criado em: " + createdFilePath);
            } else {
                System.out.println("Arquivo já existe em: " + path);
            }
        } catch (IOException e) {
            System.err.println("Erro ao criar o arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

