package Socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.json.JSONObject;

import Controller.AccountController;
import Controller.RoomController;
import Controller.SessionController;


/**
 * MyThread
 */
public class MyThread implements Runnable {

    private Socket socket = null;

    public MyThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        DataInputStream inp;
        DataOutputStream out;
        try {
            inp = new DataInputStream(socket.getInputStream());
            String s= inp.readUTF();
            JSONObject data = new JSONObject(s);
            System.out.println(s);
            String method = data.getString("method");
            JSONObject x = new JSONObject();
            switch (method) {
                case "connect":
                    x = SessionController.createSession(data);
                    break;
                case "disconnect":
                    x = SessionController.dropSession(data);
                    break;
                case "register":
                    x = AccountController.register(data);
                    break;
                case "login":
                    x = SessionController.logIn(data);
                    break;
                case "logout":
                    x = SessionController.logOut(data);
                    break;
                case "crRoom":
                    x = RoomController.createRoom(data);
                    break;
                case "joinRoom": 
                    x = RoomController.joinRoom(data);
                    break;
                case "tick":
                    x = RoomController.tick(data);
                    break;
                case "outRoom":
                    x = RoomController.outRoom(data);
                    break;
              
                case "getAllRoom":
                    x = RoomController.getAllRoom(data);
                    break;
                case "getTop5Account":
                    x = AccountController.getTop5Acount();
                    break;
                case "getAccount":
                    x = AccountController.getAccount(data);
                    break;
                
            }
            out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(x.toString());
            System.out.println(x.toString());
            inp.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

    }

    
}