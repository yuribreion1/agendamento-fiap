package br.com.fiap.agendamento_fiap.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.agendamento_fiap.model.Material;
import br.com.fiap.agendamento_fiap.model.Sala;
import br.com.fiap.agendamento_fiap.model.User;

public class LoginDb extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "agendamento-fiap";
    private static final int DATABASE_VERSION = 1;
    private static final String createUserTable = "CREATE TABLE user(" +
            "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "username TEXT NOT NULL," +
            "password TEXT NOT NULL" +
            ")";

    private static final String createRoomTable = "CREATE TABLE salas(" +
            "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "professor TEXT NOT NULL," +
            "sala TEXT NOT NULL," +
            "data TEXT NOT NULL," +
            "periodo TEXT NOT NULL," +
            "tipo TEXT NOT NULL," +
            "qnt_alunos TEXT NOT NULL" +
            ")";

    public static final String createMaterialTable = "CREATE TABLE materiais(" +
            "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "professor TEXT NOT NULL," +
            "material TEXT NOT NULL," +
            "sala TEXT NOT NULL," +
            "data TEXT NOT NULL," +
            "periodo TEXT NOT NULL)";


    private static final String dropUserTable = "DROP TABLE IF EXISTS user";
    private static final String dropRoomTable = "DROP TABLE IF EXISTS salas";
    private static final String dropMaterialTable = "DROP TABLE IF EXISTS materiais";

    public LoginDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


//    Criando as tabelas
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createUserTable);
        db.execSQL(createRoomTable);
        db.execSQL(createMaterialTable);
    }

//    Dropando as tabelas durante a instalação
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(dropUserTable);
        db.execSQL(dropRoomTable);
        db.execSQL(dropMaterialTable);
        onCreate(db);
    }

    /*
        Processos referentes a usuário

     */
    public void insertUser(User user) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("username", user.getUsername());
        cv.put("password", user.getPassword());

        db.insert("user",null, cv);
    }

    public User checkUser(String username, String password) {
        User user = new User();
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
        String[] whereArgs = new String[] {username, password};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, whereArgs);
        if (cursor.moveToFirst()) {
            user.setId(cursor.getInt(0));
            user.setUsername(cursor.getString(1));
            user.setPassword(cursor.getString(2));
        }
        cursor.close();
        return user;
    }

    public void update(User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", user.getUsername());
        cv.put("password", user.getPassword());
        db.update("user", cv, "id = ?", new String[]{String.valueOf(user.getId())});
    }

    public List<User> listUsers() {
        List<User> users = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(
                "user",
                new String[]{"id","username","password"},
                null,
                null,
                null,
                null,
                null
        );
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(0));
                user.setUsername(cursor.getString(1));
                user.setPassword(cursor.getString(2));
                users.add(user);
            } while (cursor.moveToNext());
        }
        return users;
    }

    /*
        Processos referentes a agendamento de sala

     */

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
                sala.setPeriodo(cursor.getString(4));
                sala.setTipo(cursor.getString(5));
                sala.setQuantidade(cursor.getString(6));

                salas.add(sala);
            } while (cursor.moveToNext());
        }
        return salas;
    }

    public void deleteRequestRoom(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("salas","id = ?", new String[]{String.valueOf(id)});
    }

    /*
        Processos referentes a agendamento de materais
     */

    public void insertMaterialRequest(Material material) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("professor", material.getProfessor());
        cv.put("material", material.getMaterial());
        cv.put("sala", material.getSala());
        cv.put("data", material.getData());
        cv.put("periodo", material.getPeriodo());

        db.insert("materiais", null, cv);
    }

    public List<Material> listRequestedMaterials() {
        List<Material> materials = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                "materiais",
                new String[]{"id","professor","material","sala","data","periodo"},
                null,
                null,
                null,
                null,
                null
        );
        if (cursor.moveToFirst()) {
            do {
                Material material = new Material();
                material.setId(cursor.getInt(0));
                material.setProfessor(cursor.getString(1));
                material.setMaterial(cursor.getString(2));
                material.setSala(cursor.getString(3));
                material.setData(cursor.getString(4));
                material.setPeriodo(cursor.getString(5));

                materials.add(material);
            } while (cursor.moveToNext());
        }
        return materials;
    }

    public void deleteRequestMaterial(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("materiais","id = ?", new String[]{String.valueOf(id)});
    }
}
