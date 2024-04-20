import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Typy.Alarm;
import Typy.Packet;
import Typy.TimeHistory;
import Typy.Tools;


public class UDPClient {
	public static void main(String[] args) {
		DatagramSocket aSocket = null;

		try {
			// args may contain server hostname
			if (args.length < 1) {
				args = new String[1];
				args[0] = new String("localhost");
			}
			byte[] buffer = new byte[1024];
			InetAddress aHost = InetAddress.getByName(args[0]);
			int serverPort = 9876;
			aSocket = new DatagramSocket();
			Short przebieg_sily[] = {1,2,3,4};
			Alarm alarmm = new Alarm("komora", "temperatura za duza", 1L, 2, 22.5, Alarm.eScale.BOTH );
			TimeHistory<Short, Float> ss = new TimeHistory<>("Czujnik", "Sygnal", 1L, 1, "N", (Float)0.001F, przebieg_sily, (short)10); 
        	System.out.println("Timehistory check:\n" + ss.toString());
        	byte bytes_tools[] = Tools.serialize(alarmm); 
			DatagramPacket request = new DatagramPacket(bytes_tools, bytes_tools.length, aHost, serverPort);
			aSocket.send(request);
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
			aSocket.receive(reply);
			try {
				Packet rd = (Packet) Tools.deserialize(reply.getData());
				System.out.println("Odebrano obiekt: " + rd);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Reply:\n " + new String(reply.getData(), 0, reply.getLength()));
			
		} catch (SocketException ex) {
			Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
		} catch (UnknownHostException ex) {
			Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			aSocket.close();
		}
	}
}