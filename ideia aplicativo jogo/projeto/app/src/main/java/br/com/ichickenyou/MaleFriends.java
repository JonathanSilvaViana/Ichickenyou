package br.com.ichickenyou;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MaleFriends extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_male_friends);
    }

    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
    }
}
