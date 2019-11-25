package com.morajavier.examen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    Configuration c;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c = getResources().getConfiguration();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(c.orientation == Configuration.ORIENTATION_PORTRAIT){
            HorizontalFragment horizontalFragment = new HorizontalFragment();
            fragmentTransaction.replace(android.R.id.content, horizontalFragment);
        }else {
            BDLog baseUsuarios = new BDLog(this);
            SQLiteDatabase baseDatos = baseUsuarios.getWritableDatabase();
            if (baseDatos != null) {
                //String query = "select nombre, pass from usuarios where nombre=?";
                //baseDatos.rawQuery(query, new String[]{"javier"});
                //para el where
                String[] cuando = new String[]{"javier"};
                Cursor c = baseDatos.query("usuarios", new String[]{"nombre", "pass"}, "nombre=?", cuando, null, null, null);

                if (c != null) {
                    if(c.moveToFirst()){
                        String user = c.getString(0);
                        //String user = c.getString(c.getColumnIndex("nombre"));
                        if(user.equals("javier")){
                            VerticalFragment verticalFragment = new VerticalFragment();
                            fragmentTransaction.replace(android.R.id.content, verticalFragment);
                        }else{
                            i = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(i);
                        }
                    }else{
                        i = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(i);
                    }
                }else{
                    i = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(i);
                }
                //Cerramos el cursor y la conexion con la base de datos
                c.close();
                baseDatos.close();
            }
        }
        fragmentTransaction.commit();
        //fragmentTransaction.commit();
    }
}

