package com.example.worldexplorer;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    private Animation bounceAnime, fadeInAnime;
    private TextView worldLabel, welcomeLabel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        // hide the action bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // to start the animation
        startAnimation();

    }

    public void startAnimation() {
        // to start the animation for "welcome to" label
        fadeInAnime = AnimationUtils.loadAnimation(this, R.anim.fade_in_animation);
        welcomeLabel = findViewById(R.id.welcomeToLabel);
        welcomeLabel.setAnimation(fadeInAnime);

        // to start the animation for "world explorer" label
        bounceAnime = AnimationUtils.loadAnimation(this, R.anim.bounce_animation);
        worldLabel = findViewById(R.id.worldExplorerLabel);
        worldLabel.setAnimation(bounceAnime);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                Log.e(TAG, "This is an error message");
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}
