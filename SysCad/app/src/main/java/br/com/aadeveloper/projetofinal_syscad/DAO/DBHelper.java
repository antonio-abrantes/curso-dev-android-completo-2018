package br.com.aadeveloper.projetofinal_syscad.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/*********************************************************
 * Instituto Federal de São Paulo - Campus Sertãozinho
 * Disciplina......: M4DADM-201802
 * Programação de Computadores e Dispositivos Móveis
 * Aluno...........: Antonio Gonçalves de Abrantes Neto
 *********************************************************/

/*
* Classe responsavel pela criacão e atualização da estrutura do banco de dados
* servindo de apoio para a classe PessoaFisicaDBOperations que cuida da manipulação
* dos dados.
*/
public class DBHelper extends SQLiteOpenHelper {

    private Context context;

    //Atributo com string da estrutura da tabela de gravação da aplicação
    private static final String CREATE_SQL =
            "CREATE TABLE tb_pessoa_fisica (" +
                    "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "+
                    "nome TEXT NOT NULL, " +
                    "cpf TEXT," +
                    "idade INTEGER," +
                    "telefone TEXT NOT NULL," +
                    "email TEXT);";

    //String com comando para apagar a tabela inteira
    private static final String DELETE_SQL = "DROP TABLE tb_pessoa_fisica;";

    public DBHelper(Context context, String name, int version) {
        super(context, name, null, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_SQL);
        db.execSQL(CREATE_SQL);
    }

    public Context getContext() {
        return context;
    }
}
