package Model;

import java.util.Random;
import java.util.TreeMap;


/**
 * Session
 */
public class Session {
    
    public String user = null;
    public String room = null;

    
    public static String createSession(){
        Random rd = new Random();
        Integer index;
        do {
            index = rd.nextInt(100000);
            if (!list.containsKey(index.toString())){
                
                list.put(index.toString(), new Session());
                return index.toString();
            }
            else continue;
        } while (true);
    }
    

    private static TreeMap<String, Session> list = new TreeMap<>();
    public static Session getSession(String index){
        return list.get(index);
    }
    public static boolean dropSession(String index){
        if (list.containsKey(index)){
            list.remove(index);
            return true;
        }
        return false;
    }
   
}