import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class UnixSocketClient {
    public static void main(String[] args) {
        String socketPath = "/tmp/unix_socket.sock";

        try (SocketChannel channel = SocketChannel.open(StandardProtocolFamily.UNIX)) {
            // Connect to the Unix socket server
            channel.connect(UnixDomainSocketAddress.of(socketPath));
            System.out.println("socket connected");

            String message = "hello unix socket world";
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            buffer.clear();
            buffer.put(message.getBytes());
            buffer.flip();

            while (buffer.hasRemaining()) {
                channel.write(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }
}
