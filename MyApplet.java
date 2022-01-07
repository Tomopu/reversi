import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;  // URLクラス用にインポート文を追加

public class MyApplet extends JApplet{

    private MyPanel mp;
    private JTextArea ta;
    
    public void init(){

        // 画像の読み込み
        // ダウンロード元：　https://www.pakutaso.com/20120841222post-1818.html
        URL url = MyApplet.class.getResource("Board.jpg");
        Image bg = super.getImage(url);

        this.mp = new MyPanel(bg);

        this.ta = new JTextArea(30,30);

        JPanel panel = new JPanel();
        panel.add(this.mp);
        panel.add(this.ta);

        super.getContentPane().add(panel);
    }

}