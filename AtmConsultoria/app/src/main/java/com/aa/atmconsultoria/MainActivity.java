package com.aa.atmconsultoria;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView empresa, servicos, clientes, contato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        empresa =   (ImageView) findViewById(R.id.empresaId);
        servicos =  (ImageView) findViewById(R.id.servicosId);
        clientes =  (ImageView) findViewById(R.id.clientesId);
        contato =   (ImageView) findViewById(R.id.contatoId);


        empresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), EmpresaActivity.class);
                startActivity(i);
            }
        });

        servicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ServicoActivity.class);
                startActivity(i);
            }
        });

        clientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ClienteActivity.class);
                startActivity(i);
            }
        });

        contato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ContatoActivity.class);
                startActivity(i);
            }
        });
    }
}
