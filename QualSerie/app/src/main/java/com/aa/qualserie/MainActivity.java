package com.aa.qualserie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBarNivel;
    private ImageView imagemNivel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBarNivel = (SeekBar) findViewById(R.id.seekBarNivel);
        imagemNivel = (ImageView) findViewById(R.id.imagemNivel);

        seekBarNivel.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch(progress){
                    case 1:
                        imagemNivel.setImageResource(R.drawable.pouco);
                        break;
                    case 2:
                        imagemNivel.setImageResource(R.drawable.medio);
                        break;
                    case 3:
                        imagemNivel.setImageResource(R.drawable.muito);
                        break;
                    case 4:
                        imagemNivel.setImageResource(R.drawable.susto);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "Nenhum nivel selecionado", Toast.LENGTH_SHORT).show();
                        imagemNivel.setImageDrawable(null);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
