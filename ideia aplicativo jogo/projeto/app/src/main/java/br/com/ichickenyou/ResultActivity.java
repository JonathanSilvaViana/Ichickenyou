package br.com.ichickenyou;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
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

public class ResultActivity extends AppCompatActivity {

    TextView resultadofinalinicial;
    Intent coleta_acitivity_anterior;
    Bundle agrupador;
    String resultado_campo_original;
    ImageButton bt_salvar, bt_compartilhar_resultado;
    AlertDialog.Builder builder;
    AlertDialog dialog;
    String[] NomeListaRedesSociais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //encontra o campo de resultado final inicial
        resultadofinalinicial = (TextView)findViewById(R.id.resultadofinalinicial);

        coleta_acitivity_anterior = getIntent();

        agrupador = coleta_acitivity_anterior.getExtras();

        //função que coleta da activity anterior
        if (agrupador != null)
        {
            //chama o metodo para nomear os dados originais
            nomeiaTextView();
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


    private void nomeiaTextView()
    {
        //busca o dado da activity anterior por meio de um indice tipo string e uma string com os dados
       resultado_campo_original = (String)
               agrupador.get("resultado");
       //nomeia o campo de resultado original
       resultadofinalinicial.setText(resultado_campo_original);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK)
            Log.d("back button:", "Botão voltar do aparelho pressionado");

        return false;
        //desabilitou o o botão voltar nativo do aparelho, desta forma evita erros de rotas
    }
}
