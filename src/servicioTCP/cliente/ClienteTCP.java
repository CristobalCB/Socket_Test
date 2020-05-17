package servicioTCP.cliente;

import javax.swing.JOptionPane;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteTCP {

    final static String HOST = "127.0.0.1";
    final static int PUERTO = 5300;

    public static void main(String[] args) {
        DataInputStream in;
        DataOutputStream out;
        try {
            //Se crea un socket hacia el servidor.
            Socket sc = new Socket(HOST, PUERTO);
            //Se crean dos objetos para la comunicacion entre cliente/servidor.
            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());
            //Se envia un mensaje que el usuario escribe (a travez de JOptionPanel)
            out.writeUTF(JOptionPane.showInputDialog("Escribe el texto"));
            //Se recibe el mensaje y luego se muestra con JOptionPanel
            String capital = in.readUTF();
            JOptionPane.showMessageDialog(null, capital, "mensaje devuelto", JOptionPane.INFORMATION_MESSAGE);
            //Se cierra el socket del cliente.
            sc.close();
        } catch (IOException ioe){
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ioe);
        }
    }
}
