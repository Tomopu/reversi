import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class MyPanel extends JPanel implements MouseListener{

    private int width, height;
    MyModel data;
    
    public MyPanel(){

        this.data = new MyModel();

        // パネルのサイズ
        this.height = 620;
        this.width = 620;


        super.setBackground(Color.white);
        super.setPreferredSize(new Dimension(this.width, this.height));
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);     
        drawField(g);
        drawStone(g);
    }

    // 盤面の描画
    private void drawField(Graphics g){
        for(int y=0; y<8; y++){
            for(int x=0; x<8; x++){
                g.setColor(Color.black);
                g.drawRect(30+70*x,30+70*y,70,70);
                g.setColor(Color.green);
                g.fillRect(30+70*x,30+70*y,70,70);
            }
        }
    }

    private void drawStone(Graphics g){
        int stone;
        for(int y=0; y<8; y++){
            for(int x=0; x<8; x++){
                stone = data.getState(y, x);
                System.out.print(stone);
                if(stone == 1){
                    g.setColor(Color.black);
                    g.fillOval(40+70*x,40+70*y,50,50);
                }
                if(stone == 2){
                    g.setColor(Color.white);
                    g.fillOval(40+70*x,40+70*y,50,50);
                }
            }
            System.out.println();
        }
    }

    public void mousePressed(MouseEvent e){}

    public void mouseReleased(MouseEvent e) { } // マウスボタンが離されたとき
    public void mouseClicked(MouseEvent e) { }  // マウスボタンがクリックされた(押して離された)とき
    public void mouseEntered(MouseEvent e) { }  // マウスカーソルが部品内に入ったとき
    public void mouseExited(MouseEvent e) { }   // マウスカーソルが部品外に出たとき

    public void mouseMoved(MouseEvent e){}

}