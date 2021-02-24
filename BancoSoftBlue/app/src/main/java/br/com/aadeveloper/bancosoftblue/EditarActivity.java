package br.com.aadeveloper.bancosoftblue;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.aadeveloper.bancosoftblue.banco.DBHelper;

public class EditarActivity extends AppCompatActivity {

    private EditText edtNome, edtTelefone;
    private Button btnProcurar, btnEditar;
    private SQLiteDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        edtNome = (EditText)findViewById(R.id.edtBuscaNome);
        edtTelefone = (EditText)findViewById(R.id.edtBsucaTelefone);

        btnProcurar = (Button)findViewById(R.id.buttonBuscar);
        btnEditar = (Button)findViewById(R.id.buttonEditar);

        DBHelper dbHelper = new DBHelper(this, "contatos", 1);
        db = dbHelper.getWritableDatabase();

    }

    public void procurar(View view) {
        Cursor cursor = db.query("contato", new String[]{ "telefone" }, "nome = ?", new String[]{ edtNome.getText().toString() }, null, null, null );
        if(cursor.moveToNext()){

            int telefone = cursor.getInt(0);
            edtTelefone.setText(Integer.toString(telefone));

        }else{
            Toast.makeText(this, "Dados não localizados...", Toast.LENGTH_SHORT).show();
        }
    }

    public void editar(View view) {
        ContentValues values = new ContentValues();
        values.put("telefone", Integer.parseInt(edtTelefone.getText().toString()));

        db.update("contato", values, "nome = ?", new String[]{edtNome.getText().toString()});
        Toast.makeText(this, "Atualizado com sucesso...", Toast.LENGTH_SHORT).show();
    }
}
