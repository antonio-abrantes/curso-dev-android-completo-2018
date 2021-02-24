package br.com.aadeveloper.projetofinal_syscad.DAO;
/*********************************************************
 * Instituto Federal de São Paulo - Campus Sertãozinho
 * Disciplina......: M4DADM-201802
 * Programação de Computadores e Dispositivos Móveis
 * Aluno...........: Antonio Gonçalves de Abrantes Neto
 *********************************************************/

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.ArrayList;
/*
*  Classe responsavel pelas operações de inserção, recuperação e
*  atualização dos dados da tabela no banco de dados.
*/
import br.com.aadeveloper.projetofinal_syscad.MODEL.PessoaFisica;

public class PessoaFisicaDBOperations {

    public static final String NOME_TABELA = "tb_pessoa_fisica";
    protected SQLiteDatabase database;

    public PessoaFisicaDBOperations(DBHelper dbHelper) {
        database = dbHelper.getWritableDatabase();
    }

    // Método para gravação de um registro no banco de dados
    public void gravarPessoaFisica(PessoaFisica pessoaFisica) {
        ContentValues values = new ContentValues();

        values.put("nome", pessoaFisica.getNome());
        values.put("cpf", pessoaFisica.getCpf());
        values.put("idade", pessoaFisica.getIdade());
        values.put("telefone", pessoaFisica.getTelefone());
        values.put("email", pessoaFisica.getEmail());

        database.insert(NOME_TABELA, null, values);
    }

    // Método que retorna todos os registros do banco de dados
    public ArrayList<PessoaFisica> listaPessoasFisicas() {

        Cursor cursor = database.rawQuery("SELECT * FROM "+NOME_TABELA, null);
        int indexId = cursor.getColumnIndex("id");
        int indexNome = cursor.getColumnIndex("nome");
        int indexCpf  = cursor.getColumnIndex("cpf");
        int indexIdade = cursor.getColumnIndex("idade");
        int indexTelefone = cursor.getColumnIndex("telefone");
        int indexEmail = cursor.getColumnIndex("email");

        ArrayList<PessoaFisica> pessoaFisicas = new ArrayList<>();

        try {

            if(cursor.moveToFirst())

                do {
                    PessoaFisica p = new PessoaFisica();
                    p.setId(cursor.getInt(indexId));
                    p.setNome(cursor.getString(indexNome));
                    p.setCpf(cursor.getString(indexCpf));
                    p.setIdade(cursor.getInt(indexIdade));
                    p.setTelefone(cursor.getString(indexTelefone));
                    p.setEmail(cursor.getString(indexEmail));

                    pessoaFisicas.add(p);
                    Log.i("PESSOA", "Entrou!!! "+p.getNome());
                }while (cursor.moveToNext());

        }catch (Exception ex){
            Log.i("PESSOA", "Erro!!!");
        }

        return pessoaFisicas;
    }

    // Método que retorna um registro individual de acordo com o ID informado
    public PessoaFisica getPessoaFisica(int id){

        Cursor cursor = database.rawQuery("SELECT * FROM "+NOME_TABELA+" WHERE id = "+id+";", null);
        int indexId = cursor.getColumnIndex("id");
        int indexNome = cursor.getColumnIndex("nome");
        int indexCpf  = cursor.getColumnIndex("cpf");
        int indexIdade = cursor.getColumnIndex("idade");
        int indexTelefone = cursor.getColumnIndex("telefone");
        int indexEmail = cursor.getColumnIndex("email");

        PessoaFisica pessoaFisica = new PessoaFisica();

        if(cursor.moveToFirst()){
            pessoaFisica.setId(cursor.getInt(indexId));
            pessoaFisica.setNome(cursor.getString(indexNome));
            pessoaFisica.setCpf(cursor.getString(indexCpf));
            pessoaFisica.setIdade(cursor.getInt(indexIdade));
            pessoaFisica.setTelefone(cursor.getString(indexTelefone));
            pessoaFisica.setEmail(cursor.getString(indexEmail));
        }
        return pessoaFisica;
    }

    // Método que atualiza um registro no banco de dados
    public void atualizarPessoaFisica(PessoaFisica pessoaFisica) {

        ContentValues values = new ContentValues();

        values.put("nome", pessoaFisica.getNome());
        values.put("cpf", pessoaFisica.getCpf());
        values.put("idade", pessoaFisica.getIdade());
        values.put("telefone", pessoaFisica.getTelefone());
        values.put("email", pessoaFisica.getEmail());

        database.update(NOME_TABELA, values, "id = ?", new String[]{String.valueOf(pessoaFisica.getId())});
    }

    // Método responsavel por deletar um regristro do banco de dados
    public void deletaPessoaFisica(Integer id) {
        database.execSQL("DELETE FROM "+NOME_TABELA+" WHERE id ="+id+";");
    }

}
