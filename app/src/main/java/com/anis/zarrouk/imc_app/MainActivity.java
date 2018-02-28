package com.anis.zarrouk.imc_app;

import android.app.Activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private EditText poids = null;
    private EditText taille = null;
    private RadioButton rb1 = null;
    private RadioButton rb2 = null;
    private CheckBox mega = null;
    private Button imc = null;
    private Button raz = null;
    private TextView result = null;
    private RadioGroup group = null;
    float res ;
    float poidsval;
    float tailleval;
    private final String megaString ="Vous faites un poids parfait ! Wahou ! Trop fort ! On dirait Brad Pitt (si vous êtes un homme)/Angelina Jolie (si vous êtes une femme)/Willy (si vous êtes un orque) !";
    private final String defaut="Vous devez cliquer sur le bouton « Calculer l'IMC » pour obtenir un résultat.";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imc = (Button)findViewById(R.id.imc);
        raz =(Button)findViewById(R.id.raz);
        poids = (EditText)findViewById(R.id.editPoids);
        taille = (EditText)findViewById(R.id.editTaille);
        mega = (CheckBox)findViewById(R.id.check);
        result =(TextView)findViewById(R.id.text_result);
        group =(RadioGroup)findViewById(R.id.rg);
        imc.setOnClickListener(imcclicklistener);
        raz.setOnClickListener(razListener);
        poids.addTextChangedListener(watcherListener);
        taille.addTextChangedListener(watcherListener);
        mega.setOnClickListener(checledListenr);
    }
    private TextWatcher watcherListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
                 result.setText(defaut);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private View.OnClickListener imcclicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

           if(!mega.isChecked())
           {
              String t = taille.getText().toString();
              String p = poids.getText().toString();
              float tValue = Float.valueOf(t);
              if(tValue == 0)
              {
                  Toast.makeText(MainActivity.this, "Hého, tu es un Minipouce ou quoi ?", Toast.LENGTH_SHORT).show();
              }
              else
              {
                  float pValue= Float.valueOf(p);
                  if(group.getCheckedRadioButtonId()== R.id.rd2)
                      tValue = tValue/100;
                      tValue = (float)Math.pow(tValue,2);
                      float res = pValue/tValue;
                      result.setText("Votre IMC est "+String.valueOf(res));



              }

           }
           else
           {
                     result.setText(megaString);
           }
        }
    };
    private View.OnClickListener razListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            taille.getText().clear();
            poids.getText().clear();
            result.setText(defaut);
        }
    };

    private View.OnClickListener checledListenr = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!((CheckBox)v).isChecked() && result.getText().equals(megaString))
                result.setText(defaut);
        }
    };

}
