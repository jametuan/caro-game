package Model;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Account
 */
public class Account {
    public String user = null;
    public String pswd = null;
    public String name = null;
    public int point = 0;

    public Account(String user, String password, String name, int point) {
        this.point = point;
        this.user = user;
        this.pswd = password;
        this.name = name;
    }
    public Account(String name, int point){

    }
    public Account(){};
    public static void insertAccount(Account account) {
        
        String sql = " insert into account values('" + account.user + "'," + "'" + account.pswd + "'," + "'" + account.name + "'," + account.point
                + ");";
        try {
            ConnectorDatabase.getConnect().createStatement().executeUpdate(sql);
            

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    public static Account sellectAccount(String user){
        String sql = "SELECT * FROM account WHERE user = '"+user+"'";
        ResultSet rSet = null;
        Account account = new Account();
        try {
            rSet = ConnectorDatabase.getConnect().createStatement().executeQuery(sql);
            if (rSet.next()){
            account.point= rSet.getInt("point");
            account.name= rSet.getString("name");
            account.pswd=rSet.getString("pswd");
            account.user=user;
           
           }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return account;
    }
    
    public static JSONArray getTopAccounts(){
        String sql = "SELECT user,point FROM account order by point desc limit 5";
        JSONArray x = new JSONArray();
        ResultSet rSet = null;
        try {
            rSet = ConnectorDatabase.getConnect().createStatement().executeQuery(sql);
            while (rSet.next()) {
                x.put(new JSONObject().put("user", rSet.getString("user")).put("point", rSet.getString("point")));
            }
            
        } catch (SQLException e) {
           
            e.printStackTrace();
        }
        return x;
    }
    public static void increasePoint(String user){
        
        String sql = "UPDATE ACCOUNT SET point= point +1  WHERE user = '"+user+"'";
        try {
            ConnectorDatabase.getConnect().createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

   
}