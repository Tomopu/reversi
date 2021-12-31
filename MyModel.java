public class MyModel {
    private int[][] field;

    public MyModel(){
        this.field = new int[8][8];
        initState();
    }

    public int getState(int y, int x){
        return this.field[y][x];
    }

    public void setState(int y, int x, int value){
        if(value == 1 || value == 2) this.field[y][x] = value;
    }

    private void initState(){
        for(int y=0; y<8; y++){
            for(int x=0; x<8; x++){
                this.field[y][x] = 0;
            }
        }
        // 初期コマの配置
        this.field[3][3] = 2; this.field[3][4] = 1;
        this.field[4][3] = 1; this.field[4][4] = 2;
    }
}