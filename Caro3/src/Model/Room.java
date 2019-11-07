/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Resource.ServerResource;
import Socket.MySocket;
import View.MyButton;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Dau Manh Tuan
 */
public class Room {
    public String name = null;
    public String host = null;
    public boolean state = true;
   
    public static JSONArray getAllRomm(){
        JSONObject x = new JSONObject();
        x.put("method", "getAllRoom");
        
        JSONObject answer = MySocket.sendData(x);
        
        JSONArray arr = answer.getJSONArray("rooms");
        return arr;
    }
    public static JSONObject tick(int x,int y){
        JSONObject xx = new JSONObject();
        xx.put("method", "tick");
        xx.put("x", x);
        xx.put("y", y);
        return MySocket.sendData(xx);
    }
    public static JSONObject createRoom(){
        JSONObject x = new JSONObject();
        x.put("method", "crRoom");
       
        
        
        return MySocket.sendData(x);
    }
    public static JSONObject joinRoom(String room){
        JSONObject x = new JSONObject();
        x.put("method", "joinRoom");
        x.put("room", room);
         InetAddress inetAddress;
        try {
            inetAddress = InetAddress.getLocalHost();
            x.put("host", inetAddress.getHostAddress());
            x.put("port", ServerResource.MY_PORT);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
        }
        return MySocket.sendData(x);
    }
    public static void outRoom(){
        JSONObject x = new JSONObject();
        x.put("method", "outRoom");
        MySocket.sendData(x);
    }
    
}
