package br.com.ichickenyou;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MaleFriends extends AppCompatActivity {

    ImageButton bt_adicionar, bt_retorna;
    LinearLayout dynamicview;
    ScrollView scrollView_content_friends;
    FloatingActionButton bt_scroll2up;
    AutoCompleteTextView nome_autocomplete, fazer_autocomplete;
    ArrayAdapter<String> nomes_masculinos, partes_galinha, gosta_de_fazer_ou_faz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_male_friends);

        //encontra o campo de texto para nomes
        nome_autocomplete = (AutoCompleteTextView)findViewById(R.id.nome_autocomplete);

        //encontra o campo de texto para ações
        fazer_autocomplete = (AutoCompleteTextView)findViewById(R.id.fazer_autocomplete);

        //cria o array adapter de nomes masculinos
        nomes_masculinos = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.malenames));

        //cria o array adapter de partes da galinha
        partes_galinha = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.chickenparts));

        //cria o array adapter de ações que o usuário gosta de fazer ou faz
        gosta_de_fazer_ou_faz = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.thingstodo));

        //define o array adapter no campo de texto para nomes
        nome_autocomplete.setAdapter(nomes_masculinos);
        //delimita o tamanho do campo de texto para nomes
        nome_autocomplete.setThreshold(1);
        //define a largura do dropdown do autocomplete
        nome_autocomplete.setDropDownWidth(700);

        //define o array adapter no campo de texto para o nome das ações
        fazer_autocomplete.setAdapter(gosta_de_fazer_ou_faz);
        //delimita o tamanho do campo de texto para ações
        nome_autocomplete.setThreshold(1);
        //define a largura do dropdown do autocomplete
        nome_autocomplete.setDropDownWidth(700);

        //instancia que localiza o scrollview da activity
        scrollView_content_friends = (ScrollView)findViewById(R.id.scrollView_content_friends);


        bt_retorna = (ImageButton)findViewById(R.id.bt_next_step);

        bt_retorna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MaleFriends.this, "clicado", Toast.LENGTH_SHORT).show();
            }
        });

        //instancia que localiza o floating button para subir
        bt_scroll2up = (FloatingActionButton)findViewById(R.id.bt_scroll2up);
        //esconde o botão de subir por padrão
        bt_scroll2up.hide();

        //define o evento de clique no botão subir
        bt_scroll2up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sobe em direção ao ponto mais alto da tela
                scrollView_content_friends.fullScroll(View.FOCUS_UP);
                //oculta o botão que sobe ao topo do layout da activity atual
                bt_scroll2up.hide();
            }
        });

        bt_adicionar = (ImageButton)findViewById(R.id.bt_adicionar);

        dynamicview = (LinearLayout)findViewById(R.id.conteudo_amigos);
        LinearLayout.LayoutParams lprams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);


        bt_adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //exibe o botão de subir
                bt_scroll2up.show();
                //metodo que desce o scrollview ao final
                scrollView_content_friends.fullScroll(View.FOCUS_DOWN);

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
                    nome_autocomplete_field.setAdapter(nomes_masculinos);
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
                    //partes_galinha = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.malenames));
                    galinha_spinner.setId(spinner + 1);
                    galinha_spinner.setAdapter(partes_galinha);
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
                    nome_acao_field.setAdapter(gosta_de_fazer_ou_faz);
                    dynamicview.addView(nome_acao_field);
                    Log.i("ATxtView escreve a ação", "Index: " + index);
                }

                //cria o separador claro
                for (int separador = 0; separador < 1; separador++)
                {
                    TextView linha_separador = new TextView(MaleFriends.this);
                    linha_separador.setId(separador + 1);
                    linha_separador.setBackgroundColor(Color.WHITE);
                    linha_separador.setTextSize(1);
                    linha_separador.setText(" ");
                    dynamicview.addView(linha_separador);
                }

                //cria o botão excluir form
                for (int btForm = 0; btForm < 1; btForm++)
                {
                    final Button bt_delete_form = new Button(MaleFriends.this);
                    bt_delete_form.setId(btForm + 1);
                    bt_delete_form.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    bt_delete_form.setTextColor(Color.WHITE);
                    bt_delete_form.setText(R.string.bt_erase_text);
                    bt_delete_form.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bt_delete_form.setVisibility(View.GONE);
                        }
                    });
                    dynamicview.addView(bt_delete_form);
                }

                //cria o segundo separador claro
                for (int separador = 0; separador < 1; separador++)
                {
                    TextView linha_separador = new TextView(MaleFriends.this);
                    linha_separador.setId(separador + 1);
                    linha_separador.setBackgroundColor(Color.WHITE);
                    linha_separador.setTextSize(1);
                    linha_separador.setText(" ");
                    dynamicview.addView(linha_separador);
                }

                //cria o separador escuro
                for (int separador = 0; separador < 1; separador++)
                {
                    TextView linha_separador = new TextView(MaleFriends.this);
                    linha_separador.setId(separador + 1);
                    linha_separador.setBackgroundColor(Color.BLACK);
                    //linha_separador.setLayoutParams(new LinearLayout.LayoutParams(200, 200));
                    linha_separador.setTextSize(1);
                    linha_separador.setText(" ");
                    dynamicview.addView(linha_separador);
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
