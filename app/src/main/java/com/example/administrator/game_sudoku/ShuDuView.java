package com.example.administrator.game_sudoku;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2016/3/13.
 */
public class ShuDuView extends View {
    private float width;//单元格的宽度
    private Paint bgPaint = new Paint();//背景色画笔
    private Paint darkPaint = new Paint();//深色画笔
    private Paint hilitePaint = new Paint();//白色画笔
    private Paint lightPaint = new Paint();//浅色画笔
    private Paint numPaint = new Paint();//初始数字画笔
    private Game game = new Game();

    public ShuDuView(Context context) {
        super(context);
    }

    @Override
    /**
     * 当View的宽高改变的时候执行
     * w与h 当前View的宽和高
     * oldw与oldh 变动前的宽和高
     */
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        this.width = w>h?h/9f:w/9f;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    //当Android系统需要绘制一个View对象时，就会调用该对象的onDraw
    @Override
    protected void onDraw(Canvas canvas) {
        bgPaint.setColor(getResources().getColor(R.color.backGround));
        canvas.drawRect(0, 0, getWidth(), getHeight(), bgPaint);
        darkPaint.setStrokeWidth(2);
        darkPaint.setColor(getResources().getColor(R.color.darkGray));
        hilitePaint.setStrokeWidth(2);
        hilitePaint.setColor(getResources().getColor(R.color.hiliteGray));
        lightPaint.setStrokeWidth(2);
        lightPaint.setColor(getResources().getColor(R.color.lightGray));

        setBackGround(canvas);
        initNumbers(canvas);

        super.onDraw(canvas);
    }

    private void setBackGround(Canvas canvas) {
        for (int i = 0; i<10; i++) {
            //绘制横线
            canvas.drawLine(0, i*width, 9*width, i*width, i%3==0?darkPaint:lightPaint);
            canvas.drawLine(0, i*width+2, 9*width, i*width+2, hilitePaint);
            //绘制纵线
            canvas.drawLine(i*width, 0, i*width, 9*width, i%3==0?darkPaint:lightPaint);
            canvas.drawLine(i*width+2, 0, i*width+2, 9*width, hilitePaint);
        }
    }

    private void initNumbers(Canvas canvas) {
        numPaint.setColor(Color.BLACK);
        numPaint.setStyle(Paint.Style.STROKE);//设置空心
        numPaint.setTextSize(width * 0.75f);//设置文本大小为单元格宽度的四分之三
        numPaint.setTextAlign(Paint.Align.CENTER);//设置水平方向居中
        Paint.FontMetrics fm = numPaint.getFontMetrics();
        float x = width/2;
        float y = width/2 - (fm.ascent + fm.descent)/2;
        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++)
                canvas.drawText(game.getNumStr(i, j), i * width + x, j * width + y, numPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_DOWN) {
            return super.onTouchEvent(event);
        }
        if (event.getX()>width*9 || event.getY()>width*9) {
            return super.onTouchEvent(event);
        }

        int x = (int)(event.getX()/width);
        int y = (int)(event.getY()/width);
        int used[] = game.getUsedNumsByCoord(x, y);
        for (int i=0; i<used.length; i++) {
            Log.i("Game", String.valueOf(used[i]));
        }
        return super.onTouchEvent(event);
    }
}
