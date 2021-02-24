package br.com.aadeveloper.projetofinal_syscad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import br.com.aadeveloper.projetofinal_syscad.DAO.DBHelper;
import br.com.aadeveloper.projetofinal_syscad.DAO.PessoaFisicaDBOperations;
import br.com.aadeveloper.projetofinal_syscad.MODEL.PessoaAdapter;
import br.com.aadeveloper.projetofinal_syscad.MODEL.PessoaFisica;
/*********************************************************
 * Instituto Federal de São Paulo - Campus Sertãozinho
 * Disciplina......: M4DADM-201802
 * Programação de Computadores e Dispositivos Móveis
 * Aluno...........: Antonio Gonçalves de Abrantes Neto
 *********************************************************/
/*
 *  Activity para exibição dos dados cadastrados em uma listagem
 *  Contendo um botão para exbibição dos detalhes de cada
 *  item listado proporcionando ainda a edição dos mesmos
 *  atraves do envio de um parametro entre as Activitys.
 */
public class ActivityListagem extends AppCompatActivity {

    DBHelper dbHelper;
    PessoaFisicaDBOperations dbOperations;
    ArrayList<PessoaFisica> pessoasList;
    PessoaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem);

        //Instanciação do fragmento para listagem dos dados
        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DBHelper(ActivityListagem.this, PessoaFisicaDBOperations.NOME_TABELA, 1);
        dbOperations = new PessoaFisicaDBOperations(dbHelper);

        //Lista que recupera todos os dados cadastrados no banco
        pessoasList = dbOperations.listaPessoasFisicas();

        //Atribui os dados recuperados ao fragmento de listagem
        adapter = new PessoaAdapter(pessoasList);
        rv.setAdapter(adapter);
    }

    //Metodo que atualiza os dados na listagem logo após fechamento da tela de edição
    @Override
    protected void onResume() {
        super.onResume();
        pessoasList = dbOperations.listaPessoasFisicas();
        adapter.atualizaLista(pessoasList);
    }
}
