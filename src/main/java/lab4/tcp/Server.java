package lab4.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        try {
            ServerSocket serverSocket = new ServerSocket(9000);
            while (true) {
                System.out.println("Server started. Ready to receive client request...");
                Socket socket = serverSocket.accept();
                new Thread(() ->  handler(socket)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void handler(Socket socket) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            System.out.println("Client sent:"    );
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            socket.shutdownInput();
            writer.write("Message received.");
            writer.flush();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

}
