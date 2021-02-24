package br.com.aadeveloper.broadcastreceiver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Date;

/**
 * Created by Antonio on 22/06/2018.
 */

public class ExemploService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("SERVICE", "Serviço iniciado...");
        horas();
        return super.onStartCommand(intent, flags, startId);
    }

    public void horas(){
        for (int i = 0; i < 5; i++){
            try {
                Thread.sleep(1000);
                Date data = new Date();
                Log.i("SERVICE", "Hora: "+data.toString());
            } catch (InterruptedException e) {
                Log.i("SERVICE", e.getMessage());
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("SERVICE", "Serviço encerrado!");
    }
}
