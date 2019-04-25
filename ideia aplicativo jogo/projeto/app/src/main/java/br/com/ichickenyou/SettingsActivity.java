package br.com.ichickenyou;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
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
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {


    String idioma_coreano, outros_idiomas, preferencia_som, com_som, sem_som, ativo, desativado, meuemail, assunto, mensagem, enunciadoIntentReportEmail;
    boolean se_idioma_coreano;
    ImageButton bt_voltar;
    public Intent voltar_home, bug_email_report;
    RadioGroup asopcoes;
    RadioButton som_on, som_off;
    SharedPreferences som;
    View acessoaviewparasnackbar;
    private static int tempo_de_retorno = 2165;
    FloatingActionButton bug_report_bt;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);

        //encontra e instancia o botão indicado para reportar bugs
        bug_report_bt = (FloatingActionButton)findViewById(R.id.reportar_bug);

        //quando o botão acima é precionado, chama o método de envio de bug via e-mail por intent
        bug_report_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //chama o método para reportar bug via intent de e-mail
                reportaviaemail();
                //se a opção de som estiver ativada, e a opção de som desativado estiver desativada permite executar som
                if(som.contains(ativo) == true && som.contains(desativado) == false) {
                    //cria o player e encontra o som
                    mp = MediaPlayer.create(SettingsActivity.this, R.raw.telaazulsomwindows);
                    //inicia o player
                    mp.start();
                } else
                {
                    //se a opção de audio desativado for verdadeira, então a função faz um log no aplicativo
                    Log.d("audio desativado", "audio desativado para tocar o som de bug");
                }

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

        //encontra o content das opções
        asopcoes = (RadioGroup)findViewById(R.id.opcoesdeaudio);

        //encontra o item da view de som on
        som_on = (RadioButton)findViewById(R.id.som_on);

        //encontra o item da view de som off
        som_off = (RadioButton)findViewById(R.id.som_off);

        //string que serve de nome da preferência de som no app
        preferencia_som = "Preferencia_som";

        //cria o arquivo de persistência de dados referente a som
        som = SettingsActivity.this.getSharedPreferences(preferencia_som, Context.MODE_PRIVATE);

        //prepara o arquivo de persistência

        asopcoes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                //instancia o objeto de edição de SharedPreferences
                SharedPreferences.Editor editor = som.edit();

                if (checkedId == R.id.som_on)
                {
                    //inclui valores significativos para ativaçãpo de audio
                    editor.putBoolean("ATIVADO", som_on.isChecked());
                    editor.putString("Audio", "com audio");
                    //remove as preferências conflitantes caso existam
                    editor.remove("DESATIVADO");
                    //aplica os procedimentos
                    editor.apply();

                    //inclui as normas de audio no arquivo de persistência
                    com_som = som.getString("Audio ativado", "com audio");

                }
                else  if (checkedId == R.id.som_off)
                {

                    //inclui valores significativos para ativaçãpo de audio
                    editor.putBoolean("DESATIVADO", som_off.isChecked());
                    editor.putString("Audio", "sem audio");
                    //remove as preferências conflitantes caso existam
                    editor.remove("ATIVADO");
                    //aplica os procedimentos
                    editor.apply();

                    //inclui as normas de audio no arquivo de persistência
                    sem_som = som.getString("Audio desativado", "sem audio");

                }
                else
                {
                    //se nenhuma opção foi selecionada, então faz um log durante o processo de debug mencionando o fato.
                        Log.d("Nenhum selecionado", "Nenhuma opção selecionada");
                }

            }
        });


        //variáveis string de caracteriação de nome para valores comparativos em busca condicional
        ativo = "ATIVADO";
        desativado = "DESATIVADO";

        //verifica se o audio está ativado por meio de checagem no arquivos de preferências chamado preferencia_som, usando dupla checagem condicional, prevenindo de falhas no mesmo contexto.

        if(som.contains(ativo) == true && som.contains(desativado) == false)
        {
            //se som está ativado e se estiver desativado conter false, define a opção on como selecionada
            som_on.setChecked(true);
        }
        else if (som.contains(desativado) == true && som.contains(ativo) == false)
        {
            //se som está desativado e se estiver ativo conter false, define a opção off como selecionada
            som_off.setChecked(true);
        }
        else
        {
            //se nenhuma opção foi definida, cria um log no debug avisando que nenhuma preferência de audio foi escolhida
                Log.d("Sem definições de audio", "Nenhuma definição de audio");
        }


        //evento de feedback da opção on
        som_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //acessa o elemento view da activity, para evitar depreciação em futuras versões do android
                acessoaviewparasnackbar = findViewById(R.id.activity_settings);

                //feedback de aviso
                Snackbar.make(acessoaviewparasnackbar, R.string.saved, Snackbar.LENGTH_LONG)
                        .show();

                //retorna a activity principal após a mensagem de feedback

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //cria o intent que leva a activity principal
                        voltar_home = new Intent(SettingsActivity.this, MenuActivity.class);
                        //inicia o intent anterior
                        startActivity(voltar_home);
                        //chama e invoca os eventos de animação
                        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
                        //encerra a activity
                        finish();
                    }
                },
                        //define o tempo de retorno após a ação do método e encerramento da activity
                        tempo_de_retorno);
            }
        });

        //evento de feedback da opção off
        som_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //acessa o elemento view da activity
                acessoaviewparasnackbar = findViewById(R.id.activity_settings);

                //feedback de aviso
                Snackbar.make(acessoaviewparasnackbar, R.string.saved, Snackbar.LENGTH_LONG)
                        .show();

                //retorna a activity principal após a mensagem de feedback

                new Handler().postDelayed(new Runnable() {
                                              @Override
                                              public void run() {
                                                  //cria o intent que leva a activity principal
                                                  voltar_home = new Intent(SettingsActivity.this, MenuActivity.class);
                                                  //inicia o intent anterior
                                                  startActivity(voltar_home);
                                                  //chama e invoca os eventos de animação
                                                  overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
                                                  //encerra a activity
                                                  finish();
                                              }
                                          },
                        //define o tempo de retorno após a ação do método e encerramento da activity
                        tempo_de_retorno);
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK)
            Log.d("back button:", "Botão voltar do aparelho pressionado");

        return false;
        //desabilitou o o botão voltar nativo do aparelho, desta forma evita erros de rotas
    }


    //metodo que reporta bug via e-mail por intent
    private void reportaviaemail()
    {
        //variáveis tipo coleta de string e sumário de strings necessárias para o envio de e-mail, todas com opções de tradução
        meuemail = getString(R.string.meuemail);
        assunto = getString(R.string.assuntoemail);
        String[] recipients = meuemail.split(",");
        mensagem = getString(R.string.mensagememail);
        enunciadoIntentReportEmail = getString(R.string.enunciadointentbug);

        //chama o intent de e-mail:

        //cria o tipo de intent
        bug_email_report = new Intent(Intent.ACTION_SEND);
        //inclui o e-mail destinado ao aviso do bug
        bug_email_report.putExtra(Intent.EXTRA_EMAIL, meuemail);
        //inclui o título da mensagem que será contida no e-mail
        bug_email_report.putExtra(Intent.EXTRA_SUBJECT, assunto);
        //inclui a instrução inicial de como compor a mensagem
        bug_email_report.putExtra(Intent.EXTRA_TEXT, mensagem);

        //cria o tipo de mensagem
        bug_email_report.setType("message/rfc822");
        //inicia o serviço do intent
        startActivity(Intent.createChooser(bug_email_report, enunciadoIntentReportEmail));

    }

}
