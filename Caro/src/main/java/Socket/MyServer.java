package Socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Resource.ResourceSever;

/**
 * Socket
 */
public class MyServer {
    public static void main(String args[]) {
        ServerSocket server;
        try {
            server = new ServerSocket(ResourceSever.PORT);
            while (true){
                Socket socket = server.accept();
                MyThread x = new MyThread(socket);
                Thread thread = new Thread(x);
                thread.start();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace(); 
        }
        
    }


}