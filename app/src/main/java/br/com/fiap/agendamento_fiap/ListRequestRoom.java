package br.com.fiap.agendamento_fiap;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.fiap.agendamento_fiap.adapter.RoomAdapter;
import br.com.fiap.agendamento_fiap.db.LoginDb;
import br.com.fiap.agendamento_fiap.model.Sala;

public class ListRequestRoom extends AppCompatActivity {

    RecyclerView rvRooms;
    List<Sala> salas;
    LoginDb db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_room);
        db = new LoginDb(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        rvRooms = findViewById(R.id.rvRooms);
        rvRooms.setHasFixedSize(true);
        rvRooms.setLayoutManager(layoutManager);

        salas = db.listRequestedRooms();

        RoomAdapter adapter = new RoomAdapter(this, salas);
        rvRooms.setAdapter(adapter);
    }
}
