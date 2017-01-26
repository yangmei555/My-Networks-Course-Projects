
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {
    public static void main(String[] args) throws IOException {
        if (args.length != 2){
            args = new String[2];
            args[0] = "127.0.0.1";
            args[1] = "2345";
        }
        else {
            if (args[0].charAt(0) == '-') {
                args[0] = args[0].substring(11, args[0].length());
            } else if (args[0].charAt(0) == 's') {
                args[0] = args[0].substring(9, args[0].length());
            }
            if (args[1].charAt(0) == '-') {
                args[1] = args[1].substring(13, args[1].length());
            } else if (args[1].charAt(0) == 's') {
                args[1] = args[1].substring(11, args[1].length());
            }
        }

        String hostName = args[0];

        int portNumber = Integer.parseInt(args[1]);


        try (
                Socket echoSocket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        ){
            System.out.println("This is an echo client. It will echo back what you input to the server" +
                    " if the client and server get connected successfully. If you want to quit this program" +
                    ", press \"quit\".\n");
            String userInput;
            while ((userInput = stdIn.readLine()) != null){
                if (userInput.toUpperCase().equals("QUIT")){
                    break;
                }
                out.println(userInput);
                System.out.println("echo: " + in.readLine());
            }
        } catch (UnknownHostException e){
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e){
            System.err.println("Could't get I/O for the connection to " + hostName);
            System.exit(1);
        }
    }
}
