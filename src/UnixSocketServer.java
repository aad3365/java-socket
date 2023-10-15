import java.io.*;
import java.net.*;

public class UnixSocketServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket()) {
            // Create a Unix socket by binding to a local file
            serverSocket.bind(UnixDomainSocketAddress.of("unix_socket_server.sock"));

            System.out.println("Server is listening on unix_socket_server.sock...");

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    System.out.println("Client connected");

                    // Read messages from the client
                    String clientMessage;
                    while ((clientMessage = reader.readLine()) != null) {
                        System.out.println("Client: " + clientMessage);

                        // Echo the message back to the client
                        writer.println("Server: " + clientMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
