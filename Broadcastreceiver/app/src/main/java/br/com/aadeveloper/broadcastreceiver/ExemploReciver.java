package br.com.aadeveloper.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Antonio on 22/06/2018.
 */

public class ExemploReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String acao = intent.getAction();
        if(acao.equals("BROADCAST_DINAMICO")){
            Log.i("RECEIVER", "Broadcast - Dinamico");
        }else{
            Log.i("RECEIVER", "Broadcast - Estático");
        }

        if(acao.equals("android.intent.action.BOOT_COMPLETED")){
            Intent i = new Intent(context, MainActivity.class);
            i.addCategory(Intent.CATEGORY_DEFAULT);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }
}
