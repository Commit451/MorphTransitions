package com.commit451.morphtransitions.sample;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.commit451.morphtransitions.MorphManager;

/**
 * Activity which looks like a dialog
 * Created by John on 1/6/16.
 */
public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        findViewById(R.id.root).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        MorphManager.morph(this,
                findViewById(R.id.container),
                ColorUtil.getThemeAttrColor(this, R.attr.colorAccent),
                Color.WHITE,
                getResources().getDimensionPixelSize(R.dimen.dialog_corners));
    }

    @TargetApi(21)
    public void dismiss() {
        if (Build.VERSION.SDK_INT >= 21) {
            finishAfterTransition();
        } else {
            finish();
        }
    }
}
