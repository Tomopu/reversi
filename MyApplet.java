import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;  // URLクラス用にインポート文を追加
import java.util.ArrayList;
import java.util.List;

public class MyApplet extends JApplet{

    private MyPanel mp;
    private JTextArea ta;
    private List<String> memo;
    // テキストエリアに表示する最大行数
    private final int LINEMAX = 37;

    public void init(){

        // 画像の読み込み
        // ダウンロード元：　https://www.pakutaso.com/20120841222post-1818.html
        URL url = MyApplet.class.getResource("Board.jpg");
        Image bg = super.getImage(url);

        this.mp = new MyPanel(this, bg);

        // システムメッセージの記録用リスト
        this.memo = new ArrayList<String>();
        // テキストエリア
        this.ta = new JTextArea(38,30);

        // パネルに追加
        JPanel panel = new JPanel();
        panel.add(this.mp);
        panel.add(this.ta);

        super.getContentPane().add(panel);
    }

    // テキストエリアに追加
    public void setTextArea(String text){
        // リストのサイズを取得
        int size = this.memo.size();
        String str = "";
        // もし、最大格納数を超えていたら、先頭要素を削除
        if(size >= this.LINEMAX) this.memo.remove(0);
        // リストにテキストを格納
        this.memo.add(text + "\n");
        // 格納後サイズを再取得
        size = this.memo.size();
        // リストから一文字ずつ取得
        for(int i=0; i<size; i++){
            str += this.memo.get(i);
        }
        // テキストエリアに表示
        this.ta.setText(str);
    }
}