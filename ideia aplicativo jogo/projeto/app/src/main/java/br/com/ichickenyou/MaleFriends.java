package br.com.ichickenyou;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MaleFriends extends AppCompatActivity {

    ImageButton bt_adicionar;
    LinearLayout dynamicview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_male_friends);


        bt_adicionar = (ImageButton)findViewById(R.id.bt_adicionar);

        dynamicview = (LinearLayout)findViewById(R.id.conteudo_amigos);
        LinearLayout.LayoutParams lprams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);


        bt_adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                for (int i=0; i<1; i++)
//                {
//                    Button btn = new Button(MaleFriends.this);
//                    btn.setId(i+1);
//                    btn.setText("Button"+(i+1));
//                    final int index = i;
//                    dynamicview.addView(btn);
//                    Log.i("TAG", "The index is" + index);
//                }

                //cria a textview que anuncia o pedido do nome do amigo(a)

                for (int txtVw1 = 0; txtVw1 < 1; txtVw1++)
                {
                    TextView txtVw_escolhanome = new TextView(MaleFriends.this);
                    txtVw_escolhanome.setId(txtVw1 + 1);
                    txtVw_escolhanome.setText(R.string.titlemenunames);
                    final int index = txtVw1;
                    dynamicview.addView(txtVw_escolhanome);
                    Log.i("TextView escolha o nome", "Index: " + index);
                }

                //cria a autocompletetextview onde o usuário pode escrever o nome do amigo(a)

                for (int nome_autocomplete = 0; nome_autocomplete < 1; nome_autocomplete++)
                {
                    AutoCompleteTextView nome_autocomplete_field = new AutoCompleteTextView(MaleFriends.this);
                    nome_autocomplete_field.setId(nome_autocomplete + 1);
                    final int index = nome_autocomplete;
                    dynamicview.addView(nome_autocomplete_field);
                    Log.i("ATxtView escreve o nome", "Index: " + index);
                }

                //cria a textview que anuncia a seleção da parte da galinha

                for (int txtVw2 = 0; txtVw2 < 1; txtVw2++)
                {
                    TextView txtVw_escolhaparte = new TextView(MaleFriends.this);
                    txtVw_escolhaparte.setId(txtVw2 + 1);
                    txtVw_escolhaparte.setText(R.string.titlechickenparts);
                    final int index = txtVw2;
                    dynamicview.addView(txtVw_escolhaparte);
                    Log.i("TxtV escolha a parte", "Index: " + index);
                }

                //cria o spinner onde o usuário seleciona as partes da galinha

                for (int spinner = 0; spinner < 1; spinner++)
                {
                    Spinner galinha_spinner = new Spinner(MaleFriends.this);
                    galinha_spinner.setId(spinner + 1);
                    final int index = spinner;
                    dynamicview.addView(galinha_spinner);
                    Log.i("cria spinner da galinha", "Index: " + index);
                }

                //cria a textview que anuncia a ações

                for (int txtVw3 = 0; txtVw3 < 1; txtVw3++)
                {
                    TextView txtVw_escolhaacao = new TextView(MaleFriends.this);
                    txtVw_escolhaacao.setId(txtVw3 + 1);
                    txtVw_escolhaacao.setText(R.string.titlethingstodo);
                    final int index = txtVw3;
                    dynamicview.addView(txtVw_escolhaacao);
                    Log.i("TxtV escolha a ação", "Index: " + index);
                }

                //cria a autocompletetextview onde o usuário pode escrever o nome da ação

                for (int nome_acao = 0; nome_acao < 1; nome_acao++)
                {
                    AutoCompleteTextView nome_acao_field = new AutoCompleteTextView(MaleFriends.this);
                    nome_acao_field.setId(nome_acao + 1);
                    final int index = nome_acao;
                    dynamicview.addView(nome_acao_field);
                    Log.i("ATxtView escreve a ação", "Index: " + index);
                }

                //cria o separador
                for (int separador = 0; separador < 1; separador++)
                {
                    View linha_separador = new View(MaleFriends.this);
                    linha_separador.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
//                    linha_separador.getLayoutParams().width = 1;
//                    linha_separador.getLayoutParams().height = 2;
                    linha_separador.setLayoutParams(new LinearLayout.LayoutParams(200, 200));
                }


            }
        });




    }



    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
    }
}
