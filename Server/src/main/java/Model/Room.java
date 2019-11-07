package Model;

import java.util.Random;
import java.util.TreeMap;



/**
 * Room
 */
public class Room {

    
    public String name = "Chuc mung nam moi";
    public String player1 = null,player2 = null;
    public String host1 = null,host2 = null;
    public String turn = "x";
    public int port1=-1,port2=-1;
    public int[][] arr = new int[20][20];
    public static TreeMap<String, Room> list = new TreeMap<>();
    public Room(){
        resetArr();
    }
    public boolean checkWin(int x,int y){
        int tick = arr[x][y];
        int dau=0,cuoi = 20-1;
        if (y-4>=0) dau = y-4;
        if (y+4<20) cuoi = y+4;
        int first = dau;
        for (int i=dau;i<=cuoi;i++)
            if (arr[x][i]==tick){
                if (i-first+1==5) return true;
            } else{
                first=i+1;
            }
        dau= 0;cuoi = 19;
        if (x-4>=0) dau = x-4;
        if (x+4<20) cuoi = x+4;  
        first = dau;
        for (int i=dau;i<=cuoi;i++)
        if (arr[i][y]==tick){
            if (i-first+1==5) return true;
        } else{
            first=i+1;
        }
        for (int i=0;i<=4;i++){
            dau= x-i;
            cuoi = y-i;
            if (dau<0 || cuoi <0) break;
            boolean kt = true;
            for (int j=0;j<=4;j++)
            if (dau+j >19 ||cuoi+j> 19 || arr[dau+j][cuoi+j]!=tick) { kt = false; break;}
            if (kt == true) return true;
        }
        for (int i=0;i<=4;i++){
            dau= x-i;
            cuoi = y+i;
            if (dau<0 || cuoi >19) break;
            boolean kt = true;
            for (int j=0;j<=4;j++)
            if (dau+j >19 ||cuoi-j< 0 || arr[dau+j][cuoi-j]!=tick) { kt = false; break;}
            if (kt == true) return true;
        }
        return false;
    }
    public String tick(int x,int y){
        if (arr[x][y]==-1){
            if (turn == "x") arr[x][y]=1;
            else arr[x][y]=0;
            if (turn =="x" ) {
                turn ="0";return "x";}
            else{
                turn = "x"; return "0";
            }
        }
        else return null;
        
    }
    public void addPlayer(String user, String host,int port){
        if (player1 == null){
            player1 = user;
            host1 = host;
            port1 = port;
        }
        else{
            player2 = user;
            host2 = host;
            port2 = port;
        }
    }
    public String getHostPlayer(){
        if (player1 == null) return host2;else return host1;
    }
    public int getPortPlayer(){
        if (port1 == -1) return port2;else return port1;
    }
    public boolean isHasPlayer(){
        if (player1==null && player2 == null) return false;
        else return true;
    }
    public void resetArr() {
        turn = "x";
        for (int i=0;i<20;i++)
        for (int j=0;j<20;j++) arr[i][j]=-1;
    }
    public boolean getState(){
        if (player1 == null || player2==null)  return true;
        else return false;
    }



    public static String createRoom(){
        Random rd = new Random();
        Integer index;
        do {
            index = rd.nextInt(100000);
            if (!list.containsKey(index.toString())){
                
                list.put(index.toString(), new Room());
                return index.toString();
            }
            else continue;
        } while (true);
    }
    
    public static Room getRoom(String index){
        return list.get(index);
    }
    public static boolean dropRoom(String index){
        if (list.containsKey(index)){
            list.remove(index);
            return true;
        }
        return false;
    }
}