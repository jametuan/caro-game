package Model;

import java.sql.*;
import Resource.*;
public class ConnectorDatabase {
    
    private static Connection connection = null;
    public static Connection getConnect()  {
        if (connection == null){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://"+ResourceSQL.SERVER_NAME+":"
                +ResourceSQL.SERVER_PORT+
                "/"+ResourceSQL.DATA_BASE, ResourceSQL.ACCOUNT,ResourceSQL.PASS);
            } catch (SQLException e) {
                
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
            
                e.printStackTrace();
            }
        }
        return connection;
    }
}
