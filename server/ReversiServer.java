import java.io.*;
import java.net.*;

public class ReversiServer{

    static final int PORT = 10000;
    static final int ACCEPTMAX = 2;

    public void start(){
        ServerSocket server;
        int acceptCount = 0;

        try{
            server = new ServerSocket(PORT);
            System.out.println("Server Start!! (Port:" + PORT +")");
            
            while(acceptCount < ACCEPTMAX){
                try{
                    Socket socket = null;
                    socket = server.accept();
                    new ReversiServerThread(socket).start();
                    acceptCount++;
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            System.out.println("クライアントの接続可能数の上限に達しています。");
        }catch(IOException e){
            System.err.println(e);
        }
    }

    public static void main(String[] args){
        ReversiServer server = new ReversiServer();
        server.start();
    }

}