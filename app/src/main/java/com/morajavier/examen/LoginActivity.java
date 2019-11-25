package com.morajavier.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText usuario, password;
    Button btnLogin;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usuario = findViewById(R.id.txtVNombre);
        password = findViewById(R.id.txtVPass);
        btnLogin = findViewById(R.id.buttonLogin);
        BDLog baseUsuarios = new BDLog(this);
        final SQLiteDatabase baseDatos = baseUsuarios.getWritableDatabase();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkData(usuario) && checkData(password)){
                    if(baseDatos != null){
                        ContentValues cv = new ContentValues();
                        cv.put("nombre", usuario.getText().toString());
                        cv.put("pass", password.getText().toString());
                        baseDatos.insert("usuarios", null, cv);
                        i = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(LoginActivity.this, "Conexion fallida", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(LoginActivity.this, "Llene los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean checkData(TextView campo){
        if(campo != null && campo.getText().toString() != ""){
            return true;
        }
        return false;
    }
}
