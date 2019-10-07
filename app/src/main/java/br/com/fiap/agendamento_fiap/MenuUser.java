package br.com.fiap.agendamento_fiap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MenuUser extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();
    }

    public void requestRoom(View view) {
        Intent it = new Intent(this, RequestRoom.class);
        startActivity(it);
    }

    public void requestMaterial(View view) {
        Intent it = new Intent(this, RequestMaterial.class);
        startActivity(it);
    }
}
