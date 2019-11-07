package Controller;

import org.json.JSONObject;

import Model.Account;
import Model.Session;

/**
 * SessionController
 */
public class SessionController {

    public static JSONObject createSession(JSONObject x) {
        JSONObject y = new JSONObject();
        String session = Session.createSession();
        y.put("session", session);
        y.put("accept", true);
        return y;
    }

    public static JSONObject dropSession(JSONObject x) {
        Session.dropSession(x.getString("session"));
        JSONObject y = new JSONObject();
        y.put("accept", true);
        return y;
    }

    public static JSONObject logOut(JSONObject x){
        JSONObject y = new JSONObject();
        Session.getSession(x.getString("session")).user= null;
        y.put("accept", true);
        return y;
    }
    public static JSONObject logIn(JSONObject x) {
         
        JSONObject y = new JSONObject();
        Account account = Account.sellectAccount(x.getString("user"));
        if (account.user!= null && account.pswd.equals(x.getString("pswd"))) {
            y.put("accept", true);
            Session.getSession(x.getString("session")).user = account.user;
        }else y.put("accept", false);
        return y;
    }
}