package SingleThreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {

    public void run() throws IOException, UnknownHostException {
        int port = 8010;
        ServerSocket socket = new ServerSocket(port);
        socket.setSoTimeout(20000);  // Set timeout of 20 seconds

        System.out.println("Server is listening on port: " + port);
        while (true) {
            Socket acceptedConnection = socket.accept();
            System.out.println("Connected to " + acceptedConnection.getRemoteSocketAddress());

            PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream(), true);
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));

            // Read client message
            String clientMsg = fromClient.readLine();
            System.out.println("Client: " + clientMsg);

            // Respond to client
            toClient.println("Hello from Server!");

            // Close resources
            fromClient.close();
            toClient.close();
            acceptedConnection.close();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
