package br.com.fiap.agendamento_fiap.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.agendamento_fiap.model.Sala;

public class SalaDb extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "agendamento-fiap";
    private static final int DATABASE_VERSION = 2;

    public SalaDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE salas(" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "professor TEXT NOT NULL," +
                "sala TEXT NOT NULL," +
                "data TEXT NOT NULL," +
                "periodo TEXT NOT NULL," +
                "tipo TEXT NOT NULL," +
                "qnt_alunos TEXT NOT NULL" +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertRoomRequest(Sala sala) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("professor", sala.getProfessor());
        cv.put("sala", sala.getSala());
        cv.put("data", sala.getData());
        cv.put("periodo", sala.getPeriodo());
        cv.put("tipo", sala.getTipo());
        cv.put("qnt_alunos", sala.getQuantidade());

        db.insert("salas", null, cv);
    }

    public List<Sala> listRequestedRooms() {
        List<Sala> salas = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                "salas",
                new String[]{"id","professor","sala","data","periodo","tipo","qnt_alunos"},
                null,
                null,
                null,
                null,
                null
        );
        if (cursor.moveToFirst()) {
            do {
                Sala sala = new Sala();
                sala.setId(cursor.getInt(0));
                sala.setProfessor(cursor.getString(1));
                sala.setSala(cursor.getString(2));
                sala.setData(cursor.getString(3));
                sala.setTipo(cursor.getString(4));
                sala.setQuantidade(cursor.getString(5));

                salas.add(sala);
            } while (cursor.moveToNext());
        }
        return salas;
    }
}
