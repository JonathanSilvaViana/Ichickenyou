package br.com.ichickenyou;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.provider.FontsContract;
import android.support.annotation.RequiresApi;
import android.support.v4.content.res.FontResourcesParserCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class GenderActivity extends AppCompatActivity {

    //declarações de objetos que serão usados no decorrer da execução da classe ou activity
    ActionBar actionBar;
    String idioma_coreano, outros_idiomas;
    boolean se_idioma_coreano;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);

        //chama a action bar, assim suporta o botão voltar em aparelhos android com firmware mais recentes e antigos
        actionBar = getSupportActionBar();

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
            setContentView(R.layout.activity_gender_korean_layout);
        } else {
            //exibe um log do sistema dizendo qual o idioma rastreado
            Log.d("IDIOMA:", outros_idiomas);
        }




    }
}
