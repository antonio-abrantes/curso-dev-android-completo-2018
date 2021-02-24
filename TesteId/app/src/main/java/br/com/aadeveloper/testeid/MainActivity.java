package br.com.aadeveloper.testeid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn01, btn02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn01 = (Button)findViewById(R.id.btnId01);
        btn02 = (Button)findViewById(R.id.btnId02);

        btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), btn01.getText().toString()+" Id: "+btn01.getId(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), TesteIdActivity.class);
                startActivity(i);
            }
        });

    }
}
