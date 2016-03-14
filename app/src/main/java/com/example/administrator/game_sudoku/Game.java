package com.example.administrator.game_sudoku;

/**
 * Created by Administrator on 2016/3/14.
 */
public class Game {
    private final String str = "3600000000004230800000004200" + "070460003820000014500013020" + "001900000007048300000000045";
    private int[] sudoku = new int[9*9];

    public Game() {
        sudoku = initSudoku(str);
    }

    private int getNum(int x, int y) {
        return sudoku[y*9+x];
    }

    public String getNumStr(int x, int y) {
        int v = getNum(x, y);
        if (v == 0) {
            return "";
        }
        return String.valueOf(v);
    }

    protected int[] initSudoku(String str) {
        int[] shuDu = new int[str.length()];
        for (int i=0; i<shuDu.length; i++) {
            shuDu[i] = str.charAt(i) - '0';//char - '0'转换为int
        }
        return shuDu;
    }
}
