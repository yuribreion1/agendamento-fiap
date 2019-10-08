package br.com.fiap.agendamento_fiap;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.fiap.agendamento_fiap.db.LoginDb;
import br.com.fiap.agendamento_fiap.model.User;

class ListUsers extends AppCompatActivity {

    RecyclerView rvUsers;
    List<User> users;
    LoginDb db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);

        RecyclerView.LayoutManager layoutManager
                = new LinearLayoutManager(this);

        rvUsers = findViewById(R.id.rvUser);
        rvUsers.setHasFixedSize(true);
        rvUsers.setLayoutManager(layoutManager);

        users = db.listUsers();

        UserAdapter adapter = new UserAdapter(this, users);
        rvUsers.setAdapter(adapter);

    }
}
