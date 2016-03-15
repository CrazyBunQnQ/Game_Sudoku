package com.example.administrator.game_sudoku;

/**
 * Created by Administrator on 2016/3/14.
 */
public class Game {
    private int[][] sudokuResult;


    public Game() {
        baseSudoku();
        sudokuResult = initSudoku(sudokuResult, 15);
    }

    /**
     * 初始化为最原始数独数组
     */
    public void baseSudoku() {
        sudokuResult = new int[][]{
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                {2, 3, 4, 5, 6, 7, 8, 9, 1},
                {5, 6, 7, 8, 9, 1, 2, 3, 4},
                {8, 9, 1, 2, 3, 4, 5, 6, 7},
                {3, 4, 5, 6, 7, 8, 9, 1, 2},
                {6, 7, 8, 9, 1, 2, 3, 4, 5},
                {9, 1, 2, 3, 4, 5, 6, 7, 8}
        };
    }

    private int getNum(int x, int y) {
        return sudokuResult[x][y];
    }

    public String getNumStr(int x, int y) {
        int v = getNum(x, y);
        if (v == 0) {
            return "";
        }
        return String.valueOf(v);
    }

    /**
     * 初始化随机数独
     * 随机交换每一行中两个数的位置，打乱数独
     * @param numsArray 原始数独数组
     * @param times 交换次数
     * @return 新的随机数独数组
     */
    private int[][] initSudoku(int[][] numsArray,int times) {
        //交换每一行中这两个数字的位置
        for (int i=0; i<times; i++) {
            //随机生成两个需要交换的数
            int a = (int) ((9) * Math.random() + 1);
            int b = (int) ((9) * Math.random() + 1);
            while (a == b) {
                b = (int) ((9) * Math.random() + 1);
            }

            for (int j=0; j<9; j++) {
                exchangeR(numsArray[j], a, b);
            }

        }
        //交换每一列中两个数字的位置
        for (int i=0; i<times; i++) {
            int a = (int) ((9) * Math.random() + 1);
            int b = (int) ((9) * Math.random() + 1);
            while (a == b) {
                b = (int) ((9) * Math.random() + 1);
            }

            for (int j=0; j<9; j++) {
                exchangeC(numsArray, j, a, b);
            }
        }
        digHoles(18, false);
        return numsArray;
    }

    /**
     * 一维数组中指定的两个数字交换位置
     * @param nums 给定的数组
     * @param a 数字
     * @param b 数字
     * @return 新的数组
     */
    private int[] exchangeR(int[] nums, int a, int b) {
        int temp = 0;
        for (int i=0; i<9; i++) {
            if (temp != 0 && (nums[i] == a || nums[i] == b)) {
                nums[i] = temp;
            } else if (nums[i] == a) {
                temp = a;
                nums[i] = b;
            } else if (nums[i] == b) {
                temp = b;
                nums[i] = a;
            }
        }
        return nums;
    }

    /**
     * 二维数组交换同一列中指定的两个数字的位置
     * @param nums 给定的二维数组
     * @param col 列
     * @param a 数字
     * @param b 数字
     * @return 新的二维数组
     */
    private int[][] exchangeC(int[][] nums, int col, int a, int b) {
        int temp = 0;
        for (int i=0; i<9; i++) {
            if (temp != 0 && (nums[i][col] == a || nums[i][col] == b)) {
                nums[i][col] = temp;
            } else if (nums[i][col] == a) {
                temp = a;
                nums[i][col] = b;
            } else if (nums[i][col] == b) {
                temp = b;
                nums[i][col] = a;
            }
        }
        return nums;
    }

    /**
     * 给数独棋盘挖坑
     * @param nums 挖坑数量或初始数字数量
     * @param isHoles 是否是坑的数量，false则表示初始数字的数量
     */
    private void digHoles(int nums, boolean isHoles) {
        int holes = isHoles?nums:81-nums;
        int holesPerRow = holes/9;
        int rows[] = new int [9];
        changeTo(rows, -1, holes%9);
        for (int i=0; i<9; i++) {
            changeTo(this.sudokuResult[i], 0, rows[i] == 0? holesPerRow: holesPerRow + 1);
        }

    }

    /**
     * 将一维数组中随机几个数字改成目标数字
     * @param arr 一维数组
     * @param target 目标数字
     * @param count 随机元素个数
     */
    private void changeTo(int[] arr, int target, int count) {
        for (int i=0; i<count; i++) {
            int rand = (int) (Math.random()*arr.length);
            if (arr[rand] == target) {
                i--;
            } else {
                arr[rand] = target;
            }
        }
    }
}
