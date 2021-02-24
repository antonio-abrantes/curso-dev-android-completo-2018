package com.aa.signos;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView listViewSignos;
    private String[] signos = {
            "Aquario","Peixes","Áries","Touro","Gêmeos","Câncer",
            "Leão","Virgem","Libra","Escorpião","Sagitário","Capricórnio"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewSignos = (ListView)findViewById(R.id.lvSignos);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                signos
        );

        listViewSignos.setAdapter(adapter);

        listViewSignos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                //Toast.makeText(getApplicationContext(), "Signo: "+ signos[position].toString(), Toast.LENGTH_SHORT).show();

                //Dialog
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

                //Parametros
                dialog.setTitle("Atenção");
                dialog.setNeutralButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Ok! Saindo!", Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Signo: "+ signos[position].toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setIcon(android.R.drawable.ic_media_play);
                dialog.setCancelable(false);
                dialog.create();
                dialog.show();

            }
        });
    }
}
