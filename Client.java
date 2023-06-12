package ProjetoLP2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            Socket socket = new Socket("localhost", 44444);

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            boolean escolha = true;
            String cadeiraSelecionada = null;

            while (escolha) {
                System.out.print("Escolha o seu assento: ");
                cadeiraSelecionada = sc.nextLine();

                out.writeUTF(cadeiraSelecionada);

                String resposta = in.readUTF();
                System.out.println(resposta);

                if (resposta.equals("Reserva feita!")) {
                    escolha = false;
                }
            }

            System.out.println("Assento escolhido: " + cadeiraSelecionada);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
