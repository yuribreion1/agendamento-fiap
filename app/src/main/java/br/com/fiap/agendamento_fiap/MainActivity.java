package br.com.fiap.agendamento_fiap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
    }

    public void register(View view) {
        Intent it = new Intent(this, RegisterUser.class);
        startActivity(it);
    }

    public void login(View view) {
        Intent it = new Intent(this, MenuUser.class);
        startActivity(it);
    }
}
