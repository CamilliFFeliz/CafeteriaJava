package model;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Log {
    private static final String ARQUIVO_LOG = "log_sistema.txt";

    public static void salvarLog(String mensagem) {
        try (FileWriter fw = new FileWriter(ARQUIVO_LOG, true)) {
            String dataHora = LocalDateTime.now().toString();
            fw.write(dataHora + " - " + mensagem + "\n");
        } catch (IOException e) {
            System.out.println("Erro ao salvar log: " + e.getMessage());
        }
    }
}