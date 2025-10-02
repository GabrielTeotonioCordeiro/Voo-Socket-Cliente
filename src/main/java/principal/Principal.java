package principal;

import java.io.IOException;
import javax.swing.JOptionPane;

public class Principal {

    public static void main(String[] args) throws IOException {
        ClienteSocket cliente = new ClienteSocket();
        cliente.comunicarComServidor();
        String continuar = "1";

        while (continuar.equals("1")) {
            cliente.lerInformacaoPassagem();
            continuar = JOptionPane.showInputDialog("Digite 1 para continuar ou outro caractere para sair");

        }
    }
}
