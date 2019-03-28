package br.com.ichickenyou;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;


public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnFragmentInteractionListener {

    Fragment fragment;

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Set the home as default
        Fragment fragment = new HomeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        else {
            super.onBackPressed();
            return;
        }

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();



        if (id == R.id.nav_home) {
            // Handle the home action

            fragment = new HomeFragment();

        } else if (id == R.id.nav_consultar) {

            fragment = new CrudFragment();

        } else if (id == R.id.nav_regras) {

            fragment = new RulesFragment();

        } else if (id == R.id.nav_sobre) {

            fragment = new AboutFragment();

        } else if (id == R.id.nav_sair) {

            //recebe o fragment padrão da navigation drawer
            fragment = new HomeFragment();

            //cria um menu que questiona o usuário se deseja encerrar a aplicação
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle(R.string.enunciado);
            alertDialogBuilder
                    .setMessage(R.string.querencerrar)
                    .setCancelable(false)
                    .setPositiveButton(R.string.fechar,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    moveTaskToBack(true);
                                    //mata o processo
                                    android.os.Process.killProcess(android.os.Process.myPid());
                                    //minimiza o aplicativo similar a uma função de sair
                                    System.exit(1);
                                }
                            })

                    .setNegativeButton(R.string.naofechar, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            //cancela a opção de encerramento do dialog
                            dialog.cancel();
                            //passa um feedback enquanto executar o teste
                            Log.d("Não encerrou", "a navigation drawer não recebeu o encerramento");
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        } else {

            fragment = new HomeFragment();

        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content, fragment)
                .commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
