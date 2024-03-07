import java.net.*; // all socket stuff is in here
import java.io.*;

public class Server {
    public static int SERVER_PORT = 5000; // arbitrary, but above 1023
    private int counter = 0;

    // Helper method to get the ServerSocket started
    ServerSocket goOnline() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("SERVER online");
        } catch (IOException e) {
            System.out.println("SERVER: Error creating network connection");
        }
        return serverSocket;
    }

    // Handle all requests
    void handleRequests(ServerSocket serverSocket) {
        while (true) {
            Socket socket = null;
            BufferedReader in = null;
            PrintWriter out = null;
            try {
                // Wait for an incoming client request
                socket = serverSocket.accept();
                // At this point, a client connection has been made
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream());
            } catch (IOException e) {
                System.out.println("SERVER: Error connecting to client");
                System.exit(-1);
            }
            // Read in the client's request
            try {
                String request = in.readLine();
                System.out.println("SERVER: Client Message Received: " + request);
                if (request.equals("What Time is It ?")) {
                    out.println(new java.util.Date());
                    counter++;
                } else if (request.equals("How many requests have you handled ?"))
                    out.println(counter++);
                else
                    System.out.println("SERVER: Unknown request: " + request);
                out.flush(); // Now make sure that the response is sent
                socket.close(); // We are done with the client's request
            } catch (IOException e) {
                System.out.println("SERVER: Error communicating with client");
            }
        }
    }

    public static void main(String[] args) {
        Server s = new Server();
        ServerSocket ss = s.goOnline();
        if (ss != null)
            s.handleRequests(ss);
    }
}
