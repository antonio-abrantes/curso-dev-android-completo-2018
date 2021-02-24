package com.aa.frasedodia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button btnNovaFrase;
    private TextView txtNovaFrase;

    private String[] frases = {"Frase 1", "Frase 2", "Frase 3", "Frase 4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNovaFrase = (Button)findViewById(R.id.btnNovaFrase);
        txtNovaFrase = (TextView)findViewById(R.id.txtNovaFrase);

        btnNovaFrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int indice = random.nextInt(frases.length);

                txtNovaFrase.setText(frases[indice]);
            }
        });

    }
}
