package br.com.ichickenyou;

import android.content.Context;
import android.content.Intent;
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
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class GenderActivity extends AppCompatActivity {

    //declarações de objetos que serão usados no decorrer da execução da classe ou activity
    ActionBar actionBar;
    String idioma_coreano, outros_idiomas;
    boolean se_idioma_coreano;
    ImageButton btMale, btFemale, btDoubtorPreferDontSay, manyoranothergenders;

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

        //executa a condicional se a idioma for Coreano, muda a fonte no layout difinido no método oncreate, para uma fonte compatível com o idioma coreano.

        if (se_idioma_coreano == true) {
            TextView textoperguntagenero = (TextView)findViewById(R.id.generosescolhatexto);
            Typeface typeface = ResourcesCompat.getFont(this, R.font.koreanfont);
            textoperguntagenero.setTypeface(typeface);
            textoperguntagenero.setTextSize(43);

        }
        else {
            //exibe um log do sistema dizendo qual o idioma rastreado
            Log.d("IDIOMA:", outros_idiomas);
        }

        //encontra o botão que direciona a amigos
        btMale = (ImageButton)findViewById(R.id.btMale);

        //cria o evento de direcionar para a tela de amigos masculinos
        btMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btMale = new Intent(GenderActivity.this, MaleFriends.class);
                startActivity(btMale);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_left);
            }
        });

        //encontra o botão que direciona a amigas
        btFemale = (ImageButton)findViewById(R.id.btFemale);

        //cria o evento de direcionar para a tela de amigas
        btFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btFemale = new Intent(GenderActivity.this, FemaleFriends.class);
                startActivity(btFemale);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_right);
            }
        });

        //encontra o botão que direciona amigos e amigas ou qualquer tipo de genero a partir do botão que contém um sinal de interrogação
        btDoubtorPreferDontSay = (ImageButton)findViewById(R.id.btDoubtorPreferDontSay);

        //cria o evento de direcionar para a tela de vários generos de amigo(s)
        btDoubtorPreferDontSay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btDoubtorPreferDontSay = new Intent(GenderActivity.this, AnyGenderFriends.class);
                startActivity(btDoubtorPreferDontSay);
                overridePendingTransition(R.anim.slide_out_right, R.anim.slide_out_right);
            }
        });

        manyoranothergenders = (ImageButton)findViewById(R.id.manyoranothergenders);

        //cria o evento de direcionar para a tela de vários generos de amigo(s) sejam qual for o gênero e quantidade
        manyoranothergenders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent manyoranothergenders = new Intent(GenderActivity.this, AnyGenderFriends.class);
                startActivity(manyoranothergenders);
                overridePendingTransition(R.anim.slide_out_left, R.anim.slide_out_left);
            }
        });



    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK)
            Log.d("back button:", "Botão voltar do aparelho pressionado");

        return true;
        //habilitou o o botão voltar nativo do aparelho, desta forma evita erros de rotas
    }
}
