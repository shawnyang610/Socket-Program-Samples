import java.net.Socket;
import java.net.ServerSocket;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.io.IOException;

public class Server {
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream inStream = null;
    
    public Server(int port){
        try{
            server = new ServerSocket(port);
            socket = server.accept();
            inStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            
            String line = "";
            while (!line.equals("over")){
                try{
                    line = inStream.readUTF();
                    System.out.println(line);
                }
                catch(IOException ioe){
                    System.out.println(ioe);
                }
            }
            
            System.out.println("Closing connection");
            
            socket.close();
            inStream.close();
            
            
            
        }
        catch(IOException ioe){
            System.out.println(ioe);
        }
    }

    public static void main(String[] args){
        Server server = new Server(5000);
    }
}
