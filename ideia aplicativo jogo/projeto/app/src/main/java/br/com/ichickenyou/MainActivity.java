package br.com.ichickenyou;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    private static int SPLASH_TIME_OUT = 3300;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp = MediaPlayer.create(this, R.raw.abertura);
        mp.start();

        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {
                                          Intent saidasplashscreen = new Intent(MainActivity.this,
                                                  MenuActivity.class );
                                          startActivity(saidasplashscreen);
                                          finish();
                                      }
                                  },
                SPLASH_TIME_OUT);
    }
}
