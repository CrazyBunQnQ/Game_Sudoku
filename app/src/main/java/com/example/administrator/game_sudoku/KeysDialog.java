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

    public KeysDialog(Context context, int[] used) {
        super(context);
        this.used = used;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTitle("Put in number by click");
        setContentView(R.layout.dialog_keys);
        assignViews();
        for (int i=0; i<used.length; i++) {
            if (used[i] != 0) {
                keys[used[i] - 1].setVisibility(View.INVISIBLE);
            }
        }
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

}
