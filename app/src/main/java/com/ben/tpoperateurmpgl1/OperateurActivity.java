package com.ben.tpoperateurmpgl1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OperateurActivity extends AppCompatActivity {

    TextView txtLogin;
    EditText edtNumTel,edtCodeRecharge;
    Button btnSolde,btnRecharge;
    TextView txtSolde,txtTypeLigne,txtRecharge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operateur);
        btnSolde = findViewById(R.id.btnSold);
        btnRecharge = findViewById(R.id.btnRecharge);
        txtSolde = findViewById(R.id.txtSolde);
        edtNumTel = findViewById(R.id.edtNumTel);
        txtTypeLigne = findViewById(R.id.txtTypeLigne);
        txtRecharge = findViewById(R.id.txtRecharge);
        edtCodeRecharge = findViewById(R.id.edtCodeRecharge);


        btnSolde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),txtSolde.getText().toString(),Toast.LENGTH_LONG).show();
                   Intent iSolde = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"+ txtSolde.getText().toString()+"#"));
                   startActivity(iSolde);
            }
        });

        //passage de paramètres depuis l'interface de login
        txtLogin = findViewById(R.id.txtLogin);
        Bundle extras = getIntent().getExtras();
        txtLogin.setText(extras.getString("Lg"));
        btnRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iTelephone = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"+ txtRecharge.getText().toString()+"#"));
                startActivity(iTelephone);
            }
        });



        //Génération code de recharge
        edtCodeRecharge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String sTel = edtNumTel.getText().toString();
                String sCodeRecharge = charSequence.toString();
                String sRes ="";
                if (sTel.charAt(0) == '9'){ //TT
                    sRes = "*123*" + sCodeRecharge + "#";


                }
                else  if (sTel.charAt(0) == '3'){ // orange
                    sRes = "*100*" + sCodeRecharge + "#";


                }
                else  if (sTel.charAt(0) == '2'){//ooredoo
                    sRes = "*101*" + sCodeRecharge + "#";

                }
                txtRecharge.setText(sRes);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //détection du opérateur téléphonique
        edtNumTel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            //97852147
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String sTel = charSequence.toString();
                if (sTel.length()>0){
                    if (sTel.charAt(0) == '9'){
                        txtTypeLigne.setText("Votre ligne est Tunisie Télécom");
                        txtTypeLigne.setTextColor(Color.parseColor("#18a7ea"));
                        txtSolde.setText("*122#");
                        txtRecharge.setBackgroundColor(Color.parseColor("#18a7ea"));
                        txtRecharge.setTextColor(Color.parseColor("#ffffff"));
                        txtSolde.setTextColor(Color.parseColor("#ffffff"));
                        txtSolde.setBackgroundColor(Color.parseColor("#18a7ea"));
                        edtCodeRecharge.setFilters(new InputFilter[]{new InputFilter.LengthFilter(13)});                    }
                    else  if (sTel.charAt(0) == '3'){// orange
                        txtTypeLigne.setText("Votre ligne est Orange");
                        txtTypeLigne.setTextColor(Color.parseColor("#f16e00"));
                        txtSolde.setText("*111#");
                        txtRecharge.setBackgroundColor(Color.parseColor("#f16e00"));
                        txtRecharge.setTextColor(Color.parseColor("#ffffff"));
                        txtSolde.setTextColor(Color.parseColor("#ffffff"));
                        txtSolde.setBackgroundColor(Color.parseColor("#f16e00"));
                        edtCodeRecharge.setFilters(new InputFilter[]{new InputFilter.LengthFilter(14)});
                    }
                    else  if (sTel.charAt(0) == '2'){//ooredoo
                        txtTypeLigne.setText("Votre ligne est ooredoo");
                        txtTypeLigne.setTextColor(Color.parseColor("#ed1c24"));
                        txtSolde.setText("*100#");
                        txtRecharge.setBackgroundColor(Color.parseColor("#ed1c24"));
                        txtRecharge.setTextColor(Color.parseColor("#ffffff"));
                        txtSolde.setBackgroundColor(Color.parseColor("#ed1c24"));
                        txtSolde.setTextColor(Color.parseColor("#ffffff"));
                        edtCodeRecharge.setFilters(new InputFilter[]{new InputFilter.LengthFilter(14)});
                    }
                    edtCodeRecharge.setText("");


                }
             //   Toast.makeText(getApplicationContext(),sTel,Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });




    }
}