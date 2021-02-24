package br.com.aadeveloper.capturaimagem;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog load;
    private TextView textoSrc;
    private ImageView imageView;
    Bitmap imagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoSrc    = (TextView)findViewById(R.id.textoSrc);
        imageView   = (ImageView)findViewById(R.id.imagem);

        //Executando a AsyncTask, fazando com que o processamento seja feito em backgraound, deixando a Activity livre
        new BuscaSRC().execute();
    }


    private class BuscaSRC extends AsyncTask<Void, Void, String> {
        protected String doInBackground(Void... params) {
            //Neste método, não faça nenhuma interação com a Activity atual
            String src = "";
            try{

                URL endereco =  new URL("https://mobile.twitter.com/uol");

                InputStream is = endereco.openStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String linha = br.readLine();

                while (!linha.contains(".jpg")) {
                    linha = br.readLine();
                }

                src = linha.substring(linha.indexOf("src=")+5,linha.indexOf(".jpg")+4);

                //Agora captura o endereço apenas da imagem
                endereco = new URL(src);
                //Com o endereço encontrado, converte a stream em imagem
                imagem = BitmapFactory.decodeStream(endereco.openStream());

            }catch (Exception ex){
                ex.printStackTrace();
            }

            return src;
        }

        protected void onPostExecute(String result) {
            //Metodo executado após o processamento, atribuindo o link ao TextView e a imagem ao ImageView
            textoSrc.setText(result);
            imageView.setImageBitmap(imagem);

        }
    }
}
