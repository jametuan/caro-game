package Controller;



import org.json.JSONObject;

import Model.Account;
import Model.Session;


/**
 * AccountController
 */
public class AccountController {

    public static JSONObject register(JSONObject x){
        
        JSONObject y = new JSONObject();
        Account account = Account.sellectAccount(x.getString("user"));
        if (account.user== null) {
            account.user = x.getString("user");
            account.pswd = x.getString("pswd");
            account.name = x.getString("name");
            Account.insertAccount(account);
            return y.put("accept", true);
        } else return y.put("accept", false);

    }

    public static JSONObject getTop5Acount() {
        JSONObject y = new JSONObject();
        y.put("accept", true);
        y.put("top", Account.getTopAccounts());
        return y;
    }
    public static JSONObject getAccount(JSONObject x){
        JSONObject y = new JSONObject();
        
        Account account = Account.sellectAccount(x.getString("user"));
        y.put("accept", true);
        if (account.user == null){
            y.put("accept", false);
        } else
        if (account.user == Session.getSession(x.getString("session")).user){
            y.put("user", account.user);
            y.put("pswd", account.pswd);
            y.put("point", account.point);
            y.put("name", account.name);
            
        } else
        {
            y.put("user", account.user);
            y.put("point", account.point);
            y.put("name", account.name);

        }
        return y;
    }
    
}