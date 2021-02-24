package br.com.aadeveloper.infocovid_19;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NoConnectionActivity extends AppCompatActivity {

    private Button btnReconect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_connection);

        btnReconect = (Button) findViewById(R.id.btnReconect);

        btnReconect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(VerificaConexao.isConnected(getApplicationContext())){
                    onBackPressed();
                }else{
                    VerificaConexao.msgConexao(getApplicationContext());
                }
            }
        });
    }
}
