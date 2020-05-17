package servicioUDP.servidorUDP;

import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorUDP {

    final static int PUERTO = 5301;
    static byte[] buffer = new byte[1024];

    public static void main(String[] args){
        try {
            //Abre un socket de escucha para el servidor
            System.out.println("Servidor UDP iniciado");
            DatagramSocket scUDP = new DatagramSocket(PUERTO);
            //Define el datagrama.
            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
            while(true) {
                //Recibe la peticion
                scUDP.receive(peticion);
                System.out.println("Datagrama recibido");
                //Decodifica quien lo envío y el mensaje.
                InetAddress direccionC = peticion.getAddress();
                int puertoC = peticion.getPort();
                String mensaje = new String(peticion.getData());
                //Realiza la accion que debe hacer y cargado en el buffer
                buffer = mensaje.toUpperCase().getBytes();
                //Elavora el datagrama de respuesta.
                DatagramPacket repuesta = new DatagramPacket(buffer, buffer.length, direccionC, puertoC);
                scUDP.send(repuesta);
                System.out.println("Datagrama respuesta enviada");
            }

        } catch (Exception e) {
            Logger.getLogger(ServidorUDP.class.getName()).log(Level.SEVERE, null, e);
        }

    }
}
