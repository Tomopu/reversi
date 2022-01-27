import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ReversiServerThread extends Thread{
    private static List<ReversiServerThread> threads = new ArrayList<ReversiServerThread>();

    private Socket socket;

    private int number = 0;

    public ReversiServerThread(Socket socket){
        this.socket = socket;
        threads.add(this);
        number = threads.indexOf(this);
        System.out.println("new client connected!! (no. " + number + ")");
    }

    public void run(){
        InputStream in = null;
        String message;
        int size;
        byte[] w = new byte[1024];
        try{
            in = socket.getInputStream();
            while(true){
                try{
                    size = in.read(w);
                    if(size<=0) throw new IOException();
                    message = new String(w, 0, size, "UTF-8");
                    System.out.println("「" + message + "」を受信しました。");
                    this.sendMessageAll(number + ":" + message);
                }catch(IOException e){
                    System.err.println("*** クライアントが切断しました。終了してください。 ***");
                socket.close();
                threads.remove(this);
                return;
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void sendMessageAll(String message){
        ReversiServerThread thread;
        System.out.println("client number:" + threads.size());
        for(int i=0; i<threads.size(); i++){
            thread = (ReversiServerThread)threads.get(i);
            System.out.println(thread.isAlive());
            if(thread.isAlive()) thread.sendMessage(message);
        }
    }

    public void sendMessage(String message){
        try{
            OutputStream out = socket.getOutputStream();
            byte[] b = message.getBytes("UTF-8");
            out.write(b);
            out.flush();
            System.out.println("送信しました。");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
