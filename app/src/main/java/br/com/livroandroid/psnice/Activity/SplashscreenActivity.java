package br.com.livroandroid.psnice.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import br.com.livroandroid.psnice.R;

public class SplashscreenActivity extends AppCompatActivity {

    public ImageView ivSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        ivSplash = findViewById(R.id.ivSplash);
        daDelay();
    }

    public void daDelay(){
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                mostrarLogin();
            }
        }, 3000);
    }

    public void mostrarLogin(){
        Intent i = new Intent(this, LogarComIdActivity.class);
        startActivity(i);
    }
}
