package br.com.fiap.agendamento_fiap;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Calendar;

import br.com.fiap.agendamento_fiap.db.LoginDb;
import br.com.fiap.agendamento_fiap.model.Sala;

public class RequestRoom extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinnerProfessor;
    Spinner spinnerSala;
    Spinner spinnerPeriodo;
    Spinner spinnerTipo;
    EditText countStudents;
    Sala requestSala;
    LoginDb db;

    Calendar myCalendar = Calendar.getInstance();
    int year = myCalendar.get(Calendar.YEAR);
    int month = myCalendar.get(Calendar.MONTH);
    int day = myCalendar.get(Calendar.DAY_OF_MONTH);
    TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_room);
        
        db = new LoginDb(this);

        countStudents = findViewById(R.id.count);
        spinnerProfessor = findViewById(R.id.professor_spinner);
        spinnerSala = findViewById(R.id.sala_spinner);
        spinnerPeriodo = findViewById(R.id.periodo_spinner);
        spinnerTipo = findViewById(R.id.tipo_spinner);

        date = findViewById(R.id.date_label);

        ArrayAdapter<CharSequence> adapterProfessor = ArrayAdapter.createFromResource(this, R.array.professor_array, android.R.layout.simple_spinner_item);
        adapterProfessor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProfessor.setAdapter(adapterProfessor);

        ArrayAdapter<CharSequence> adapterSala = ArrayAdapter.createFromResource(this, R.array.salas_array, android.R.layout.simple_spinner_item);
        adapterSala.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSala.setAdapter(adapterSala);

        ArrayAdapter<CharSequence> adapterPeriodo = ArrayAdapter.createFromResource(this, R.array.periodos_array, android.R.layout.simple_spinner_item);
        adapterPeriodo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPeriodo.setAdapter(adapterPeriodo);

        ArrayAdapter<CharSequence> adapterTipo = ArrayAdapter.createFromResource(this, R.array.parcCli_array, android.R.layout.simple_spinner_item);
        adapterTipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipo.setAdapter(adapterTipo);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
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
                String dateSelected = DateFormat.getDateInstance(DateFormat.SHORT).format(myCalendar.getTime());
                date.setText(dateSelected);
            }
        }, year, month,day);
        picker.show();
    }

    public void requestRoom(View view) {
        String professor = spinnerProfessor.getSelectedItem().toString();
        String sala = spinnerSala.getSelectedItem().toString();
        String data = date.getText().toString();
        String periodo = spinnerPeriodo.getSelectedItem().toString();
        String tipo = spinnerTipo.getSelectedItem().toString();
        String qntAlunos = countStudents.getText().toString();

        if (qntAlunos.equals("") || data.contains("Data a ser selecionada")) {
            Toast.makeText(this, "Favor preencher os campos necessários", Toast.LENGTH_SHORT).show();
            return;
        } else {
            try {
                requestSala = new Sala();
                requestSala.setProfessor(professor);
                requestSala.setSala(sala);
                requestSala.setData(data);
                requestSala.setPeriodo(periodo);
                requestSala.setTipo(tipo);
                requestSala.setQuantidade(qntAlunos);
                db.insertRoomRequest(requestSala);
                Toast.makeText(this, "Solicitação feita com sucesso", Toast.LENGTH_SHORT).show();
                Intent it = new Intent(this, MenuUser.class);
                startActivity(it);
            } catch (SQLiteException e) {
                Toast.makeText(this, "Houve um erro para inserir", Toast.LENGTH_SHORT).show();
                e.getMessage();
            }
        }
    }
}
