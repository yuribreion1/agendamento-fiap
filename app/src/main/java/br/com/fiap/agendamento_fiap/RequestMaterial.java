package br.com.fiap.agendamento_fiap;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Calendar;

import br.com.fiap.agendamento_fiap.db.LoginDb;
import br.com.fiap.agendamento_fiap.model.Material;

public class RequestMaterial extends AppCompatActivity {

    Spinner spinnerProfessor;
    Spinner spinnerMaterial;
    Spinner spinnerSala;
    Spinner spinnerPeriodo;
    Material requestMaterial;
    LoginDb db;

    Calendar myCalendar = Calendar.getInstance();
    int year = myCalendar.get(Calendar.YEAR);
    int month = myCalendar.get(Calendar.MONTH);
    int day = myCalendar.get(Calendar.DAY_OF_MONTH);
    TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_material);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        db = new LoginDb(this);

        spinnerProfessor = findViewById(R.id.professor_spinner);
        spinnerMaterial = findViewById(R.id.material_spinner);
        spinnerSala = findViewById(R.id.sala_spinner);
        spinnerPeriodo = findViewById(R.id.periodo_spinner);

        date = findViewById(R.id.date_label);

        ArrayAdapter<CharSequence> adapterProfessor = ArrayAdapter.createFromResource(this, R.array.professor_array, android.R.layout.simple_spinner_item);
        adapterProfessor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProfessor.setAdapter(adapterProfessor);

        ArrayAdapter<CharSequence> adapterMaterial = ArrayAdapter.createFromResource(this, R.array.materiais_array, android.R.layout.simple_spinner_item);
        adapterMaterial.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMaterial.setAdapter(adapterMaterial);

        ArrayAdapter<CharSequence> adapterSala = ArrayAdapter.createFromResource(this, R.array.salas_array, android.R.layout.simple_spinner_item);
        adapterSala.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSala.setAdapter(adapterSala);

        ArrayAdapter<CharSequence> adapterPeriodo = ArrayAdapter.createFromResource(this, R.array.periodos_array, android.R.layout.simple_spinner_item);
        adapterPeriodo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPeriodo.setAdapter(adapterPeriodo);

    }

    public void mostraDatas(View view) {
        DatePickerDialog picker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String dateSelected = DateFormat.getDateInstance(DateFormat.SHORT).format(myCalendar.getTime());
                date.setText(dateSelected);
            }
        }, year, month, day);
        picker.show();
    }


    public void requestMaterial(View view) {
        String professor = spinnerProfessor.getSelectedItem().toString();
        String material = spinnerMaterial.getSelectedItem().toString();
        String sala = spinnerSala.getSelectedItem().toString();
        String data = date.getText().toString();
        String periodo = spinnerPeriodo.getSelectedItem().toString();

        if (data.contains("Data a ser selecionada")) {
            Toast.makeText(this, "Favor preencher os campos necessários", Toast.LENGTH_SHORT).show();
            return;
        } else {
            try {
                requestMaterial = new Material();
                requestMaterial.setProfessor(professor);
                requestMaterial.setMaterial(material);
                requestMaterial.setSala(sala);
                requestMaterial.setData(data);
                requestMaterial.setPeriodo(periodo);
                db.insertMaterialRequest(requestMaterial);
                Toast.makeText(this, "Solicitação feita com sucesso", Toast.LENGTH_LONG).show();
                Intent it = new Intent(this, MenuUser.class);
                startActivity(it);
            } catch (SQLiteException e) {
                Toast.makeText(this, "Houve um erro para inserir", Toast.LENGTH_SHORT).show();
                e.getMessage();
            }
        }

    }
}
