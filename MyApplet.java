import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;  // URLクラス用にインポート文を追加
import java.util.ArrayList;
import java.util.List;

public class MyApplet extends JApplet implements ActionListener{

    private MyPanel mp;
    private JTextArea ta;
    private JTextField tfIp, tfPort;
    private JLabel label1, label2, label3;
    private JButton connect, soloplay;

    private List<String> memo;
    private final int STRMAX = 36;

    
    public void init(){

        // 画像の読み込み
        // ダウンロード元：　https://www.pakutaso.com/20120841222post-1818.html
        URL url = MyApplet.class.getResource("Board.jpg");
        Image bg = super.getImage(url);

        this.mp = new MyPanel(this, bg);

        // システムメッセージの記録用リスト
        this.memo = new ArrayList<String>();

        this.ta = new JTextArea(38,30);
        this.label1 = new JLabel("server's ip address:");
        this.tfIp = new JTextField(15);
        this.label2 = new JLabel("server's port:");
        this.tfPort = new JTextField(5);
        this.connect = new JButton("connect");
        this.label3 = new JLabel(" |");
        this.soloplay = new JButton("solo play");

        JPanel panel = new JPanel();
        panel.add(this.mp);
        panel.add(this.ta);
        panel.add(this.label1);
        panel.add(this.tfIp);
        panel.add(this.label2);
        panel.add(this.tfPort);
        panel.add(this.connect);
        panel.add(this.label3);
        panel.add(this.soloplay);

        super.getContentPane().add(panel);

        this.soloplay.addActionListener(this);
        this.connect.addActionListener(this);
    }

    public void setTextArea(String text){
        int size = this.memo.size()+1;
        String str = "";
        if(size > this.STRMAX) this.memo.remove(0);
        this.memo.add(text + "\n");
        for(int i=0; i<size; i++){
            str += this.memo.get(i);
            this.ta.setText(str);
        }
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == this.soloplay){
            this.mp.soloPlay();
        }
        if(e.getSource() == this.connect){
            String ip = this.tfIp.getText();
            // int port = Integer.parseInt(this.tfPort.getText());
            // System.out.println(ip + ":" + port);
            // this.mp.multiPlay(ip, port);
        }
    }
}