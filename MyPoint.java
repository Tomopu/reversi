public class MyPoint{
    private int x, y;

    public MyPoint(int y, int x){
        this.y = y;
        this.x = x;
    }

    // x座標の取得
    public int getX(){
        return this.x;
    }

    // y座標の取得
    public int getY(){
        return this.y;
    }

    // 座標を登録
    public void setPoint(int y, int x){
        this.y = y;
        this.x = x;
    }
}