import java.io.*;
import java.net.*;

public class ClientThread extends Thread{
    private Client client;
    private Socket socket;
    private InputStream in;
    private OutputStream out;

    private String ip = "192.168.1.2";
    private int port = 10000;

    public ClientThread(Client client){
        this.client = client;
    }

    public void run(){
        try{
            connect(this.ip, this.port);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void connect(String ip, int port){
        int size;
        String str;
        byte[] b = new byte[1024];
        try{
            socket = new Socket(ip, port);
            System.out.println("接続しました:" + socket.getRemoteSocketAddress());
            in = socket.getInputStream();
            out = socket.getOutputStream();
            while(socket != null && socket.isConnected()){
                size = in.read(b);
                if(size <= 0) continue;
                str = new String(b, 0, size, "UTF-8");
                System.out.println(str);
            }
        }catch(IOException e){
            e.printStackTrace();
            disconnect();
        }
    }

    public void send(String message){
        byte[] b = message.getBytes();
        try{
            if(out != null){
                out.write(b);
                out.flush();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void disconnect(){
        try{
            if(socket != null){
                socket.close();
                socket = null;
                System.out.println("切断しました。");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
