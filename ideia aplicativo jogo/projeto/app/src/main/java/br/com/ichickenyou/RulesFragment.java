package br.com.ichickenyou;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;
import static android.provider.Telephony.Mms.Part.TEXT;
import static br.com.ichickenyou.BuildConfig.APPLICATION_ID;

public class RulesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Switch aceitaounao;
    Typeface fonte_coreana;
    String idioma_coreano, outros_idiomas, nome_do_arquivo_de_persistencia, caminho_aceito;
    boolean se_idioma_coreano;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //tempo de transição da função de desinstalar o aplicativo e mudar fragment após mensagem de feedback agradecendo pela instalação
    int tempo_encerramento = 2800;
    //posiciona a ação de troca de intents
    Handler posicionador = new Handler();
    Fragment homeFragment;

    private OnFragmentInteractionListener mListener;

    public RulesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RulesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RulesFragment newInstance(String param1, String param2) {
        RulesFragment fragment = new RulesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rules, null, false);

    }




    //método construtor de ações na view do fragment
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //encontra o botão tipo switch, que concede ao usuário a opção de aceitar ou não as regras do aplicativo
        aceitaounao = (Switch) view.findViewById(R.id.toggleButton);

        //variável que procederá o nome do arquivo de persistência
        nome_do_arquivo_de_persistencia = "ACEITE";

        //cria a persistência de aceite de normas
        final SharedPreferences aceite = getActivity().getSharedPreferences(nome_do_arquivo_de_persistencia, Context.MODE_PRIVATE);

        //essa função permite o aceite das regras que constituem o aplicativo
        aceitaounao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //executa condicional quando checado
                if(aceitaounao.isChecked())
                {

                    try {
                        //cria as configurações se o aplicativo tiver as regras aceitas, no arquivo de persistência
                        SharedPreferences.Editor editor = aceite.edit();
                        editor.putString("regras aceitas", "aceitado");
                        editor.putBoolean("ACEITO", aceitaounao.isChecked());
                        editor.commit();

                        //feedback de agradecimento pelo aceite
                        Toast.makeText(getContext(), R.string.grato, Toast.LENGTH_SHORT).show();

                        //inclui as normas de aceite no arquivo de persistência
                        String aceitei = aceite.getString("regras aceitas", "aceitado");
                        Boolean aceito = aceite.getBoolean("ACEITO", aceitaounao.isChecked());
                        aceitaounao.setChecked(true);

                        //chama o fragment de home
                        posicionador.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                homeFragment = new HomeFragment();
                                getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.content_rules_fragment, homeFragment, "passa à home")
                                        .addToBackStack(null)
                                        .commit();
                            }
                        },

                                tempo_encerramento);

                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "Erro", Toast.LENGTH_SHORT).show();
                    }

                }

                else
                {
                    //agradece o uso e solicita a desinstalação avisando em três idiomas distintos
                    String recusou = getResources().getString(R.string.aindasimgrato);
                    Toast.makeText(getContext(), recusou, Toast.LENGTH_LONG).show();


                    //desinstala o aplicativo em caso de recusa das regras

                    posicionador.postDelayed(new Runnable() {

                        public void run() {
                            //A string packagename recebe o id da aplicação e logo a converte para uma string
                            String nomedopacote = APPLICATION_ID.toString();
                            //concatena a indicação de pacote com a string packagename gerando um endereço de aplicação válida
                            Uri packageUri = Uri.parse("package:" + nomedopacote);
                            Intent uninstallIntent =
                                    new Intent(Intent.ACTION_DELETE, packageUri); //compatível para todas edições do android
                            startActivity(uninstallIntent);
                        }
                    }, tempo_encerramento);

                }
            }

        });


        //define o texto do botão de aceite ou não

        //instancia o nome da variável gravada no arquivo de persistência
        caminho_aceito = "ACEITO";
        //busca o nome da variável de persistência informada tida como verdadeira
        aceite.getBoolean(caminho_aceito, true);

        //condição se houver variável de aceite, define o botão como marcado de aceite, se não define como contrário.
        if (aceite.contains(caminho_aceito) == true)
        {
            aceitaounao.setChecked(true);
        }
        else {
            aceitaounao.setChecked(false);
        }


        //mudar a fonte quando o idioma coreano estiver ativado

        //variável que define a string do idioma coreano
        idioma_coreano = "ko";

        //variável boolean que define se o idioma rastreado, nesse caso encapsulado da variável "idioma_coreano", acima;
        se_idioma_coreano = Locale.getDefault().getLanguage().equals(idioma_coreano);

        //essa variável coleta a string do idioma local que esteja no aparelho que execute a aplicação
        outros_idiomas = Locale.getDefault().getLanguage();

        //inclui o método getContext para uma variável aplicável em fragments
        Context contexto = getContext();

        //se o idioma for Coreano, então aplica a fonte compatível com o idioma coreano, assim como a proporção, do contrário exibe um log para
        if (se_idioma_coreano == true) {
            TextView titleRules = (TextView)view.findViewById(R.id.titleRules);
            Typeface typeface = ResourcesCompat.getFont(contexto, R.font.koreanfont);
            titleRules.setTypeface(typeface);
            titleRules.setTextSize(43);

        } else {
            //exibe um log do sistema dizendo qual o idioma rastreado
            Log.d("IDIOMA:", outros_idiomas);
        }

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
