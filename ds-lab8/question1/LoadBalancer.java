import java.io.*;
import java.net.*;

public class LoadBalancer {

    static int getLoad(int port) throws Exception {
        Socket s = new Socket("localhost", port);

        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
            new InputStreamReader(s.getInputStream())
        );

        out.println("LOAD");
        int load = Integer.parseInt(in.readLine());

        s.close();
        return load;
    }

    static String sendToServer(String msg, int port) throws Exception {
        Socket s = new Socket("localhost", port);

        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
            new InputStreamReader(s.getInputStream())
        );

        out.println(msg);
        String response = in.readLine();

        s.close();
        return response;
    }

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(6000);
        System.out.println("Load Balancer running...");

        while (true) {
            Socket client = server.accept();

            BufferedReader in = new BufferedReader(
                new InputStreamReader(client.getInputStream())
            );
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);

            String msg = in.readLine();

            int load1 = getLoad(5001);
            int load2 = getLoad(5002);

            int chosenPort = (load1 < load2) ? 5001 : 5002;

            System.out.println("Routing to server on port " + chosenPort);

            String response = sendToServer(msg, chosenPort);

            out.println(response);

            client.close();
        }
    }
}