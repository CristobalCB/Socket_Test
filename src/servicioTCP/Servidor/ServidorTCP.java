package servicioTCP.Servidor;


import servicioTCP.cliente.ClienteTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorTCP {

    private static final int PUERTO = 5300;

    public static void main(String[] args){
        ServerSocket server = null;
        Socket sc = null;
        DataInputStream in;
        DataOutputStream out;
        try {
            //Se inicia el servidor.
            server = new ServerSocket(PUERTO);
            while (true){
                //Espera a que algun cliente se conecte y le asigna el socket;
                sc = server.accept();
                System.out.println("Cliente Conectado");
                //Se crean dos objetos para la comunicación una vez que la coneccion se establecio.
                in = new DataInputStream(sc.getInputStream());
                out = new DataOutputStream(sc.getOutputStream());
                //Usando los objetos se recibe el mensaje del cliente conectado.
                String text = in.readUTF();
                System.out.println("Mensaje modificado");
                //Y se envia de vuelta con la modificacion de string para mayusculas.
                out.writeUTF(text.toUpperCase());
                //Se cierra el socket del cliente.
                sc.close();
                System.out.println("Cliente desconectado");
                //Luego se reinicia para admitir a un nuevo cliente.
            }
        }catch(IOException ioe){
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ioe);
        }

    }
}
