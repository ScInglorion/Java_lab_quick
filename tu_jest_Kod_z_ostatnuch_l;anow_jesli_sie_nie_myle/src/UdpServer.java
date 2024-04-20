import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Typy.*;

public class UdpServer {
    DatagramSocket socket = null;
    byte[] buffer = new byte[1024];

    public UdpServer(int port) throws SocketException{
      socket = new DatagramSocket(port);
      System.out.println("Server ready");
    }
    public static void main(String[] args) {
      if(args.length < 1){
        args = new String[1];
        args[0] = "9876";
      }

      try {
        // args contain message content and server hostname
        UdpServer server = new UdpServer(Integer.parseInt(args[0]));
        DatagramPacket p = server.get_packet();

        // deserializacja
        Packet data = (Packet)Tools.deserialize(p.getData());
        // obsluga komunikatu
        data.process();
        server.send_reply(p);
      } catch (SocketException ex) {
        Logger.getLogger(UdpServer.class.getName()).log(Level.SEVERE, null, ex);
      } catch (IOException ex) {
        Logger.getLogger(UdpServer.class.getName()).log(Level.SEVERE, null, ex);
      } catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } 
      
    }


    private void send_reply(DatagramPacket request) throws IOException {
      DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), 
      		request.getAddress(), request.getPort());
      socket.send(request);
    }

    private DatagramPacket get_packet() throws IOException {
      DatagramPacket request = new DatagramPacket(buffer, buffer.length);
      System.out.println("Waiting for request...");
      socket.receive(request);
      String t = new String(request.getData(), 0, request.getLength());
      System.out.println("Received: " + t);
      return request;
    }
}

