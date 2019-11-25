package com.morajavier.examen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class BDLog extends SQLiteOpenHelper {
    private static final int VERSION_DB = 1;
    private static final String NAME_DB = "examen";
    private static final String TABLA_USUARIOS = "usuarios";
    private static final String COLUMN_ID = "_id";
    private static final String NOMBRE = "nombre";
    private static final String PASS = "pass";
    private static final String CREAR_TABLA = "CREATE TABLE "
                                                + TABLA_USUARIOS
                                                +"(" + COLUMN_ID
                                                + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                + NOMBRE + " TEXT, "
                                                + PASS + " TEXT);";
    public BDLog(Context context) {
        super(context, NAME_DB, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREAR_TABLA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
