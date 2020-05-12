
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {

    public static final int PORT = 8100;
    ServerSocket socket;

    public GameServer() throws IOException {
        this.socket = null;
        try {
            this.socket = new ServerSocket(this.PORT);
            Socket s = this.socket.accept();
            new ClientThread(s).start();
        } catch (IOException e) {
            System.out.println("Error at server level!" + e);
        } finally {
            this.socket.close();
        }
    }
}
