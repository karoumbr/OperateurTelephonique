package com.ben.tpoperateurmpgl1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtLogin, edtPwd;
    Button btnConnexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtLogin = findViewById(R.id.edtLogin);
        edtPwd = findViewById(R.id.edtPwd);
        btnConnexion = findViewById(R.id.btnConnexion);
        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sLogin = edtLogin.getText().toString();
                String sPwd = edtPwd.getText().toString();
                if (sPwd.equals("123456")){
                    Intent i = new Intent(getApplicationContext(),OperateurActivity.class);
                    i.putExtra("Lg",sLogin);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(),"Mot de passe incorrect",Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}