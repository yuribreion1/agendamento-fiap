package br.com.fiap.agendamento_fiap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.com.fiap.agendamento_fiap.db.LoginDb;
import br.com.fiap.agendamento_fiap.model.Material;

public class UpdateRequestMaterial extends AppCompatActivity {

    LoginDb db;
    Material material;
    Button btnExcluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_request_material);
        btnExcluir = findViewById(R.id.btnExcluir);

        db = new LoginDb(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            material = (Material) bundle.get("material");
            btnExcluir.setVisibility(View.VISIBLE);
        }
    }

    public void deleteRequestMaterial(View view) {
        db.deleteRequestMaterial(material.getId());
        Toast.makeText(this, "Solicitação removida com sucesso", Toast.LENGTH_SHORT).show();
        Intent it = new Intent(this, MenuUser.class);
        startActivity(it);
    }
}
