package br.com.fiap.agendamento_fiap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.fiap.agendamento_fiap.db.LoginDb;
import br.com.fiap.agendamento_fiap.model.User;

public class MainActivity extends AppCompatActivity {

    EditText fieldSerial;
    EditText fieldPassword;
    User user;
    LoginDb loginDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        fieldSerial     =   findViewById(R.id.serial);
        fieldPassword   =   findViewById(R.id.password);
        loginDb = new LoginDb(this);
    }

    public void register(View view) {
        Intent it = new Intent(this, RegisterUser.class);
        startActivity(it);
    }

    public void login(View view) {

        String username = fieldSerial.getText().toString();
        String password = fieldPassword.getText().toString();
        user = loginDb.checkUser(username, password);

        Toast.makeText(this, user.getUsername(), Toast.LENGTH_SHORT).show();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Informe os campos corretamente", Toast.LENGTH_SHORT).show();
            return;
        }

        if (username.equals("")) {
            if (password.equals("")) {
                Toast.makeText(this, "Usuário e senha em branco", Toast.LENGTH_SHORT).show();
                return;
            } else {
                Toast.makeText(this, "Usuário em branco", Toast.LENGTH_SHORT).show();
                return;
            }
        }


        if (username.equals(user.getUsername()) && password.equals(user.getPassword()) ) {
            Toast.makeText(this, "Usuário autenticado", Toast.LENGTH_SHORT).show();
            Intent it = new Intent(this, MenuUser.class);
            startActivity(it);
        } else {
            Toast.makeText(this, "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show();
            return;
        }

    }
}
