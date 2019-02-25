import java.net.Socket;
import java.net.UnknownHostException;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client {
    private Socket socket = null;
    private BufferedReader inStream = null;
    private DataOutputStream outStream = null;
    
    public Client(String ip, int port){
        try {
            socket = new Socket(ip, port);
            System.out.println("connected");
            
            inStream = new BufferedReader(new InputStreamReader(System.in));
            outStream = new DataOutputStream(socket.getOutputStream());
        } 
        catch(UnknownHostException hostException){
            System.out.println(hostException);
        }
        catch(IOException ioException){
            System.out.println(ioException);
        }
    
    
        String line = "";
        
        while (!line.equals("over")){
            try {
                line = inStream.readLine();
                outStream.writeUTF(line);
            }
            catch(IOException ioException){
                System.out.println(ioException);
            }
        }
        
        try {
            inStream.close();
            outStream.close();
            socket.close();
        }
        catch(IOException ioe){
            System.out.println(ioe);
        }
        
    }

    public static void main(String[] args){
        Client client = new Client("127.0.0.1", 5000);
    }

}
