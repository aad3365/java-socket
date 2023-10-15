import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class UnixSocketServer {
    public static void main(String[] args) {
        //String socketPath = "/home/park/workspace/java-socket/unix_socket.sock";
        String socketPath = "/tmp/unix_socket.sock";

        try (ServerSocketChannel serverChannel = ServerSocketChannel.open(StandardProtocolFamily.UNIX)) {
            // Create a Unix socket by binding to a local file
            UnixDomainSocketAddress socketAddress = UnixDomainSocketAddress.of(socketPath);
            serverChannel.bind(socketAddress);
            
            System.out.println("Server is listening on unix_socket.sock...");
            try (SocketChannel channel = serverChannel.accept()) {
                while (true) {
                    readSocketMessage(channel)
                        .ifPresent(message -> System.out.printf("[Client message] %s", message));
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                Files.deleteIfExists(Path.of(socketPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static Optional<String> readSocketMessage(SocketChannel channel) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int bytesRead = channel.read(buffer);

        if (bytesRead < 0)
            return Optional.empty();

        byte[] bytes = new byte[bytesRead];

        buffer.flip();
        buffer.get(bytes);
        String message = new String(bytes);

        return Optional.of(message);
    }
}
