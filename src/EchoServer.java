
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class EchoServer {
    public static void main(String[] args) throws IOException{
        if (args.length != 1){
            args = new String[1];
            args[0] = "2345";
        } else {
            if (args[0].charAt(0) == '-') {
                args[0] = args[0].substring(7, args[0].length());
            } else if (args[0].charAt(0) == 'p') {
                args[0] = args[0].substring(5, args[0].length());
            }
        }

        int portNumber = Integer.parseInt(args[0]);

        System.out.println("This is an echo server.\n");

        try (
                ServerSocket serverSocket = new ServerSocket(portNumber);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                out.println(inputLine);
                System.out.println("Receives: " + inputLine);
            }
        } catch (IOException e){
            System.out.println("Exception caught when trying to listen on port " +
            portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
