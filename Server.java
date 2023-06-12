package ProjetoLP2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {

    private static Map<String, Boolean> Cadeiras = new HashMap<>();

    private static void inicializarCadeiras() {
        for (char i = 'A'; i <= 'D'; i++) {
            for (int j = 1; j <= 6; j++) {
                String cadeira = String.valueOf(i) + j;
                Cadeiras.put(cadeira, false);
            }
        }
    }

    public static void main(String[] args) {
        inicializarCadeiras();
        try {
            ServerSocket server = new ServerSocket(44444);
            System.out.println("Servidor aguardando conexões...");

            while (true) {
                Socket client = server.accept();

                Thread thread = new ThreadServidor(client, Cadeiras);
                System.out.println("Usuário conectado: " + client.getInetAddress().getHostAddress() + " Na thread: " + thread.getId());
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
