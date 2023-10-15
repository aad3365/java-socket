// A Java program for a Server
import java.net.*;
import java.util.Scanner;
import java.io.*;
 
public class Server
{
    // constructor with port
    public Server(int port)
    {
        // starts server and waits for a connection
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    System.out.println("Client connected");

                    // Read client's message
                    String clientMessage = reader.readLine();
                    System.out.println("Client: " + clientMessage);

                    // Respond to the client
                    writer.println("Hello Client");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        int port = -1;

        try (sc) {
            System.out.print("server 가 구동될 port 를 입력하세요: ");
            port = sc.nextInt();
        } catch (Exception e) {
            System.out.println("port 입력 도중 실패");
        }

        // server 실행
        new Server(port);
    }
}
