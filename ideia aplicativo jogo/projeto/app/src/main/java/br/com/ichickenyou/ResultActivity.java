package br.com.ichickenyou;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Process;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import pl.droidsonroids.gif.GifImageView;

public class ResultActivity extends AppCompatActivity {

    TextView resultadofinalinicial;
    Intent coleta_acitivity_anterior;
    Bundle agrupador;
    SharedPreferences som;
    String resultado_campo_original, preferencia_som, ativo, desativado;
    ImageButton bt_salvar, bt_compartilhar_resultado, bt_sair_do_jogo;
    AlertDialog.Builder builder;
    AlertDialog dialog;
    String[] NomeListaRedesSociais;
    MediaPlayer mp;
    pl.droidsonroids.gif.GifImageView galinha_final;
    Calendar calendario;
    int dia_da_semana;
    private Intent encerra_resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //encontra o gif que servirá como animação para o resultado
        galinha_final = (GifImageView)findViewById(R.id.galinha_final);

        //encontra o campo de resultado final inicial
        resultadofinalinicial = (TextView)findViewById(R.id.resultadofinalinicial);

        //dentro do contexto da aplicação seleciona o audio desejado, nulo pois obterá valor posteriormente
        //mp = MediaPlayer.create(this, null);

        //obtem os resíduos da activity anterior
        coleta_acitivity_anterior = getIntent();

        //agrupa os resíduos da activity anterior ao resultado
        agrupador = coleta_acitivity_anterior.getExtras();

        //váriavel que adquire a instancia de calendário
        calendario = Calendar.getInstance();

        //váriavel que adquire o dia da semana a partir de calendário
        dia_da_semana = calendario.get(Calendar.DAY_OF_WEEK);

        //função que coleta da activity anterior
        if (agrupador != null)
        {
            //chama o metodo para nomear os dados originais
            nomeiaTextView();
            //de acordo com o dia da semana define o gif
            diaDasemana();
            //cria o efeito sonoro
            comSom();

        }
        else {
            Toast.makeText(this, "algo errado", Toast.LENGTH_SHORT).show();
        }

        //variável de instancia que encontra o botão de salvar
        bt_salvar = (ImageButton)findViewById(R.id.bt_salvar);

        //aciona o método de salvar
        bt_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ResultActivity.this, "botão salvar clicado", Toast.LENGTH_SHORT).show();
            }
        });

        //variável de instancia que encontra o botão de compartilhar
        bt_compartilhar_resultado = (ImageButton)findViewById(R.id.bt_compartilhar_resultado);

        //aciona o método de compartilhar
        bt_compartilhar_resultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acionaCompartilhar();
            }
        });

        //variável de instancia que encontra o botão de encerrar
        bt_sair_do_jogo = (ImageButton)findViewById(R.id.bt_sair_do_jogo);

        //aciona o metodo que encerra o resultado
        bt_sair_do_jogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                encerraResultado();
            }

        });


    }

    private void acionaCompartilhar()
    {
        // define o alerta com dialogo
        builder = new AlertDialog.Builder(this);
        //Não permite cancelar clicando fora do alerta
                builder.setCancelable(false);
        builder.setTitle(R.string.choose_social_destination);


        //Encontra o Array que servirá de lista para alimentar o dialogo do alerta de redes sociais
        NomeListaRedesSociais = getResources().getStringArray(R.array.redes_sociais);
        //Define a lista de nomes das redes sociais no dialogo do alerta e cria o evento deste método
        builder.setItems(NomeListaRedesSociais, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    //Twitter
                    case 0:
                        Toast.makeText(ResultActivity.this, R.string.Twitter, Toast.LENGTH_SHORT).show();
                        Log.d("Escolheu twitter", getResources().getString(R.string.Twitter));
                        break;
                    //Kakao
                    case 1:
                        Toast.makeText(ResultActivity.this, R.string.Kakao, Toast.LENGTH_SHORT).show();
                        Log.d("Escolheu kakao", getResources().getString(R.string.Kakao));
                        break;
                    //Fechar
                    case 2:
                        //cria um log e encerra a mensagem de dialogo
                        Log.d("Escolheu fechar", "Fechou a escolha de redes sociais");
                        break;
                }
            }
        });
        //Gera o alerta com o dialogo
        dialog = builder.create();
        dialog.show();
    }

    public void diaDasemana()
    {

        switch (dia_da_semana)
        {
            case Calendar.SUNDAY:
                Log.d("domingo", "domingo");
                galinha_final.setBackgroundResource(R.drawable.danca_de_rua);
                //dentro do contexto da aplicação seleciona o audio desejado
                mp = MediaPlayer.create(this, R.raw.resultado);
                break;
            case  Calendar.MONDAY:
                Log.d("2° feira", "segunda");
                galinha_final.setBackgroundResource(R.drawable.danca_dos_dedos);
                //dentro do contexto da aplicação seleciona o audio desejado
                mp = MediaPlayer.create(this, R.raw.resultado);
                break;
            case  Calendar.TUESDAY:
                Log.d("3° feira", "terça");
                galinha_final.setBackgroundResource(R.drawable.palmas);
                //dentro do contexto da aplicação seleciona o audio desejado
                mp = MediaPlayer.create(this, R.raw.resultado);
                break;
            case  Calendar.WEDNESDAY:
                Log.d("4° feira", "quarta");
                galinha_final.setBackgroundResource(R.drawable.jato_gema);
                //dentro do contexto da aplicação seleciona o audio desejado
                mp = MediaPlayer.create(this, R.raw.resultado);
                break;
            case  Calendar.THURSDAY:
                Log.d("5° feira", "quinta");
                galinha_final.setBackgroundResource(R.drawable.flatuencia);
                //dentro do contexto da aplicação seleciona o audio desejado
                mp = MediaPlayer.create(this, R.raw.resultado);
                break;
            case  Calendar.FRIDAY:
                Log.d("6° feira", "sexta");
                galinha_final.setBackgroundResource(R.drawable.tapa);
                //dentro do contexto da aplicação seleciona o audio desejado
                mp = MediaPlayer.create(this, R.raw.resultado);
                break;
            case  Calendar.SATURDAY:
                Log.d("sabado", "sabado");
                galinha_final.setBackgroundResource(R.drawable.flatuencia);
                //dentro do contexto da aplicação seleciona o audio desejado
                mp = MediaPlayer.create(this, R.raw.resultado);
                break;
            default:
                Log.d("erro no calendário", "erro calendário");
                galinha_final.setImageResource(R.drawable.danca_dos_dedos);
                //dentro do contexto da aplicação seleciona o audio desejado
                mp = MediaPlayer.create(this, R.raw.resultado);
                break;
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
            //ativa o som para o início de jogo
            mp.start();
        }
        else
        {
            //se a opção de audio desativado for verdadeira, então a função faz um log no aplicativo
            Log.d("audio desativado", "audio desativado para tocar o som de play");
        }

    }

    //metódo que nomeia o campo onde o resultado deve ser inserido
    private void nomeiaTextView()
    {
        //busca o dado da activity anterior por meio de um indice tipo string e uma string com os dados
       resultado_campo_original = (String)
               agrupador.get("resultado");
       //nomeia o campo de resultado original
       resultadofinalinicial.setText(resultado_campo_original);
    }

    //metodo usado para encerrar a activity de resultados
    private void encerraResultado()
    {
        //cria o intent para sair da activy
        encerra_resultado = new Intent(this, MenuActivity.class);
        //gera a activity que destina o contexto
        startActivity(encerra_resultado);

        //chama e invoca os eventos de animação
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
        //encerra a activity
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK)
            Log.d("back button:", "Botão voltar do aparelho pressionado");

        return false;
        //desabilitou o o botão voltar nativo do aparelho, desta forma evita erros de rotas
    }
}
