package br.com.aadeveloper.broadcastreceiver;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button enviar_estatico;
    private Button enviar_dinamico;
    private Button service;
    private Button bindService;
    private TextView textViewStatus, textViewHoras;
    ExemploReciver exemploReciver;
    ExemploBindService exemploBindService;
    private boolean statusBind = false;
    LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewStatus = (TextView)findViewById(R.id.textViewStatus);
        textViewHoras = (TextView)findViewById(R.id.textViewHoras);

        exemploReciver = new ExemploReciver();
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.registerReceiver(exemploReciver, new IntentFilter("BROADCAST_DINAMICO"));

        enviar_estatico = (Button)findViewById(R.id.enviar_estatico);
        enviar_estatico.setOnClickListener(this);

        enviar_dinamico = (Button)findViewById(R.id.enviar_dinamico);
        enviar_dinamico.setOnClickListener(this);

        service = (Button)findViewById(R.id.service);
        service.setOnClickListener(this);

        bindService = (Button)findViewById(R.id.iniciarbindService);
        bindService.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.enviar_estatico:
                sendBroadcast(new Intent("BROADCAST_ESTATICO"));
                ConfirmaEnvio();
                break;
            case R.id.enviar_dinamico:
                localBroadcastManager.sendBroadcast(new Intent("BROADCAST_DINAMICO"));
                ConfirmaEnvio();
                break;
            case R.id.service:
                Intent intent = new Intent(this, ExemploService.class);
                if(isRunningService(intent)){
                    stopService(intent);
                    textViewStatus.setText("Serviço parado");
                }else{
                    startService(intent);
                    textViewStatus.setText("Serviço iniciado");
                }
                break;
            case R.id.iniciarbindService:
                if(statusBind){
                    textViewHoras.setText(exemploBindService.getHoras());
                }
                break;
        }
    }

    private boolean isRunningService(Intent intent){
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo serviceInfo: manager.getRunningServices(Integer.MAX_VALUE)){
            if(serviceInfo.service.getClassName().equals("br.com.aadeveloper.broadcastreceiver.ExemploService")){
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intentBindService = new Intent(this, ExemploBindService.class);
        bindService(intentBindService, serviceConnection, BIND_AUTO_CREATE);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ExemploBindService.LocalBinder localBinder = (ExemploBindService.LocalBinder) service;
            exemploBindService = localBinder.getService();
            statusBind = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            statusBind = false;
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        if(statusBind){
            unbindService(serviceConnection);
            statusBind = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(exemploReciver);
    }

    private void ConfirmaEnvio(){
        Toast.makeText(this, "Intent enviada", Toast.LENGTH_SHORT).show();
    }
}
