package br.com.aadeveloper.infocovid_19;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private String url = "https://infocovid19app.herokuapp.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadWebApp(MainActivity.this);
    }

    private void loadWebApp(Context context) {
        if (VerificaConexao.isConnected(MainActivity.this)){
            openWebView();
        }else {
            openActivityNoConnection();
        }
    }

    private void openWebView() {

        if(webView != null){
            webView = null;
        }

        getSupportActionBar().hide();

        getSupportActionBar().setDisplayShowHomeEnabled(false);
//        getSupportActionBar().setIcon(R.mipmap.ic_actionbar);

        View decorView = getWindow().getDecorView();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }

        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.myHomePage);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(false);
        webView.loadUrl(url);
    }

    private void openActivityNoConnection() {
        startActivity(new Intent(MainActivity.this, NoConnectionActivity.class));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (VerificaConexao.isConnected(getApplicationContext())){
            loadWebApp(getApplicationContext());
        }else {
            VerificaConexao.msgConexao(getApplicationContext());
            finishAffinity();
        }
    }
}