import java.io.BufferedReader;
import java.io.*;
// import java.net.*;

public class Client{
    
    public void connect(){
        ClientThread client;
        client = new ClientThread(this);
        client.start();
        String message;

        // キーボード入力についての設定
        BufferedReader keyIn = new BufferedReader(new InputStreamReader(System.in));

        try{
            while((message = keyIn.readLine()).length() > 0){
                System.out.println("enter message's length :" + message.length() + "(" + message + ")");
                client.send(message);
                System.out.println("送信しました。");
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }   

    public static void main(String[] args){
        new Client().connect();
    }
}

