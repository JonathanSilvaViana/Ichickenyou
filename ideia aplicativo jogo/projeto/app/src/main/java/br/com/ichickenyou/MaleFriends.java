package br.com.ichickenyou;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import org.apache.commons.lang3.StringUtils;




public class MaleFriends extends AppCompatActivity {

    ImageButton bt_adicionar, bt_avanca_resultado;
    LinearLayout dynamicview;
    ScrollView scrollView_content_friends;
    FloatingActionButton bt_scroll2up;
    AutoCompleteTextView nome_autocomplete, fazer_autocomplete;
    ArrayAdapter<String> nomes_masculinos, partes_galinha, gosta_de_fazer_ou_faz;
    String nome_amigo, nome_acao, pega_acao_formatada, pega_parte_galinha_formatada, de, espaco, pega_nome_formatado, preposicao_plural;
    Spinner menu_partes_galinha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_male_friends);

        //encontra o campo de texto para nomes
        nome_autocomplete = (AutoCompleteTextView)findViewById(R.id.nome_autocomplete);
        //coleta o texto digitado no campo de nome
        nome_amigo = nome_autocomplete.getText().toString();

        //encontra o campo de selelção para partes da galinha
        menu_partes_galinha = (Spinner)findViewById(R.id.menu_partes_galinha);
        //coleta o texto do menu de seleção de parte da galinha
        menu_partes_galinha.getSelectedItem().toString();

        //encontra o campo de texto para ações
        fazer_autocomplete = (AutoCompleteTextView)findViewById(R.id.fazer_autocomplete);
        //coleta o texto digitado no campo de ações
        nome_acao = fazer_autocomplete.getText().toString();

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

        //cria a instancia localizando o botão que avança ao resultado
        bt_avanca_resultado = (ImageButton)findViewById(R.id.bt_next_step);

        //evento que move à página de resultados
        bt_avanca_resultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MaleFriends.this, "Clicado", Toast.LENGTH_SHORT).show();

                //teste para obter os dados

                //gerador de espaço
                espaco = " ";
                //pega o nome digitado no campo de nome
                String pega_nome = nome_autocomplete.getText().toString();
                //define o nome como inicial maiúscula, usando o pacote StringUtils, do projeto apache
                pega_nome_formatado = StringUtils.capitalize(pega_nome);
                //pega a ação digitado no campo de ação
                String pega_acao = fazer_autocomplete.getText().toString();
                //define a ação como inicial maiúscula, usando o pacote StringUtils, do projeto apache
                pega_acao_formatada = StringUtils.capitalize(pega_acao);
                //pega o nome da parte da galinha escolhida no campo de escolher a parte da galinha
                String pega_parte_galinha = menu_partes_galinha.getSelectedItem().toString();
                //define a parte da galinha como todas letras minúsculas, usando o pacote StringUtils, do projeto apache
                pega_parte_galinha_formatada = StringUtils.lowerCase(pega_parte_galinha);
                //checa se o nome da parte final da galinha termina com "s", ou seja, se está no plural.
                Boolean checa_se_termina_com_s = StringUtils.endsWith(pega_parte_galinha_formatada, "s");
                if(checa_se_termina_com_s == true)
                {
                    Toast.makeText(MaleFriends.this, "plural", Toast.LENGTH_SHORT).show();
                    //aplicar a string array com seus valores no contexto

                    String[] preposicoes = {
                            "de",
                            "da",
                            "do",
                            "dos",
                            "das",
                            "na",
                            "nas",
                            "no",
                            "nos",
                            "sob",
                            "em cima",
                            "em cima de",
                            "em cima da",
                            "em cima das",
                            "em cima do",
                            "em cima dos"
                    };
                    preposicao_plural= "das" + espaco;
                }
                else {
                    Toast.makeText(MaleFriends.this, "singular", Toast.LENGTH_SHORT).show();
                    preposicao_plural = "";
                }

                de = "de";
                //variável que unifica as informações
                String unificador = pega_acao_formatada + espaco + preposicao_plural + pega_parte_galinha_formatada + espaco + de + espaco + pega_nome_formatado;

                Intent vai_ao_resultado = new Intent(MaleFriends.this, ResultActivity.class);
                String resultado_unificado = "resultado";
                vai_ao_resultado.putExtra(resultado_unificado, unificador);
                startActivity(vai_ao_resultado);
                finish();

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

                //gera o formulário sintético

                for (int form = 0; form < 1; form ++)
                {
                    //cria a textview que anuncia o pedido do nome do amigo(a)

                    final TextView txtVw_escolhanome = new TextView(MaleFriends.this);
                    txtVw_escolhanome.setId(form + 1);
                    txtVw_escolhanome.setText(R.string.titlemenunames);
                    final int index = form;
                    dynamicview.addView(txtVw_escolhanome);
                    Log.i("TextView escolha o nome", "Index: " + index);

                    //cria a autocompletetextview onde o usuário pode escrever o nome do amigo(a)

                    final AutoCompleteTextView nome_autocomplete_field = new AutoCompleteTextView(MaleFriends.this);
                    nome_autocomplete_field.setId(form + 1);
                    final int index2 = form;
                    nome_autocomplete_field.setAdapter(nomes_masculinos);
                    dynamicview.addView(nome_autocomplete_field);
                    Log.i("ATxtView escreve o nome", "Index: " + index2);

                    //cria a textview que anuncia a seleção da parte da galinha

                    final TextView txtVw_escolhaparte = new TextView(MaleFriends.this);
                    txtVw_escolhaparte.setId(form + 1 + 1);
                    txtVw_escolhaparte.setText(R.string.titlechickenparts);
                    final int index3 = form;
                    dynamicview.addView(txtVw_escolhaparte);
                    Log.i("TxtV escolha a parte", "Index: " + index3);

                    //cria o spinner onde o usuário seleciona as partes da galinha

                    final Spinner galinha_spinner = new Spinner(MaleFriends.this);
                    //partes_galinha = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.malenames));
                    galinha_spinner.setId(form + 1);
                    galinha_spinner.setAdapter(partes_galinha);
                    final int index4 = form;
                    dynamicview.addView(galinha_spinner);
                    Log.i("cria spinner da galinha", "Index: " + index4);

                    //cria a textview que anuncia a ações

                    final TextView txtVw_escolhaacao = new TextView(MaleFriends.this);
                    txtVw_escolhaacao.setId(form + 1 + 1 + 1);
                    txtVw_escolhaacao.setText(R.string.titlethingstodo);
                    final int index5 = form;
                    dynamicview.addView(txtVw_escolhaacao);
                    Log.i("TxtV escolha a ação", "Index: " + index5);

                    //cria a autocompletetextview onde o usuário pode escrever o nome da ação

                    final AutoCompleteTextView nome_acao_field = new AutoCompleteTextView(MaleFriends.this);
                    nome_acao_field.setId(form + 1 + 1);
                    final int index6 = form;
                    nome_acao_field.setAdapter(gosta_de_fazer_ou_faz);
                    dynamicview.addView(nome_acao_field);
                    Log.i("ATxtView escreve a ação", "Index: " + index6);

                    //cria o separador claro

                    final TextView linha_separador = new TextView(MaleFriends.this);
                    linha_separador.setId(form + 1 + 1 + 1 + 1);
                    linha_separador.setBackgroundColor(Color.WHITE);
                    linha_separador.setTextSize(1);
                    linha_separador.setText(" ");
                    dynamicview.addView(linha_separador);

                    //cria o botão excluir form

                    final Button bt_delete_form = new Button(MaleFriends.this);
                    bt_delete_form.setId(form + 1);
                    bt_delete_form.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    bt_delete_form.setTextColor(Color.WHITE);
                    bt_delete_form.setText(R.string.bt_erase_text);
                    dynamicview.addView(bt_delete_form);

                    //cria o segundo separador claro

                    final TextView linha_separador2 = new TextView(MaleFriends.this);
                    linha_separador2.setId(form + 1 + 1 + 1 + 1 + 1);
                    linha_separador2.setBackgroundColor(Color.WHITE);
                    linha_separador2.setTextSize(1);
                    linha_separador2.setText(" ");
                    dynamicview.addView(linha_separador2);

                    //cria o separador escuro

                    final TextView linha_separador3 = new TextView(MaleFriends.this);
                    linha_separador3.setId(form + 1 + 1 + 1 + 1 + 1 + 1);
                    linha_separador3.setBackgroundColor(Color.BLACK);
                    linha_separador3.setTextSize(1);
                    linha_separador3.setText(" ");
                    dynamicview.addView(linha_separador3);

                    //quando o botão de deletar form for clicado, o formulário onde é contido será deletado junto a todos elementos nele contidos
                    bt_delete_form.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //apaga todos os campos do formulário, a partir do campo criado
                            txtVw_escolhanome.setVisibility(View.GONE);
                            nome_autocomplete_field.setVisibility(View.GONE);
                            txtVw_escolhaparte.setVisibility(View.GONE);
                            nome_autocomplete_field.setVisibility(View.GONE);
                            txtVw_escolhaparte.setVisibility(View.GONE);
                            galinha_spinner.setVisibility(View.GONE);
                            txtVw_escolhaacao.setVisibility(View.GONE);
                            nome_acao_field.setVisibility(View.GONE);
                            linha_separador.setVisibility(View.GONE);
                            bt_delete_form.setVisibility(View.GONE);
                            linha_separador2.setVisibility(View.GONE);
                            linha_separador3.setVisibility(View.GONE);
                        }
                    });

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

