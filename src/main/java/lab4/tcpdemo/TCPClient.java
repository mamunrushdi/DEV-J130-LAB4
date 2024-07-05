package lab4.tcpdemo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPClient {
    public static void main(String argv[]) throws Exception {
        String sentence;
        String modifiedSentence;
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        Socket clientSocket = new Socket("localhost", 6789);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        sentence = inFromUser.readLine();
        outToServer.writeBytes(sentence + '\n');
        modifiedSentence = inFromServer.readLine();
        System.out.println(modifiedSentence);
        clientSocket.close();



    }

    public static void handler(Socket socket) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            String line;
            System.out.println("Получено сообщение:"    );
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            socket.shutdownInput();
            writer.write("Сообщение получено");
            writer.flush();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

}
