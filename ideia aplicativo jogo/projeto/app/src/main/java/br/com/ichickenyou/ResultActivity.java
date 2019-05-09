package br.com.ichickenyou;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    TextView resultadofinalinicial;
    Intent coleta_acitivity_anterior;
    Bundle agrupador;
    String resultado_campo_original;

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
