import java.io.*;
import java.net.*;
import java.util.Scanner;
 
public class Client {
    public Client(String address, int port)
    {
        try (Socket socket = new Socket(address, port);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("Connected to the server.");

            // Read user input from the console
            System.out.print("Client: ");

            String userInput = "hello socket";
            System.out.println(userInput);

            // Send the user input to the server
            writer.println(userInput);
    
            // Receive and print the server's response
            String serverResponse = reader.readLine();
            System.out.println("Server: " + serverResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        String ip = "";
        int port = -1;

        try (sc) {
            System.out.print("서버의 ip 를 입력하세요: ");
            ip = sc.nextLine().trim();

            System.out.print("서버의 port 를 입력하세요: ");
            port = sc.nextInt();
        } catch (Exception e) {
            System.out.println();
        }

        new Client(ip, port);
    }
}