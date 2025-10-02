package principal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JOptionPane;

public class ClienteSocket {

    private Socket servidor;
    private PrintWriter out;
    private BufferedReader in;

    public void comunicarComServidor() {
        try {
            String endereco = JOptionPane.showInputDialog("Digite o endereco");
            servidor = new Socket(endereco, 4444);//endereco e porta
            out = new PrintWriter(servidor.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(servidor.getInputStream()));
        } catch (IOException e) {
            System.err.println("Problemas de IO");
        }
    }

    public void lerInformacaoPassagem() throws IOException {
        String mensagem = "C;";

        String selecionarAcao = JOptionPane.showInputDialog("Digite Consultar ou Marcar");
        if (selecionarAcao.equalsIgnoreCase("Marcar")) {
            mensagem = "M;";
        }

        String voo = JOptionPane.showInputDialog("Digite o codigo do voo");
        mensagem += voo + ";";
        String assento = JOptionPane.showInputDialog("Digite o numero do seu assento");
        mensagem += assento + ";";

        out.println(mensagem);
        String resultado = in.readLine();

        mostrarResultado(Integer.parseInt(resultado));
    }

    public void mostrarResultado(int resultado) {
        switch (resultado) {

            case 0 -> JOptionPane.showMessageDialog(null, "Voo disponível");
            case 1 -> JOptionPane.showMessageDialog(null, "Assento indisponível");
            case 2 -> JOptionPane.showMessageDialog(null, "Assento inexistente");
            case 3 -> JOptionPane.showMessageDialog(null, "Voo inexistente");
            case 4 -> JOptionPane.showMessageDialog(null, "Marcação realizada");
            default -> JOptionPane.showMessageDialog(null, "Código desconhecido");
        }
    }
}
