package lab4.tcp;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        Client clientClass = new Client();

        System.out.print("Please write your text (to close, type 'exit'): ");
        Scanner scanner = new Scanner(System.in);
        boolean isOpen = true;
        try {
            while (isOpen) {
                String text = scanner.nextLine();
                if (text.equals("exit")) {
                    isOpen = false;
                } else {
                    String response = clientClass.send(text);
                    System.out.println("Reply received from server: " + response);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public String send(String text) throws IOException {
        Socket socket = new Socket("127.0.0.1", 9000);
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
            writer.write(text);
            writer.flush();
            socket.shutdownOutput();
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) !=null){
                sb.append(line);
            }
            return sb.toString();
        }
    }

//    public static void main(String[] args) {
//        System.out.println("Client started ... ");
//
////        try (
////                // Create client socket
////                Socket socket = new Socket("127.0.0.1", 9090);
//////                Socket socket = new Socket("localhost", 9090);
////                //to send data to server
////                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
////                // to read data coming from server
////                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
////                // to read data from keyboard
////                Scanner scanner = new Scanner(System.in)
////        ) {
////
////            System.out.println("Please insert your text: ");
////
////            String str1, str2;
////            // repeat as long as exit
////            // is not typed at client
////
////            while (!"exit".equals(str1 = scanner.nextLine())) {
////                System.out.println("you typed: " + str1);
////                // send data to server
////                dataOutputStream.writeBytes(str1 + "\n");
////                dataOutputStream.flush();
////
////                // receive from server
////                str2 = bufferedReader.readLine();
////                System.out.println("from server: " + str2);
////            }
////
////        } catch (Exception e) {
////            throw new RuntimeException(e.getMessage());
////        }
//    }
}
