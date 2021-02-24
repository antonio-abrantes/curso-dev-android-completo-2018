package br.com.aadeveloper.broadcastreceiver;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Date;

/**
 * Created by Antonio on 22/06/2018.
 */

public class ExemploBindService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        IBinder iBinder = new LocalBinder();
        return iBinder;
    }

    public class LocalBinder extends Binder{
        ExemploBindService getService(){
            return ExemploBindService.this;
        }
    }

    public String getHoras(){
        Date data = new Date();
        return data.toString();
    }
}
