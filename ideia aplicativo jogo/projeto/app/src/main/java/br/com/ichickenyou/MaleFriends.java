package br.com.ichickenyou;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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


                for (int i=0; i<1; i++)
                {
                    Button btn = new Button(MaleFriends.this);
                    btn.setId(i+1);
                    btn.setText("Button"+(i+1));
                    //btn.setLayoutParams(lprams);
                    final int index = i;
                    //btn.setOnClickListener(chama);
                    dynamicview.addView(btn);
                }


            }
        });

    }

//    @Override
//    public void onClick(View view){
//        switch(view.getId()){
//            case 1:
//                //first button click
//                break;
//            //Second button click
//            case 2:
//                break;
//            case 3:
//                //third button click
//                break;
//            case 4:
//                //fourth button click
//                break;
//            default:
//                break;
//        }
//    }

    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
    }
}
