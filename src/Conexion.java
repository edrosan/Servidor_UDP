import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Conexion implements Runnable {
    DatagramSocket aSocket = null;

    public Conexion(DatagramSocket aSocket){
        this.aSocket = aSocket;
    }

    private static int numeroDeVocales(String frase) {
        int res = 0;
        String fraseMin = frase.toLowerCase();

        for (int i = 0; i < fraseMin.length(); ++i) {
            switch ( fraseMin.charAt(i) ) {
                case 'a', 'á', 'e', 'é', 'i', 'í', 'o', 'ó', 'u', 'ú' -> res++;
                default -> {
                }
            }
        }
        return res;
    }


    @Override
    public void run() {

        byte[] buffer = new byte[32];

        DatagramPacket request = new DatagramPacket(buffer, buffer.length);
        try {
            aSocket.receive(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int puertoCliente = request.getPort();
        InetAddress direccionCliente = request.getAddress();

        System.out.println("Cliente: "+direccionCliente);

        String mensajeRecibido = new String(request.getData());
        System.out.println("\""+mensajeRecibido+"\"");

        String respuesta = "" + numeroDeVocales(new String(request.getData()));
        byte[] bufferRes = respuesta.getBytes();

        System.out.println("Respuesta: "+respuesta);

        DatagramPacket reply = new DatagramPacket(bufferRes, bufferRes.length, direccionCliente, puertoCliente);
        try {
            aSocket.send(reply);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
