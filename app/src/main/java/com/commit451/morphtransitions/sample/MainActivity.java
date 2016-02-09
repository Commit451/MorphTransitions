package com.commit451.morphtransitions.sample;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = DialogActivity.newIntent(MainActivity.this, DialogActivity.TYPE_FAB);
                if (Build.VERSION.SDK_INT >= 21) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation
                            (MainActivity.this, view, getString(R.string.morph_transition));
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.do_nothing);
                }
            }
        });

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = DialogActivity.newIntent(MainActivity.this, DialogActivity.TYPE_BUTTON);
                if (Build.VERSION.SDK_INT >= 21) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation
                            (MainActivity.this, v, getString(R.string.morph_transition));
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.do_nothing);
                }
            }
        });
    }
}
