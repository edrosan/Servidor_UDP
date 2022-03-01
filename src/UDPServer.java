import java.net.*;

public class UDPServer{

    public static void main(String args[]){
        final int puerto = 6789;
        DatagramSocket aSocket = null;

        try {
            aSocket = new DatagramSocket(puerto);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        System.out.println("Servidor iniciado.");

        while(true){
            new Thread(new Conexion(aSocket)).start();
        }

    }
}