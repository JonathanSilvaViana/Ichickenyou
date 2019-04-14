package br.com.ichickenyou;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.Locale;

import static br.com.ichickenyou.BuildConfig.APPLICATION_ID;


public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static int SPLASH_TIME_OUT = 140;
    MediaPlayer mp;
    Intent jogocomeca, entra_configuracao;
    Button play;
    Typeface fonte_coreana;
    String idioma_coreano, outros_idiomas, caminho_aceito;
    boolean se_idioma_coreano;
    SharedPreferences aceite;
    //tempo de transição da função de ir as regras do aplicativo
    int tempo_encerramento = 0;
    Fragment rulesFragment;
    //posiciona a ação de troca de intents
    Handler posicionador = new Handler();
    FloatingActionButton bt_configuracoes;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    boolean doubleBackToExitPressedOnce = false;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //botão de iniciar jogo
        play = (Button)view.findViewById(R.id.button);

        //busca o arquivo de persistência
        aceite = getActivity().getSharedPreferences("ACEITE", Context.MODE_PRIVATE);
        //instancia o nome da variável gravada no arquivo de persistência
        caminho_aceito = "ACEITO";
        //busca o nome da variável de persistência informada tida como verdadeira
        aceite.getBoolean(caminho_aceito, true);

        //evento de click sob o botão de play
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //verifica se o arquivo de persistência possuí a variável de aceite informada antes do evento de clique no botão de play
                if (aceite.contains(caminho_aceito) == true)
                {
                    //dentro do contexto da aplicação seleciona o audio desejado
                    mp = MediaPlayer.create(getContext(), R.raw.play);
                    //ativa o som para o início de jogo
                    mp.start();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            jogocomeca = new Intent(getContext(), GenderActivity.class);
                            startActivity(jogocomeca);
                            //overridePendingTransition();

                        }
                    }, SPLASH_TIME_OUT);
                }
                else
                {
                    //cria um menu que questiona o usuário se deseja aceitar as regras do aplicativo caso existam, ou se prefere deixar para um momento posterior
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                    //define o título
                    alertDialogBuilder.setTitle(R.string.aceite_primeiro);
                    alertDialogBuilder
                            //define o subtítulo
                            .setMessage(R.string.motivar)
                            .setCancelable(false)
                            //botão que o usuário pode deixar para decidir posteriormente
                            .setPositiveButton(R.string.depois,
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            getActivity().moveTaskToBack(true);
                                            //mata o processo
                                            android.os.Process.killProcess(android.os.Process.myPid());
                                            //minimiza o aplicativo similar a uma função de sair
                                            System.exit(1);
                                        }
                                    })

                            //botão que o usuário decide se quer visualizar as regras para então decidir se quer aceitar ou não as regras de uso do aplicativo
                            .setNegativeButton(R.string.ir_regras, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    posicionador.postDelayed(new Runnable() {
                                                                 @Override
                                                                 public void run() {
                                                                     rulesFragment = new RulesFragment();
                                                                     getActivity().getSupportFragmentManager().beginTransaction()
                                                                             .replace(R.id.include_home_content, rulesFragment, "passa à regras")
                                                                             .addToBackStack(null)
                                                                             .commit();
                                                                 }
                                                             },

                                            tempo_encerramento);
                                }
                            });

                    //cria o componente de menu dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    //exibe o componente de menu dialog
                    alertDialog.show();
                }

            }
        });

        //chama o botão de configuração
        bt_configuracoes = (FloatingActionButton)view.findViewById(R.id.bt_configuracao);

        //evento de clique que inicia a classe de configuração
        bt_configuracoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chama a classe de sconfiguração
                entra_configuracao = new Intent(getContext(), SettingsActivity.class);
                //inicia a classe de configuração
                startActivity(entra_configuracao);
            }
        });


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
            TextView titleAbout = (TextView)view.findViewById(R.id.titleAbout);
            Typeface typeface = ResourcesCompat.getFont(contexto, R.font.koreanfont);
            titleAbout.setTypeface(typeface);
            titleAbout.setTextSize(43);

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
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
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