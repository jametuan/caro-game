/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Resource.ServerResource;
import Socket.MySocket;
import org.json.JSONObject;

/**
 *
 * @author Dau Manh Tuan
 */
public class Session {
    public static String connectServer(){
        JSONObject x = new JSONObject();
        x.put("method", "connect");
        return MySocket.sendData(x).getString("session");
    }
    public static void disconnectServer(){
        JSONObject x = new JSONObject();
        x.put("method", "disconnect");
        MySocket.sendData(x);
    }
    public static boolean logIn(String user, String pswd){
        JSONObject x = new JSONObject();
        x.put("method", "login");
        x.put("user", user);
        x.put("pswd", pswd);
        return MySocket.sendData(x).getBoolean("accept");
    }
    public static void logOut() {
        JSONObject x = new JSONObject();
        x.put("method", "logout");
        MySocket.sendData(x);
    }
}
