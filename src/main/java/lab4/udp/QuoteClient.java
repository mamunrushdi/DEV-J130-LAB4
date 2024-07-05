package lab4.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class QuoteClient {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        do{

            System.out.print("To receive a Qoute, please press enter. To exit press any latter.");
             userInput = scanner.nextLine();
            // get a datagram socket
            DatagramSocket socket = new DatagramSocket();

            // send request
            byte[] buf = new byte[256];
            InetAddress address =  InetAddress.getLocalHost();
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
            socket.send(packet);

            // get response
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

            // display response
            String received = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Quote of the Moment: " + received);

            socket.close();
        }
        while (userInput.isEmpty());


    }
}
