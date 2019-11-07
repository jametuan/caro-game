 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Socket;

import View.MyButton;
import View.RoomCaro;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author Dau Manh Tuan
 */
public class MyServer implements Runnable{

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(Resource.ServerResource.MY_PORT);
            java.net.Socket socket ;
            while (true) {                
                socket = server.accept();
                DataInputStream inp = new DataInputStream(socket.getInputStream());
                JSONObject xx = new JSONObject(inp.readUTF());
                String method = xx.getString("method");
                if ("tick".equals(method)){
                    int x = xx.getInt("x");
                    int y = xx.getInt("y");
                    String tick = xx.getString("tickxo");
                    MyButton.tick(x, y, tick);
                }
                if ("restart".equals(method)){
                    RoomCaro.MyRoomCaro.reSet(xx);
                }
                if ("win".equals(method)){
                    RoomCaro.MyRoomCaro.alertWin(xx);
                }
                socket.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(MyServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
