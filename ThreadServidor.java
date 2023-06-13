import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Map;

public class ThreadServidor extends Thread {
    private Socket client;
    private Map<String, Boolean> Cadeiras;

    public ThreadServidor(Socket client, Map<String, Boolean> Cadeiras) {
        this.client = client;
        this.Cadeiras = Cadeiras;
    }

    @Override
    public void run() {
        try {
            DataInputStream in = new DataInputStream(client.getInputStream());
            DataOutputStream out = new DataOutputStream(client.getOutputStream());

            boolean escolha = true;
            String cadeiraSelecionada = null;

            while (escolha) {
                cadeiraSelecionada = in.readUTF();

                String respostaServer;

                if (Cadeiras.containsKey(cadeiraSelecionada)) {
                    if (Cadeiras.get(cadeiraSelecionada)) {
                        respostaServer = "Assento ocupado!";
                    } else {
                        Cadeiras.put(cadeiraSelecionada, true);
                        respostaServer = "Reserva feita!";
                       
                    }
                } else if (cadeiraSelecionada.equalsIgnoreCase("sair")){
                    respostaServer = "Obrigado por voar conosco!";
                    escolha = false;
                } else {
                    respostaServer = "Assento inválido!";
                }

                out.writeUTF(respostaServer);
            }

            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
