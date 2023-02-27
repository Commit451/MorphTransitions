package com.commit451.morphtransitions.sample;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.commit451.morphtransitions.FabTransform;
import com.commit451.morphtransitions.MorphTransform;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);

        final Switch switchFullScreen = (Switch) findViewById(R.id.switch_full_screen);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                if (switchFullScreen.isChecked()) {
                    intent = new Intent(MainActivity.this, FullScreenActivity.class);
                } else {
                    intent = DialogActivity.newIntent(MainActivity.this, DialogActivity.TYPE_FAB);
                }
                if (Build.VERSION.SDK_INT >= 21) {
                    FabTransform.addExtras(intent, ContextCompat.getColor(MainActivity.this, R.color.colorAccent),
                            R.drawable.ic_mood_24dp);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation
                            (MainActivity.this, view, getString(R.string.transition_morph));
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
                    MorphTransform.addExtras(intent,
                            ContextCompat.getColor(MainActivity.this, R.color.colorAccent),
                            getResources().getDimensionPixelSize(R.dimen.dialog_corners));
                    ActivityOptions options =
                            ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, v,
                                    getString(R.string.transition_morph));
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.do_nothing);
                }
            }
        });
    }
}
