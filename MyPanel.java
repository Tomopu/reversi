import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class MyPanel extends JPanel implements MouseListener{

    private int width, height;
    private Color boardColor, clearWhite, ClearBlack;
    MyModel data;
    MyStack stack;
    private Image bg;

    private int turn = 0;
    
    public MyPanel(Image bg){
        
        // データのインスタンスを作成
        this.data = new MyModel();

        // スタックのインスタンスを作成
        this.stack = new MyStack();

        // パネルのサイズ
        this.height = 620;
        this.width = 620;

        // 背景画像の保存
        this.bg = bg;

        // 盤面の背景色を設定
        this.boardColor = new Color(3,170,2);
        // 色指定
        this.clearWhite = new Color(255,255,255,150);
        this.ClearBlack = new Color(0,0,0,90);

        // 設定
        super.setBackground(Color.white);
        super.setPreferredSize(new Dimension(this.width, this.height));

        // マウスリスナー
        this.addMouseListener(this);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);   
        // 背景が描画
        g.drawImage(this.bg, 0, 0, this);
        // 石が置ける場所の判定
        judgePlaced(this.turn); 
        // 盤面を描画
        drawBoard(g);
        // 石の描画
        drawStone(g);
    }

    // 盤面の描画
    private void drawBoard(Graphics g){
        for(int y=0; y<8; y++){
            for(int x=0; x<8; x++){
                g.setColor(this.boardColor);
                g.fillRect(30+70*x,30+70*y,70,70);
                g.setColor(Color.black);
                g.drawRect(30+70*x,30+70*y,70,70);
            }
        }

        // 四隅の点
        g.setColor(Color.black);
        g.fillOval(165, 165, 10, 10);
        g.fillOval(445, 165, 10, 10);
        g.fillOval(165, 445, 10, 10);
        g.fillOval(445, 445, 10, 10);
    }

    private void drawStone(Graphics g){
        int stone;
        for(int y=0; y<8; y++){
            for(int x=0; x<8; x++){
                stone = data.getState(y+1, x+1);
                if(stone == 1){
                    g.setColor(Color.black);
                    g.fillOval(40+70*x,40+70*y,50,50);
                }
                if(stone == 2){
                    g.setColor(Color.white);
                    g.fillOval(40+70*x,40+70*y,50,50);
                }else if(stone == 3){
                    if(this.turn == 0) g.setColor(this.ClearBlack);
                    else g.setColor(this.clearWhite);
                    g.fillOval(60+70*x,60+70*y,10,10);
                }
            }
        }
    }

    // 石が置ける場所の判定 turn: 0黒, 1白
    private void judgePlaced(int turn){
        int color = turn+1;
        for(int y=1; y<9; y++){
            for(int x=1; x<9; x++){
                if(this.data.getState(y, x) == color){
                    for(int dir=0; dir<8; dir++){
                        check(y, x, dir, turn, 0);
                    }
                }
            }
        }
    }

    // 石が置ける場所の確認
    private void check(int y, int x, int dir, int turn, int flag){
        // 方向と座標の対応
        switch(dir){
            case 0: y--;      break;
            case 1: y--; x++; break;
            case 2:      x++; break;
            case 3: y++; x++; break;
            case 4: y++;      break;
            case 5: y++; x--; break;
            case 6:      x--; break;
            case 7: y--; x--; break;
        }
    
        int state = this.data.getState(y, x);
        int enemy = ((turn+1)%2)+1;

        if(state == enemy){
            check(y, x, dir, turn, 1);
        }else if(state == 0){
            if(flag != 0){
                // boardに記録
                this.data.setState(y, x, 3);
            }
        }
        return;
    }

    private void turnStone(int y, int x, int dir, int turn){

        // 方向と座標の対応
        switch(dir){
            case 0: y--;      break;
            case 1: y--; x++; break;
            case 2:      x++; break;
            case 3: y++; x++; break;
            case 4: y++;      break;
            case 5: y++; x--; break;
            case 6:      x--; break;
            case 7: y--; x--; break;
        }

        int state = this.data.getState(y, x);
        int enemy = ((turn+1)%2)+1;
        int me = turn+1;

        if(state == enemy){
            this.stack.push(new MyPoint(y, x));
            turnStone(y, x, dir, turn);
        }else if(state == me){
            MyPoint point;
            int length = this.stack.len();
            for(int i=0; i<length; i++){
                point = this.stack.pop();
                this.data.setState(point.getY(), point.getX(), me);
            }
        }

        return;
    }

    private void putStone(int y, int x, int turn){
        for(int dir=0; dir<8; dir++){
            // 石を置いた座標をスタックにプッシュ
            this.stack.push(new MyPoint(y, x));
            turnStone(y, x, dir, turn);
            // スタックの中を空にする
            this.stack.clear();
        }
    }

    public void mousePressed(MouseEvent e){
        int x = e.getX();
        int y = e.getY();
        
        // クリックされた座標が盤面内であれば
        if((30 <= x && x <= 590) && (30 <= y && y <= 590)){
            y = (y-30)/70;
            x = (x-30)/70;
            if(this.data.getState(y+1, x+1) == 3){
                putStone(y+1, x+1, this.turn);
                this.data.clear();
                this.turn = (this.turn+1)%2;
            }
            repaint();
        }
    }

    public void mouseReleased(MouseEvent e) { } // マウスボタンが離されたとき
    public void mouseClicked(MouseEvent e) { }  // マウスボタンがクリックされた(押して離された)とき
    public void mouseEntered(MouseEvent e) { }  // マウスカーソルが部品内に入ったとき
    public void mouseExited(MouseEvent e) { }   // マウスカーソルが部品外に出たとき

    public void mouseMoved(MouseEvent e){}

}