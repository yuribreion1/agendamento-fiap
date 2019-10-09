package br.com.fiap.agendamento_fiap;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.fiap.agendamento_fiap.adapter.MaterialAdapter;
import br.com.fiap.agendamento_fiap.db.LoginDb;
import br.com.fiap.agendamento_fiap.model.Material;

public class ListRequestMaterial extends AppCompatActivity {

    RecyclerView rvMaterials;
    List<Material> materials;
    LoginDb db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_material);
        db = new LoginDb(this);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        rvMaterials = findViewById(R.id.rvMaterials);
        rvMaterials.setHasFixedSize(true);
        rvMaterials.setLayoutManager(layoutManager);

        materials = db.listRequestedMaterials();

        MaterialAdapter adapter = new MaterialAdapter(this, materials);
        rvMaterials.setAdapter(adapter);
    }
}
