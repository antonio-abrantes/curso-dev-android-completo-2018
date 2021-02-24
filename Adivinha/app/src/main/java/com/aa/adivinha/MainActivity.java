package com.aa.adivinha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button btnJogar;
    private TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnJogar =      (Button) findViewById(R.id.btnJogar);
        txtResultado =  (TextView)findViewById(R.id.txtResultato);

        btnJogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random randomico = new Random();
                int numero = randomico.nextInt(11);

                txtResultado.setText(String.format(getResources().getString(R.string.teste_resultato), numero));
            }
        });
    }
}
