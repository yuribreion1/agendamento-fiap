package br.com.fiap.agendamento_fiap.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.agendamento_fiap.model.User;

public class LoginDb extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "LoginDb";
    private static final int DATABASE_VERSION = 1;

    public LoginDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE user(" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "username TEXT NOT NULL," +
                "password TEXT NOT NULL" +
                ")";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

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
        String[] whereArgs = new String[] {"username","password"};
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
}
