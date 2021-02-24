package br.com.aadeveloper.bancosoftblue;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.aadeveloper.bancosoftblue.banco.DBHelper;

public class InserirActivity extends AppCompatActivity {

    private EditText edtNome, edtTelefone;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir);

        edtNome = (EditText)findViewById(R.id.editTextNome);
        edtTelefone = (EditText)findViewById(R.id.editTextTelefone);

        DBHelper dbHelper = new DBHelper(this, "contatos", 1);
        db = dbHelper.getWritableDatabase();

    }

    public void gravar(View view) {

        ContentValues values = new ContentValues();
        values.put("nome", edtNome.getText().toString());
        values.put("telefone", Integer.parseInt(edtTelefone.getText().toString()));

            db.insert("contato", null, values);

        Toast.makeText(this, "Inserido com sucesso...", Toast.LENGTH_SHORT).show();

    }
}
