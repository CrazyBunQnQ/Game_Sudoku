package com.example.administrator.game_sudoku;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Administrator on 2016/3/16.
 */
public class KeysDialog extends Dialog {
    private final Button keys[] = new Button[9];
    private final int used[];
    private ShuDuView shuDuView;

    public KeysDialog(Context context, int[] used, ShuDuView shuDuView) {
        super(context);
        this.used = used;
        this.shuDuView = shuDuView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_keys);
        assignViews();
        for (int i=0; i<used.length; i++) {
            if (used[i] != 0) {
                keys[used[i] - 1].setVisibility(View.INVISIBLE);
            }
        }
        setListenners();
    }

    private void assignViews() {
        keys[0] = (Button)findViewById(R.id.key_1);
        keys[1] = (Button)findViewById(R.id.key_2);
        keys[2] = (Button)findViewById(R.id.key_3);
        keys[3] = (Button)findViewById(R.id.key_4);
        keys[4] = (Button)findViewById(R.id.key_5);
        keys[5] = (Button)findViewById(R.id.key_6);
        keys[6] = (Button)findViewById(R.id.key_7);
        keys[7] = (Button)findViewById(R.id.key_8);
        keys[8] = (Button)findViewById(R.id.key_9);
    }

    private void returnResult(int number) {
        shuDuView.setNumber(number);
        dismiss();
    }

    private void setListenners() {
        for (int i=0; i<keys.length; i++) {
            final int number = i + 1;
            keys[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    returnResult(number);
                }
            });
        }
    }
}
