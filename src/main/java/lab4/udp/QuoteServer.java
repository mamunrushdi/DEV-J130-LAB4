package lab4.udp;

import java.io.IOException;

public class QuoteServer {
    public static void main(String[] args) throws IOException {
        new QuoteServerThread().start();
        System.out.println("///Server started. Waiting for client request....");
    }
}
