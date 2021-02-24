package br.com.aadeveloper.sharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText textNome;
    private Button buttonSalvar;
    private TextView textoResposta;

    private static final String ARQUIVO_PREFRENCIA = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textNome = (EditText)findViewById(R.id.editTextNome);
        buttonSalvar = (Button) findViewById(R.id.buttonSalvar);
        textoResposta = (TextView) findViewById(R.id.textViewResposta);

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFRENCIA, 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                if(textNome.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Preencha o nome!", Toast.LENGTH_SHORT).show();
                }else {
                    editor.putString("nome", textNome.getText().toString());
                    editor.commit();
                }
            }
        });

        //Recuperar os dados
        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFRENCIA, 0);
        if(sharedPreferences.contains("nome")){
            String nome = sharedPreferences.getString("nome", "usuário não definido");
            textoResposta.setText(nome);
        }else{
            textoResposta.setText("Ola, usuário não definido");
        }


    }
}
