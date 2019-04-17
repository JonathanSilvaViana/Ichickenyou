package br.com.ichickenyou;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {


    String idioma_coreano, outros_idiomas, preferencia_som;
    boolean se_idioma_coreano;
    ImageButton bt_voltar;
    public Intent voltar_home;
    RadioButton som_on, som_off;
    SharedPreferences som;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.bt_certo);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, R.string.saved, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        //localiza o botão voltar
        bt_voltar = (ImageButton)findViewById(R.id.imageButton);

        bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltar_home = new Intent(SettingsActivity.this, MenuActivity.class);
                startActivity(voltar_home);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
                finish();
            }
        });


        //variável que define a string do idioma coreano
        idioma_coreano = "ko";

        //variável boolean que define se o idioma rastreado, nesse caso encapsulado da variável "idioma_coreano", acima;
        se_idioma_coreano = Locale.getDefault().getLanguage().equals(idioma_coreano);

        //essa variável coleta a string do idioma local que esteja no aparelho que execute a aplicação
        outros_idiomas = Locale.getDefault().getLanguage();

        //executa a condicional se a idioma for Coreano, muda a fonte no layout difinido no método oncreate, para uma fonte compatível com o idioma coreano.

        if (se_idioma_coreano == true) {
            TextView texto_som = (TextView)findViewById(R.id.texto_som);
            Typeface typeface = ResourcesCompat.getFont(this, R.font.koreanfont);
            texto_som.setTypeface(typeface);
            texto_som.setTextSize(43);
        }
        else {
            //exibe um log do sistema dizendo qual o idioma rastreado
            Log.d("IDIOMA:", outros_idiomas);
        }

        //tratativa de efeitos sonoros

        //encontra o item da view de som on
        som_on = (RadioButton)findViewById(R.id.som_on);

        //encontra o item da view de som off
        som_off = (RadioButton)findViewById(R.id.som_off);

        //string que serve de nome da preferência de som no app
        preferencia_som = "Preferencia_som";

        //cria o arquivo de persistência de dados referente a som
        som = SettingsActivity.this.getSharedPreferences(preferencia_som, Context.MODE_PRIVATE);





    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK)
            Log.d("back button:", "Botão voltar do aparelho pressionado");

        return false;
        //desabilitou o o botão voltar nativo do aparelho, desta forma evita erros de rotas
    }

}
