package Controller;

import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import Model.Account;
import Model.Room;
import Model.Session;
import Socket.MySocket;


/**
 * RoomController
 */
public class RoomController {
    
    
    public static JSONObject tick(JSONObject x){
        Room room = Room.getRoom(Session.getSession(x.getString("session")).room);
        String user = Session.getSession(x.getString("session")).user;
        if (room.getState()==false){
            if ((room.turn=="x" && user == room.player1) || (room.turn=="0" && user == room.player2)){
            String answer = room.tick(x.getInt("x"), x.getInt("y"));
            if (answer == null) return new JSONObject().put("accept", false);
            else 
            {
                JSONObject y = new JSONObject();
                y.put("x",x.getInt("x"));
                y.put("y",x.getInt("y"));
                y.put("method", "tick");
                if (room.turn=="x")
                    { 
                        y.put("tickxo","0");
                        MySocket.sendData(y, room.host1, room.port1);
                       
                    } 
                    else {
                        y.put("tickxo","x");
                        MySocket.sendData(y, room.host2, room.port2);
                      
                    }
                if (room.checkWin(x.getInt("x"), x.getInt("y"))){
                    y = new JSONObject();
                    y.put("method", "win");
                    y.put("player1", room.player1);
                    y.put("player2", room.player2);
                    if (room.turn=="x") {
                        y.put("win",room.player2);
                        Account.increasePoint(room.player2);
                    }
                    else {
                        y.put("win",room.player1);
                        Account.increasePoint(room.player1);
                    }
                    MySocket.sendData(y, room.host1, room.port1);
                    MySocket.sendData(y, room.host2, room.port2);
                      
                    
                }
                return new JSONObject().put("accept", true).put("tick", answer);
            }

        } else return new JSONObject().put("accept", false);
        
        }
        else {
            return new JSONObject().put("accept", false);
        }
    }
    public static JSONObject joinRoom(JSONObject x){
        JSONObject answer = new JSONObject();
        Room room = Room.getRoom(x.getString("room"));
        String user = Session.getSession(x.getString("session")).user;
        if (room ==null || room.getState()==false || Session.getSession(x.getString("session")).room != null) {
            answer.put("accept", false);
            return answer;
        }else{
            room.resetArr();
            String host = room.getHostPlayer();
            int port = room.getPortPlayer();
            Session.getSession(x.getString("session")).room=x.getString("room");
            room.addPlayer(user, x.getString("host"),x.getInt("port"));
            answer.put("accept", true);
            answer.put("player1", room.player1);
            answer.put("player2", room.player2);
            if (host != null) {
                MySocket.sendData(answer.put("method","restart"), host, port);
            }
            return answer;
        }
    }
    public static JSONObject outRoom(JSONObject x){
        JSONObject answer = new JSONObject();
        Room room = Room.getRoom(Session.getSession(x.getString("session")).room);
        
        String user = Session.getSession(x.getString("session")).user;
        room.resetArr();
        if (room.player1 == user) {
            room.player1 = null;
            room.host1 = null;
            room.port1 = -1;
        }
        else {
            room.player2 = null;
            room.host2 = null;
            room.port2 = -1;
        }

        if (room.isHasPlayer() == false){
            Room.dropRoom(Session.getSession(x.getString("session")).room);
        }
        else {
            String host = room.getHostPlayer();
            int port = room.getPortPlayer();
            if (host != null) {
                
                answer.put("player1", room.player1);
                answer.put("player2", room.player2);
                MySocket.sendData(answer.put("method","restart"), host, port);
            }
        }
        Session.getSession(x.getString("session")).room = null;
        answer.put("accept", true);
        return answer;
    }
    public static JSONObject createRoom(JSONObject x){
        JSONObject y = new JSONObject();
        String index = Room.createRoom();
        
       // Session.getSession(x.getString("session")).room = index;
       
      //  room.name = x.getString("name");
    
        y.put("accept", true);
        y.put("room",index);
        return y;
    }

    public static JSONObject getAllRoom(JSONObject x){
        
        JSONObject y = new JSONObject();
        JSONArray z = new JSONArray();
        y.put("accept", true);
        Set<String> keys = Room.list.keySet();
        for (String key : keys) {
            JSONObject t = new JSONObject();
            t.put("room", key);
            t.put("name", Room.getRoom(key).name);
            t.put("state", Room.getRoom(key).getState());
            z.put(t);
        }
        y.put("rooms", z);
        return y;
    }
}