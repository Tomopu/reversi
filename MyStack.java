public class MyStack{
    private MyPoint[] stack;
    private int top;
    private int index_max;

    public MyStack(){
        this.top = 0;
        // リバーシは8*8マスなので， indexの最大値は8にする．
        this.index_max = 8;
        this.stack = new MyPoint[this.index_max];
        clear();
    }

    // Point型の変数をプッシュする
    public void push(MyPoint point){
        if(this.top >= this.index_max) return;
        this.stack[this.top] = point;
        this.top++;
    }

    // スタックから1番上の要素を取り出す
    public MyPoint pop(){
        if(this.top <= 0){
            this.top = 0;
            return null;
        }

        this.top--;
        MyPoint point = this.stack[this.top];
        this.stack[this.top] = null;
        return point;
    }

    // スタックに格納されている要素の数を取得
    public int len(){
        return this.top;
    }

    // スタックの中身を初期化する
    public void clear(){
        for(int i=0; i<this.index_max; i++){
            this.stack[i] = null;
        }
        this.top = 0;
    }

    // スタックの中身を表示　(デバック時に使用)
    public void print(){
        System.out.print("{");
        for(int i=this.top-1; i>=0; i--){
            System.out.print("(" + this.stack[i].getY() + ", " + this.stack[i].getX() + ") ");
        }
        System.out.print("}, ");
    }
}