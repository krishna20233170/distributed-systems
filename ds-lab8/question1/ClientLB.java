import java.io.*;
import java.net.*;

public class ClientLB {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 6000);

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

        System.out.println("Response: " + in.readLine());

        s.close();
    }
}