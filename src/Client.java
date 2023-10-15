import java.io.*;
import java.net.*;
import java.util.Scanner;
 
public class Client {
    private Socket socket = null;
 
    public Client(String address, int port)
    {
        try {
            socket = new Socket(address, port);
            System.out.println("연결 성공");
        }
        catch (UnknownHostException u) {
            System.out.println(u);
            return;
        }
        catch (IOException i) {
            System.out.println(i);
            return;
        }
 
        try {
            socket.close();
        }
        catch (IOException i) {
            System.out.println(i);
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