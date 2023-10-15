// A Java program for a Server
import java.net.*;
import java.util.Scanner;
import java.io.*;
 
public class Server
{
    //initialize socket and input stream
    private Socket socket = null;
    private ServerSocket server = null;
 
    // constructor with port
    public Server(int port)
    {
        // starts server and waits for a connection
        try
        {
            server = new ServerSocket(port, 0, InetAddress.getByName("0.0.0.0"));
            System.out.println("서버 시작");
            System.out.println("client를 기다리는중 ...");
 
            socket = server.accept();
            System.out.println("Client 수락");
            
            System.out.println("연결된 Client 의 ip address 는 " + socket.getInetAddress().toString() + " 입니다.");
            socket.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
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

        // server 실향
        new Server(port);        
    }
}
