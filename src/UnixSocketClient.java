import java.io.*;
import java.net.*;

public class UnixSocketClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket()) {
            // Connect to the Unix socket server
            socket.connect(UnixDomainSocketAddress.of("unix_socket_server.sock"));

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Connected to the server.");

            while (true) {
                // Read user input from the console
                System.out.print("Client: ");
                String userInput = consoleReader.readLine();

                // Send the user input to the server
                writer.println(userInput);

                // Receive and print the server's response
                String serverResponse = reader.readLine();
                System.out.println(serverResponse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
