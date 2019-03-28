package br.com.ichickenyou;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    private static int SPLASH_TIME_OUT = 2665;
    MediaPlayer mp;
    String idioma_coreano, outros_idiomas;
    boolean se_idioma_coreano;

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

        //variável que define a string do idioma coreano
        idioma_coreano = "ko";

        //variável boolean que define se o idioma rastreado, nesse caso encapsulado da variável "idioma_coreano", acima;
        se_idioma_coreano = Locale.getDefault().getLanguage().equals(idioma_coreano);

        //essa variável coleta a string do idioma local que esteja no aparelho que execute a aplicação
        outros_idiomas = Locale.getDefault().getLanguage();

        //executa a condicional se a idioma for Coreano, muda para um layout que possui recursos
        // que suportam fonte asiática, tais como fontes e componentes
        // que não estão disponíveis em todas versões do android.

        if (se_idioma_coreano == true) {
            //define o layout da activy em coreano
            setContentView(R.layout.activity_main_korean_layout);
        } else {
            //exibe um log do sistema dizendo qual o idioma rastreado
            Log.d("IDIOMA:", outros_idiomas);
        }

    }

}
