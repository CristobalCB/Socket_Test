package servicioUDP.clienteUDP;

import servicioTCP.cliente.ClienteTCP;

import javax.swing.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteUDP {

    private final static int PUERTO_S = 5301;
    private static byte[] buffer = new byte[1024];

    public static void main(String[] args){
        try {
            //Creacion de la dirección del servidor.
            InetAddress direccionS = InetAddress.getByName("Localhost");
            //Creación del socket con puerto automatico.
            DatagramSocket scUDP = new DatagramSocket();
            //Recepción del mensaje del usuario y cargado a buffer.
            String mensaje = JOptionPane.showInputDialog("Escribe el texto");
            buffer = mensaje.getBytes();
            //Creacion del datagrama a enviar y posterior enviado.
            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length, direccionS, PUERTO_S);
            scUDP.send(peticion);
            System.out.println("Mensaje enviado");
            //Creacion del datagrama de respondido y su posterior recibimiento.
            DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length);
            scUDP.receive(respuesta);
            System.out.println("Respuesta recibida.");
            //Conversión del datagrama en el string y presentación en JOptionPanel.
            mensaje = new String(respuesta.getData());
            JOptionPane.showMessageDialog(null, mensaje, "mensaje devuelto", JOptionPane.INFORMATION_MESSAGE);
            //Cierre del socket del cliente.
            scUDP.close();
        } catch (Exception e) {
            Logger.getLogger(ClienteUDP.class.getName()).log(Level.SEVERE, null, e);

        }
    }
}
