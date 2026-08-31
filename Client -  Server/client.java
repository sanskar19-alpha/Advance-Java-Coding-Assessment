import java.io.*;
import java.net.*;

public class client{
    public static void main(String[] args) {
        try (
            Socket socket = new Socket("localhost", 5000);
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            System.out.println("Connected to server");

            output.println("Hello from client");

            String response = input.readLine();
            System.out.println("Received from server: " + response);

            System.out.println("Client is running...");
        } catch (IOException e) {
        }
    }
}
