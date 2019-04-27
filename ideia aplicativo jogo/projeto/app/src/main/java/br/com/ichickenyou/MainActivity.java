package br.com.ichickenyou;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    private static int SPLASH_TIME_OUT = 2665;
    MediaPlayer mp;
    String idioma_coreano, outros_idiomas, preferencia_som, ativo, desativado;
    boolean se_idioma_coreano;
    SharedPreferences som;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //chama o método que aplica as preferências de som
        comSom();

        //cria a transição da splash screen
        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {
                                          Intent saidasplashscreen = new Intent(MainActivity.this,
                                                  MenuActivity.class );
                                          startActivity(saidasplashscreen);
                                          finish();
                                      }
                                  },
                //encerra o tempo de exibição
                SPLASH_TIME_OUT);

        //variável que define a string do idioma coreano
        idioma_coreano = "ko";

        //variável boolean que define se o idioma rastreado, nesse caso encapsulado da variável "idioma_coreano", acima;
        se_idioma_coreano = Locale.getDefault().getLanguage().equals(idioma_coreano);

        //essa variável coleta a string do idioma local que esteja no aparelho que execute a aplicação
        outros_idiomas = Locale.getDefault().getLanguage();

        //executa a condicional se a idioma for Coreano, muda a fonte no layout difinido no método oncreate, para uma fonte compatível com o idioma coreano.

        if (se_idioma_coreano == true) {
            TextView mainTextView = (TextView)findViewById(R.id.textView);
            Typeface typeface = ResourcesCompat.getFont(this, R.font.koreanfont);
            mainTextView.setTypeface(typeface);
            mainTextView.setTextSize(43);
        }
        else {
            //exibe um log do sistema dizendo qual o idioma rastreado
            Log.d("IDIOMA:", outros_idiomas);
        }

    }

    public void comSom()
    {
        //string que serve de nome para o arquivo de preferências
        preferencia_som = "Preferencia_som";
        //coleta o arquivo de preferências via a activity que mantém o fragment
        som = getSharedPreferences(preferencia_som, Context.MODE_PRIVATE);
        //abaixo as strings que denotam por nome os estados contidos no arquivo de preferências
        ativo = "ATIVADO";
        desativado = "DESATIVADO";

        //cria condicional
        if (som.contains(ativo) == true && som.contains(desativado) == false)
        {
            //dentro do contexto da aplicação seleciona o audio desejado
            mp = MediaPlayer.create(MainActivity.this, R.raw.abertura);
            //ativa o som para o início de jogo
            mp.start();
        }
        else
        {
            //se a opção de audio desativado for verdadeira, então a função faz um log no aplicativo
            Log.d("audio desativado", "audio desativado para tocar o som de play");
        }

    }

}
