package br.com.fiap.agendamento_fiap;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Calendar;

public class RequestRoom extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinnerProfessor;
    Spinner spinnerSala;
    Spinner spinnerPeriodo;
    Calendar myCalendar = Calendar.getInstance();
    int year = myCalendar.get(Calendar.YEAR);
    int month = myCalendar.get(Calendar.MONTH);
    int day = myCalendar.get(Calendar.DAY_OF_MONTH);
    TextView date;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_room);
        getSupportActionBar().hide();

        spinnerProfessor = findViewById(R.id.professor_spinner);
        spinnerSala = findViewById(R.id.sala_spinner);
        spinnerPeriodo = findViewById(R.id.periodo_spinner);
        date = findViewById(R.id.date_label);

        ArrayAdapter<CharSequence> adapterProfessor = ArrayAdapter.createFromResource(this, R.array.professor_array, android.R.layout.simple_spinner_item);
        adapterProfessor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProfessor.setAdapter(adapterProfessor);

        ArrayAdapter<CharSequence> adapterSala = ArrayAdapter.createFromResource(this, R.array.salas_array, android.R.layout.simple_spinner_item);
        adapterSala.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSala.setAdapter(adapterSala);

        ArrayAdapter<CharSequence> adapterPeriodo = ArrayAdapter.createFromResource(this, R.array.salas_array, android.R.layout.simple_spinner_item);
        adapterSala.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPeriodo.setAdapter(adapterPeriodo);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selecao = parent.getItemAtPosition(position).toString();
        Toast.makeText(this,selecao, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void mostraDatas(View view) {
        DatePickerDialog picker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String dateSelected = DateFormat.getDateInstance(DateFormat.FULL).format(myCalendar.getTime());
                Toast.makeText(RequestRoom.this,"Data selecionada: " + dateSelected, Toast.LENGTH_SHORT).show();
                date.setText(dateSelected);
            }
        }, year, month,day);
        picker.show();
    }
}
