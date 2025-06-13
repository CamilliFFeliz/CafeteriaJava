package model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
    private static final String NOME_ARQUIVO = "log_cafeteria.txt";
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public static void salvarLog(String mensagem) {
        try (PrintWriter out = new PrintWriter(new FileWriter(NOME_ARQUIVO, true))) {
            String logFormatado = dtf.format(LocalDateTime.now()) + " - INFO - " + mensagem;
            out.println(logFormatado);
        } catch (IOException e) {
            salvarErroCritico("Erro ao salvar log de informação", e);
        }
    }

    public static void salvarLog(String mensagem, Exception erro) {
        try (PrintWriter out = new PrintWriter(new FileWriter(NOME_ARQUIVO, true))) {
            String logFormatado = dtf.format(LocalDateTime.now()) + " - ERRO - " + mensagem;
            out.println(logFormatado);
            erro.printStackTrace(out);
        } catch (IOException e) {
            salvarErroCritico("Erro ao salvar log de erro", e);
        }
    }

    private static void salvarErroCritico(String mensagem, Exception erro) {
        try (PrintWriter out = new PrintWriter(new FileWriter(NOME_ARQUIVO, true))) {
            String logFormatado = dtf.format(LocalDateTime.now()) + " - CRITICO - " + mensagem;
            out.println(logFormatado);
            erro.printStackTrace(out);
        } catch (IOException ignored) {
        }
    }
}
