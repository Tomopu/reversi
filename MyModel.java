public class MyModel {
    private int[][] board;
    private int width, height;

    public MyModel(){
        // 空白0, 黒1, 白2, 候補3, 壁4
        this.board = new int[10][10];

        // パネルのサイズ
        this.height = 620;
        this.width = 620;

        initState();
    }

    // heightのゲッター
    public int getHeight(){
        return this.height;
    }

    // widthのゲッター
    public int getWidth(){
        return this.width;
    }

    // boardの要素を取得
    public int getState(int y, int x){
        return this.board[y][x];
    }

    // boardの要素を設定
    public void setState(int y, int x, int value){
        this.board[y][x] = value;
    }

    // boardの初期化
    private void initState(){
        for(int y=0; y<10; y++){
            for(int x=0; x<10; x++){
                // 壁の配置
                if(y==0||y==9||x==0||x==9) this.board[y][x] = 4;
                else this.board[y][x] = 0;
            }
        }

        // 初期コマの配置
        this.board[4][4] = 2; this.board[4][5] = 1;
        this.board[5][4] = 1; this.board[5][5] = 2;
    }

    // 石の置ける候補の初期化
    public void clear(){
        for(int y=1; y<9; y++){
            for(int x=1; x<9; x++){
                if(this.board[y][x] == 3) this.board[y][x] = 0;
            }
        }
    }

    // 配列boardの描画 (デバック用)
    public void printBorad(){
        for(int y=0; y<10; y++){
            for(int x=0; x<10; x++){
                System.out.print(this.board[y][x]);
            }
            System.out.println();
        }
        System.out.println();
    }
}