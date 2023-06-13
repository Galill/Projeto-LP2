import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
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
            ArrayList<String> totalCadeiras = new ArrayList<>();

            while (escolha) {
                System.out.print("Escolha o seu assento: ");
                cadeiraSelecionada = sc.nextLine();

                out.writeUTF(cadeiraSelecionada);

                String respostaServer = in.readUTF();
                System.out.println(respostaServer);

                if (respostaServer.equals("Reserva feita!")) {
                        totalCadeiras.add(cadeiraSelecionada);
                }

                if (cadeiraSelecionada.equalsIgnoreCase("sair")){
                    escolha = false;
                    System.out.println("Assentos escolhidos: ");
                        System.out.println(totalCadeiras);
                }
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sc.close();
    }
}
