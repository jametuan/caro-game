/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Room;
import java.awt.Button;
import javax.swing.JOptionPane;
import org.json.JSONObject;

/**
 *
 * @author Dau Manh Tuan
 */
public class MyButton extends Button{
    public int x ;
    public int y;
    public static MyButton[][] arr = new MyButton[20][20];
    public static void reSet(){
        for (int i=0;i<20;i++)
            for (int j=0;j<20;j++) arr[i][j].setLabel("");
    }
    public MyButton(int x,int y){
        this.x =x;
        this.y = y;
        arr[x][y]= this;
        arr[x][y].setLabel("");
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (eventTick() == false) 
                    JOptionPane.showMessageDialog(null,"Ban khong duoc danh");
                   
            }
        });
    }
    private boolean eventTick(){
        JSONObject x =  Room.tick(this.x, this.y);
        if (x.getBoolean("accept")){
            MyButton.tick(this.x,this.y,x.getString("tick"));
            RoomCaro.MyRoomCaro.setTurn(x.getString("tick"));
            return true;
        }
        return false;
    }
    public static void  tick(int x,int y,String tick){
        arr[x][y].setLabel(tick);
        RoomCaro.MyRoomCaro.setTurn(tick);
    }
}
