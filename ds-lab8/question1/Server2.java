import java.io.*;
import java.net.*;
import java.util.Random;

public class Server2 {
    static int load = 0;

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(5002);
        System.out.println("Server1 running...");

        while (true) {
            Socket s = server.accept();
            load++;

            BufferedReader in = new BufferedReader(
                new InputStreamReader(s.getInputStream())
            );
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);

            String request = in.readLine();

            if (request.equals("LOAD")) {
                out.println(load);
            } else {
                out.println(request.toUpperCase());
            }

            load--;
            s.close();
        }
    }
}