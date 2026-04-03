import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(5001);
        System.out.println("Server started...");

        while (true) {
            Socket s = server.accept();

            BufferedReader in = new BufferedReader(
                new InputStreamReader(s.getInputStream())
            );
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);

            String str = in.readLine();
            System.out.println("Received: " + str);

            out.println(str.toUpperCase());

            s.close();
        }
    }
}