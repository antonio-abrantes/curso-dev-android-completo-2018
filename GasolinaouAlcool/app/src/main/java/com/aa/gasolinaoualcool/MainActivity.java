package com.aa.gasolinaoualcool;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtAlcool, edtGasolina;
    private Button btnVerificarMelhorCombustivel;
    private TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtAlcool = (EditText)findViewById(R.id.edtAlcool);
        edtGasolina = (EditText)findViewById(R.id.edtGasolina);
        btnVerificarMelhorCombustivel = (Button)findViewById(R.id.btnVerificarMelhorCombustivel);
        txtResultado = (TextView)findViewById(R.id.txtResultado);

        btnVerificarMelhorCombustivel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strAlcool = edtAlcool.getText().toString();
                String strGasolina = edtGasolina.getText().toString();

                if(strAlcool.isEmpty() || strGasolina.isEmpty()){
                    Toast.makeText(MainActivity.this, "Campo em branco!", Toast.LENGTH_SHORT).show();
                }else{
                    Double alcool = Double.parseDouble(strAlcool);
                    Double gasolina = Double.parseDouble(strGasolina);

                    double resultado = alcool / gasolina;

                    if(resultado >= 0.7){
                        txtResultado.setText("É melhor utilizar a gasolina");
                    }else{
                        txtResultado.setText("É melhor utilizar o alcool");
                    }
                }

                ((InputMethodManager) getApplicationContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(btnVerificarMelhorCombustivel.getWindowToken(), 0);
            }
        });
    }
}
