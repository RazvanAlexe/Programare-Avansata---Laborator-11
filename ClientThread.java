
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientThread extends Thread {

    private final Socket socket;
    
    public ClientThread(Socket s) {
        this.socket = s;
    }

    @Override
    public void run() {
        boolean CLIENT_LOOP = true;
        BufferedReader in = null;
        PrintWriter out = null;
        
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Error at ClientThread level!" + e);
        } finally {
            try {
                this.socket.close();
            } catch (IOException e) {
                System.out.println("Error at ClientThread level!" + e);
            }
        }
        while(CLIENT_LOOP == true){
            String request = "";
            String response = "";
            try {
                request = in.readLine();
            } catch (IOException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(request.equals("stop")){
                CLIENT_LOOP = false;
                response = "Server stopped";
            }
            else
            {
                response = "response";
            }
            out.println(response);
            out.flush();
        }
    }
}
