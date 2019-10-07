package br.com.fiap.agendamento_fiap;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.com.fiap.agendamento_fiap.db.LoginDb;
import br.com.fiap.agendamento_fiap.model.User;

public class RegisterUser extends AppCompatActivity {

    EditText fieldUser;
    EditText fieldPassword;
    LoginDb loginDb;
    User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        getSupportActionBar().hide();

        fieldUser       =   findViewById(R.id.serial);
        fieldPassword   =   findViewById(R.id.password);
        loginDb = new LoginDb(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            user = (User) bundle.get("user");
            if (user != null) {
                fieldUser.setText(user.getUsername());
                fieldPassword.setText((user.getPassword()));
            }
        }

    }

    public void registerUser(View view) {

        String username = fieldUser.getText().toString();
        String password = fieldPassword.getText().toString();

        if (username.isEmpty() || password.isEmpty() ) {
            Toast.makeText(this, "Informe os campos corretamente", Toast.LENGTH_SHORT).show();
            return;
        }

        if (user == null) {
            user = new User();
            user.setUsername(username);
            user.setPassword(password);
            loginDb.insertUser(user);
            Toast.makeText(this, "Usuário cadastrado com sucesso", Toast.LENGTH_SHORT).show();
            Intent it = new Intent(this, MainActivity.class);
            startActivity(it);
        } else {
            Toast.makeText(this, "Usuário não foi cadastrado", Toast.LENGTH_SHORT).show();
        }

        finish();
    }
}
