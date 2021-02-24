package com.aa.idadedecachorro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtIdadeCachorro;
    private TextView txtResultadoIdade;
    private Button btnDescobrirIdade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtIdadeCachorro = (EditText)findViewById(R.id.edtIdadeCachorro);
        txtResultadoIdade = (TextView)findViewById(R.id.txtResultadoIdade);
        btnDescobrirIdade = (Button)findViewById(R.id.btnDescobrirIdade);

        btnDescobrirIdade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strIdade = edtIdadeCachorro.getText().toString();

                if(strIdade.isEmpty() || strIdade.length() == 0){
                    Toast.makeText( getApplicationContext() ,"Valor invalido!", Toast.LENGTH_SHORT).show();
                }else{
                    Integer idade = Integer.parseInt(strIdade);
                    idade = idade * 7;
                    txtResultadoIdade.setText(String.format(getResources().getString(R.string.texto_resultado), idade));
                }
            }
        });

    }
}
