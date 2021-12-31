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

        this.mp = new MyPanel();

        this.ta = new JTextArea(30,30);

        JPanel panel = new JPanel();
        panel.add(this.mp);
        panel.add(this.ta);

        super.getContentPane().add(panel);
    }

}