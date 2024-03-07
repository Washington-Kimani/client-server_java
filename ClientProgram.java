import java.net.*;
import java.io.*;

public class ClientProgram {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    // Make a connection to the server
    private void connectToServer() {
        try {
            socket = new Socket(InetAddress.getLocalHost(), Server.SERVER_PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("CLIENT: Cannot connect to server");
            System.exit(-1);
        }
    }

    // Disconnect from the server
    private void disconnectFromServer() {
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("CLIENT: Cannot disconnect from server");
        }
    }

    // Ask the server for the current time
    private void askForTime() {
        connectToServer();
        out.println("What Times is It ?");
        out.flush();
        try {
            String time = in.readLine();
            System.out.println("CLIENT: The times is " + time);
        } catch (IOException e) {
            System.out.println("CLIENT: Cannot receive time from server");
        }
        disconnectFromServer();
    }

    // Ask the server for the number of requests obtained
    private void askForNumberOfRequests() {
        connectToServer();
        out.println("How many requests have you handled ?");
        out.flush();
        int count = 0;
        try {
            count = Integer.parseInt(in.readLine());
        } catch (IOException e) {
            System.out.println("CLIENT: Cannot receive num requests from server");
        }
        System.out.println("CLIENT: The number of requests are " + count);
        disconnectFromServer();
    }

    // Ask the server to order a pizza
    private void askForAPizza() {
        connectToServer();
        out.println("Give me a pizza");
        out.flush();
        disconnectFromServer();

    }

    private static void Delay() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
    }

    public static void main(String[] args) {
        ClientProgram c = new ClientProgram();
        Delay();
        c.askForTime();
        Delay();
        c.askForNumberOfRequests();
        Delay();
        c.askForAPizza();
        Delay();
        c.askForTime();
        Delay();
        c.askForNumberOfRequests();
    }
}
