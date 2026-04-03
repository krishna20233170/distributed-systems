import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 5001);

        BufferedReader input = new BufferedReader(
            new InputStreamReader(System.in)
        );
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
            new InputStreamReader(s.getInputStream())
        );

        System.out.print("Enter string: ");
        String str = input.readLine();

        out.println(str);

        String response = in.readLine();
        System.out.println("Uppercase: " + response);

        s.close();
    }
}