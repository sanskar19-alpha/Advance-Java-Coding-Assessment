import java.io.*;
import java.net.*;

public class server {
    public static void main(String[] args) {
        try (
            ServerSocket serverSocket = new ServerSocket(5000);
            Socket socket = serverSocket.accept();
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true)
        ) {
            System.out.println("Server is waiting for client request...");
            System.out.println("Client connected");

            String message = input.readLine();
            System.out.println("Received from client: " + message);

            output.println("Message received by server");
            System.out.println("Server is running...");
        } catch (IOException e) {
        }
    }
}