import java.net.*;
import java.io.*;
import java.util.Scanner;

public class UDPClient{

    public static void main(String args[]){
        DatagramSocket aSocket = null;
        Scanner entrada = new Scanner(System.in);

        try {
            InetAddress aHost = InetAddress.getLocalHost();
            int serverPort = 6789;
            byte[] buffer = new byte[1024];

            aSocket = new DatagramSocket();

            String mensaje = "";

            while(!mensaje.equals("END")){
                mensaje = entrada.nextLine();
                if(!mensaje.equals("END")) {
                    byte[] m = mensaje.getBytes();

                    DatagramPacket request = new DatagramPacket(m, mensaje.length(), aHost, serverPort);
                    aSocket.send(request);

                    System.out.println("Mensaje enviado: \"" + mensaje + "\"");

                    DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                    aSocket.receive(reply);

                    String mensajeRecibido = new String(reply.getData());
                    System.out.println("Numero de vocales: " + mensajeRecibido);
                }else{
                System.out.println("Saliendo.....");
                }
            }

            aSocket.close();

        }catch (SocketException e){System.out.println("Socket: " + e.getMessage());
        }catch (IOException e){System.out.println("IO: " + e.getMessage());
        }finally {if(aSocket != null) aSocket.close();}
    }
}