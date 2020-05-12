
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class GameClient {

    public static void run() throws IOException {
        String serverAddress = "127.0.0.1";
        int PORT = 8100;
        boolean GAME_LOOP = true;
        Socket socket;
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            socket = new Socket(serverAddress, PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.out.println("Error at Client level!" + e);
        }
        String request;
        Scanner keyboard = new Scanner(System.in);
        while (GAME_LOOP == true) {
            System.out.println("Enter a command");
            do {
                request = keyboard.nextLine();
            } while (!request.equals(" "));
            out.println(request);
            if(request.equals("exit"))
            {
                GAME_LOOP = false;
                break;
            }
            String response = in.readLine();
            if(response.equals("Server stopped"))
                GAME_LOOP = false;
        }

    }
}
