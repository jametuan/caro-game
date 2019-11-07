/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Socket.MySocket;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Dau Manh Tuan
 */
public class Account {
    public String user = null;
    public String pswd = null;
    public String name = null;
    public int point = 0;
    
    public static boolean register(String user, String pswd, String name){
        JSONObject x = new JSONObject();
        x.put("method", "register");
        x.put("user", user);
        x.put("pswd", pswd);
        x.put("name", name);
        return MySocket.sendData(x).getBoolean("accept");
    }
    public static JSONArray getTop5(){
        JSONObject x = new JSONObject();
        x.put("method", "getTop5Account");
        return MySocket.sendData(x).getJSONArray("top");
    }
    public static JSONObject getAccount(String user) {
        JSONObject x = new JSONObject();
        x.put("method", "getAccount");
        x.put("user", user);
        return MySocket.sendData(x);
    }
}
