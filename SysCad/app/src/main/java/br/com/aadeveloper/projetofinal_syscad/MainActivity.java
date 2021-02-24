package br.com.aadeveloper.projetofinal_syscad;
/*********************************************************
 * Instituto Federal de São Paulo - Campus Sertãozinho
 * Disciplina......: M4DADM-201802
 * Programação de Computadores e Dispositivos Móveis
 * Aluno...........: Antonio Gonçalves de Abrantes Neto
 *********************************************************/

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/*
*  Activity principal da aplicação
*/
public class MainActivity extends AppCompatActivity {

    private Button btnCadastro;
    private Button btnListagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCadastro = (Button)findViewById(R.id.btnCadastro);
        btnListagem = (Button)findViewById(R.id.btnListagem);

        //Evento do botão que inicia a Activity de cadastro
        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityCadastro.class);
                startActivity(intent);
            }
        });

        //Evento que inicia uma Activity onde são listados as pessoas já cadastradas
        btnListagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityListagem.class);
                startActivity(intent);
            }
        });
    }
}
