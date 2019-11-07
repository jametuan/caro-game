package Socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.JSONObject;

/**
 * MySocket
 */
public class MySocket {

    public static void sendData(JSONObject x, String host, int port) {
        try {
            Socket socket = new Socket(host, port);
            DataOutputStream out =  new DataOutputStream(socket.getOutputStream());
            System.out.println(x.toString());
            out.writeUTF(x.toString());
            out.close();
            socket.close();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}