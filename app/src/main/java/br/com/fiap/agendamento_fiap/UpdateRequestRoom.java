package br.com.fiap.agendamento_fiap;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import br.com.fiap.agendamento_fiap.db.LoginDb;
import br.com.fiap.agendamento_fiap.model.Sala;

public class UpdateRequestRoom extends AppCompatActivity {

    LoginDb db;
    Sala sala;
    Button btnExcluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_request_room);
        btnExcluir = findViewById(R.id.btnExcluir);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        db = new LoginDb(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            sala = (Sala) bundle.get("sala");


            btnExcluir.setVisibility(View.VISIBLE);
        }
    }

    public void deleteRequestRoom(View view) {
        db.deleteRequestRoom(sala.getId());
        Toast.makeText(this, "Solicitação removida com sucesso", Toast.LENGTH_SHORT).show();
        Intent it = new Intent(this, MenuUser.class);
        startActivity(it);
    }
}
