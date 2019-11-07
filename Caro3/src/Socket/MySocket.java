/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author Dau Manh Tuan
 */
public class MySocket {
    
    public static JSONObject sendData(JSONObject data){
        JSONObject x = null;
        try {
            Socket socket = new Socket(Resource.ServerResource.SERVER_NAME, Resource.ServerResource.SERVER_PORT);
            
            data.put("session", Resource.ServerResource.session);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            
            out.writeUTF(data.toString());
            System.out.println(data.toString());
            DataInputStream inp = new DataInputStream(socket.getInputStream());
            String s = inp.readUTF();
            x = new JSONObject(s);
            System.out.println(x.toString());
            out.close();
            inp.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(MySocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }
}
