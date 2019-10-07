package br.com.fiap.agendamento_fiap;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterUser extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
    }

    public void registerUser(View view) {
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }
}
