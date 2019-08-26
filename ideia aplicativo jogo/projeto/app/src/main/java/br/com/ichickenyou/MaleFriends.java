package br.com.ichickenyou;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.res.ResourcesCompat;
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

import java.util.Calendar;
import java.util.Locale;


public class MaleFriends extends AppCompatActivity {

    ImageButton bt_adicionar, bt_avanca_resultado;
    LinearLayout dynamicview;
    ScrollView scrollView_content_friends;
    FloatingActionButton bt_scroll2up;
    AutoCompleteTextView nome_autocomplete, fazer_autocomplete;
    ArrayAdapter<String> nomes_masculinos, partes_galinha, gosta_de_fazer_ou_faz, partes_da_galinha_versao_coreana;
    String pega_nome, pega_acao, unificador, idioma_portugues, idioma_coreano, idioma_ingles ,nome_amigo, nome_acao, pega_acao_formatada, pega_parte_galinha_formatada, pega_parte_galinha, de, espaco, pega_nome_formatado, preposicao_plural;
    Spinner menu_partes_galinha;
    boolean se_idioma_portugues, se_idioma_coreano, se_idioma_ingles, checa_se_termina_com_s, checa_se_termina_com_cao,checa_se_termina_com_eca, checa_se_termina_com_gem, checa_se_termina_com_ras, checa_se_termina_com_ele, checa_se_termina_com_rra, checa_se_termina_com_ico, checa_se_termina_com_sta, checa_se_termina_com_hos, checa_se_termina_com_ado, checa_se_termina_com_ata, checa_se_termina_com_sa, checa_se_termina_com_ela, checa_se_termina_com_oxa, checa_se_termina_com_oxas, checa_se_termina_com_apo, checa_se_termina_com_ito;
    Calendar calendario;
    int dia_da_semana, tamanho_da_fonte_em_coreano;
    Spinner menu_partes_galinha_para_alterar_em_coreano;
    TextView Txt_view_escolher_nome_de_amigo, Txt_view_escolher_parte_da_galinha, Txt_view_escolher_algo_que_gosta_de_fazer;
    Typeface fonte_coreana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_male_friends);

        //variável que define o tamanho em valor inteiro da fonte
        tamanho_da_fonte_em_coreano = 43;

        //localiza a fonte compatível com o idioma coreano
        fonte_coreana = ResourcesCompat.getFont(this, R.font.koreanfont);

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

        //cria o array adapter de partes da galinha para o idioma português e inglês
        partes_galinha = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.chickenparts));

        //cria o array adapter de partes da galinha para o idioma coreano
        partes_da_galinha_versao_coreana = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.chickenparts_Korean_use_only));

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

                //variável que define um índice de valores tipo string contendo preposições da língua portuguesa usados no aplicativo
                String[] preposicoes_portugues = {
                        "de",
                        "da",
                        "do",
                        "dos",
                        "das",
                        "na",
                        "os",
                        "as",
                        "nas",
                        "no",
                        "nos",
                        "sob",
                        "em cima",
                        "em cima de",
                        "em cima da",
                        "em cima das",
                        "em cima do",
                        "em cima dos",
                        "sob a"
                };

                //variável que define um índice de valores tipo string contendo preposições da língua inglesa usados no aplicativo
                String[] preposicoes_ingles = {
                        "of",
                        "on",
                        "at",
                        "in",
                        "in the",
                        "'s",
                        "the"
                };

                //variável que define um índice de valores tipo string contendo preposições da língua coreana usados no aplicativo
                String[] preposicoes_coreano = {
                        "씨",
                        "는",
                        "은",
                        "의",
                        "이",
                        "가",
                        "에"
                };

                //gerador de espaço
                espaco = " ";
                //pega o nome digitado no campo de nome
                pega_nome = nome_autocomplete.getText().toString();
                //define o nome como inicial maiúscula, usando o pacote StringUtils, do projeto apache
                pega_nome_formatado = StringUtils.capitalize(pega_nome);
                //pega a ação digitado no campo de ação
                pega_acao = fazer_autocomplete.getText().toString();
                //define a ação como inicial maiúscula, usando o pacote StringUtils, do projeto apache
                pega_acao_formatada = StringUtils.capitalize(pega_acao);
                //pega o nome da parte da galinha escolhida no campo de escolher a parte da galinha
                pega_parte_galinha = menu_partes_galinha.getSelectedItem().toString();
                //define a parte da galinha como todas letras minúsculas, usando o pacote StringUtils, do projeto apache
                pega_parte_galinha_formatada = StringUtils.lowerCase(pega_parte_galinha);
                //checa se o nome da parte final da galinha termina com "s".
                checa_se_termina_com_s = StringUtils.endsWithIgnoreCase(pega_parte_galinha_formatada, "s");
                //checa se o nome da parte final da galinha termina com "hos"
                checa_se_termina_com_hos = StringUtils.endsWithIgnoreCase(pega_parte_galinha_formatada, "hos");
                //checa se o nome da parte final da galinha termina com "ado"
                checa_se_termina_com_ado = StringUtils.endsWithIgnoreCase(pega_parte_galinha_formatada, "ado");
                //checa se o nome da parte final da galinha termina com "pes"
                checa_se_termina_com_ata = StringUtils.endsWithIgnoreCase(pega_parte_galinha_formatada, "ata");
                //checa se o nome da parte final da galinha termina com "sa"
                checa_se_termina_com_sa = StringUtils.endsWithIgnoreCase(pega_parte_galinha_formatada, "sa");
                //checa se o nome da parte final da galinha termina com "ela"
                checa_se_termina_com_ela = StringUtils.endsWithIgnoreCase(pega_parte_galinha_formatada, "ela");
                //checa se o nome da parte final da galinha termina com "oxa"
                checa_se_termina_com_oxa = StringUtils.endsWithIgnoreCase(pega_parte_galinha_formatada, "oxa");
                //checa se o nome da parte final da galinha termina com "xas"
                checa_se_termina_com_oxas = StringUtils.endsWithIgnoreCase(pega_parte_galinha_formatada, "xas");
                //checa se o nome da parte final da galinha termina com "apo"
                checa_se_termina_com_apo = StringUtils.endsWithIgnoreCase(pega_parte_galinha_formatada, "apo");
                //checa se o nome da parte final da galinha termina com "ito"
                checa_se_termina_com_ito = StringUtils.endsWithIgnoreCase(pega_parte_galinha_formatada, "ito");
                //checa se o nome da parte final da galinha termina com "ico"
                checa_se_termina_com_ico = StringUtils.endsWithIgnoreCase(pega_parte_galinha_formatada, "ico");
                //checa se o nome da parte final da galinha termina com "sta"
                checa_se_termina_com_sta = StringUtils.endsWithIgnoreCase(pega_parte_galinha_formatada, "sta");
                //checa se o nome da parte final da galinha termina com "rra"
                checa_se_termina_com_rra = StringUtils.endsWithIgnoreCase(pega_parte_galinha_formatada, "rra");
                //checa se o nome da parte final da galinha termina com "ras"
                checa_se_termina_com_ras = StringUtils.endsWithIgnoreCase(pega_parte_galinha_formatada, "ras");
                //checa se o nome da parte final da galinha termina com "ele"
                checa_se_termina_com_ele = StringUtils.endsWithIgnoreCase(pega_parte_galinha_formatada, "ele");
                //checa se o nome da parte final da galinha termina com "gem"
                checa_se_termina_com_gem = StringUtils.endsWithIgnoreCase(pega_parte_galinha_formatada, "gem");
                //checa se o nome da parte final da galinha termina com "eça"
                checa_se_termina_com_eca = StringUtils.endsWithIgnoreCase(pega_parte_galinha_formatada, "eça");
                //checa se o nome da parte final da galinha termina com "cao"
                checa_se_termina_com_cao = StringUtils.endsWithIgnoreCase(pega_parte_galinha_formatada, "ção");
                de = preposicoes_portugues[0];
                //váriavel que adquire a instancia de calendário
                calendario = Calendar.getInstance();
                //váriavel que adquire o dia da semana a partir de calendário
                dia_da_semana = calendario.get(Calendar.DAY_OF_WEEK);

                //criar um switch checando idiomas e inserir as devidas funções

                //variável que nomina a sigla do idioma português
                idioma_portugues = "pt";

                //variável que obter o valor boleano do idioma português
                se_idioma_portugues = Locale.getDefault().getLanguage().equals(idioma_portugues);

                //variável que nomina a sigla do idioma coreano
                idioma_coreano = "ko";

                //variável que obter o valor boleano do idioma coreano
                se_idioma_coreano = Locale.getDefault().getLanguage().equals(idioma_coreano);

                //variável que nomina a sigla do idioma inglês
                idioma_ingles = "en";

                //variável que obter o valor boleano do idioma inglês
                se_idioma_ingles = Locale.getDefault().getLanguage().equals(idioma_ingles);

                //checa se o idioma português é dado por verdadeiro
                if (se_idioma_portugues)
                {
                    if(checa_se_termina_com_hos && checa_se_termina_com_s)
                    {
                        //define qual preposição incluir coletando um valor do array de preposições na língua portuguesa
                        preposicao_plural = preposicoes_portugues[10] + espaco;
                        Log.d("termina com 'os'", "plural?");
                    }

                    else if (checa_se_termina_com_s || checa_se_termina_com_oxas || checa_se_termina_com_ras)
                    {
                        Log.d("Palavra termina com 's'", "plural feminino");
                        preposicao_plural = preposicoes_portugues[8] + espaco;
                    }

                    else if (checa_se_termina_com_ado || checa_se_termina_com_cao || checa_se_termina_com_apo || checa_se_termina_com_ito || checa_se_termina_com_ico)
                    {
                        Log.d("termina com 'ado'", "ado");
                        preposicao_plural = preposicoes_portugues[9] + espaco;
                    }

                    else if (checa_se_termina_com_ata || checa_se_termina_com_ele || checa_se_termina_com_rra || checa_se_termina_com_sta || checa_se_termina_com_sa || checa_se_termina_com_ela || checa_se_termina_com_oxa)
                    {
                        Log.d("termina com 'ata'", "ata");
                        preposicao_plural = preposicoes_portugues[5] + espaco;
                    }

                    else if(checa_se_termina_com_gem)
                    {
                        Log.d("termina com 'gem'", "gem");
                        preposicao_plural = preposicoes_portugues[14] + espaco;
                    }

                    else if (checa_se_termina_com_eca)
                    {
                        Log.d("termina com 'eca'", "eca");

                        //função especial na "cabeça da galinha" medida a partir do dia da semana, afetando o resultado de maneira hilária especifica do idioma português

                        switch (dia_da_semana)
                        {

                            case Calendar.SUNDAY:
                                Log.d("1° feira :P", "domingo ok");
                                preposicao_plural = "pairando" + espaco + preposicoes_portugues[18] + espaco;
                                break;

                            case Calendar.MONDAY:
                                Log.d("2° feira", "2° ok");
                                preposicao_plural = "enquanto rodando" + espaco + preposicoes_portugues[18] + espaco;
                                break;

                            case Calendar.TUESDAY:
                                Log.d("3° feira", "3° ok");
                                preposicao_plural = "quebrando as leis da gravidade" + espaco + preposicoes_portugues[18] + espaco;
                                break;

                            case Calendar.WEDNESDAY:
                                Log.d("4° feira", "4° ok");
                                preposicao_plural = "novamente" + espaco + preposicoes_portugues[18] + espaco;
                                break;

                            case Calendar.THURSDAY:
                                Log.d("5° feira", "5° ok");
                                preposicao_plural = "e usando o app" + espaco + preposicoes_portugues[18] + espaco;
                                break;

                            case Calendar.FRIDAY:
                                Log.d("6° feira", "6° ok");
                                preposicao_plural = "e usando o app" + espaco + preposicoes_portugues[18] + espaco;
                                break;

                            case Calendar.SATURDAY:
                                Log.d("epaaa! é sábado!!!", "dia normal");
                                preposicao_plural = preposicoes_portugues[18] + espaco;
                                break;

                            default:
                                Log.d("erro feira", "erro calendário");
                                preposicao_plural = preposicoes_portugues[18] + espaco;
                                break;
                        }

                        //preposicao_plural = preposicoes_portugues[18] + espaco;
                    }

                    else
                    {
                        Log.d("Singular", "palavra no singular");
                        preposicao_plural = "";
                    }


                    //variável que unifica as informações em português
                    unificador =
                            pega_acao_formatada +
                            espaco +
                            preposicao_plural +
                            pega_parte_galinha_formatada +
                            espaco +
                            de +
                            espaco +
                            pega_nome_formatado
                    ;

                }
                //checa se o idioma é coreano
                else if (se_idioma_coreano)
                {

                    //encontra os arquivos necessários no arquivo content_friends.xml

                    //encontra a textview que pede para escolher o nome do amigo
                    Txt_view_escolher_nome_de_amigo = (TextView)findViewById(R.id.Txt_view_escolher_nome_de_amigo);
                    //encontra a textview que pede para escolher a parte da galinha
                    Txt_view_escolher_parte_da_galinha = (TextView)findViewById(R.id.Txt_view_escolher_parte_da_galinha);
                    //encontra a textview que pede para escolher o que gosta de fazer
                    Txt_view_escolher_algo_que_gosta_de_fazer = (TextView)findViewById(R.id.Txt_view_escolher_algo_que_gosta_de_fazer);

                    //formata os elementos de acordo com a necessidade do idioma coreano nessa versão

                    //inicialmente o tamanho da fonte de cada elemento
                    Txt_view_escolher_nome_de_amigo.setTextSize(tamanho_da_fonte_em_coreano);
                    Txt_view_escolher_parte_da_galinha.setTextSize(tamanho_da_fonte_em_coreano);
                    Txt_view_escolher_algo_que_gosta_de_fazer.setTextSize(tamanho_da_fonte_em_coreano);

                    //define a fonte compatível com caracteres coreanos
                    Txt_view_escolher_nome_de_amigo.setTypeface(fonte_coreana);
                    Txt_view_escolher_parte_da_galinha.setTypeface(fonte_coreana);
                    Txt_view_escolher_algo_que_gosta_de_fazer.setTypeface(fonte_coreana);

                    //encontra o menu spinner destinado as partes da galinha em coreano
                    menu_partes_galinha_para_alterar_em_coreano = (Spinner)findViewById(R.id.menu_partes_galinha);
                    //define o layout do menu dropdown do adapter
                    partes_da_galinha_versao_coreana.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //define as entradas do elemento spinner para o idioma coreano
                    menu_partes_galinha_para_alterar_em_coreano.setAdapter(partes_da_galinha_versao_coreana);

                    switch (dia_da_semana)
                    {

                        case Calendar.SUNDAY:
                            Log.d("1° feira :P", "domingo ok");
                            break;

                        case Calendar.MONDAY:
                            Log.d("2° feira", "2° ok");
                            pega_nome = "니 좋아하는" + espaco + pega_nome;
                            break;

                        case Calendar.TUESDAY:
                            Log.d("3° feira", "3° ok");
                            break;

                        case Calendar.WEDNESDAY:
                            Log.d("4° feira", "4° ok");
                            break;

                        case Calendar.THURSDAY:
                            Log.d("5° feira", "5° ok");
                            pega_nome = "우리 사랑하는" + espaco + pega_nome;
                            break;

                        case Calendar.FRIDAY:
                            Log.d("6° feira", "6° ok");
                            break;

                        case Calendar.SATURDAY:
                            Log.d("epaaa! é sábado!!!", "dia normal");
                            break;

                        default:
                            Log.d("erro feira", "erro calendário");
                            break;
                    }

                    //unifica o resultado calculado em coreano
                    unificador =
                                    pega_nome +
                                    preposicoes_coreano[0] +
                                    preposicoes_coreano[3] +
                                    espaco +
                                    pega_parte_galinha +
                                    preposicoes_coreano[6] +
                                    espaco +
                                    pega_acao
                    ;

                }
                else if (se_idioma_ingles)
                {
                    //completar idioma inglês

                    unificador =
                                    pega_acao_formatada +
                                    espaco +
                                    pega_nome_formatado +
                                    preposicoes_ingles[5] +
                                    espaco +
                                    pega_parte_galinha_formatada

                            ;
                }
                else
                {
                    Toast.makeText(MaleFriends.this, "Something wrong about language, alert the developer", Toast.LENGTH_SHORT).show();
                    Log.d("Erro de idioma", "Lang error!!!");
                }





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

