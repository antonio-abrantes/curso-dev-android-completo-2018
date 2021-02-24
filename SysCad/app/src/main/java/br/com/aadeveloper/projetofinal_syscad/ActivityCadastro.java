package br.com.aadeveloper.projetofinal_syscad;
/*********************************************************
 * Instituto Federal de São Paulo - Campus Sertãozinho
 * Disciplina......: M4DADM-201802
 * Programação de Computadores e Dispositivos Móveis
 * Aluno...........: Antonio Gonçalves de Abrantes Neto
 *********************************************************/

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.aadeveloper.projetofinal_syscad.DAO.DBHelper;
import br.com.aadeveloper.projetofinal_syscad.DAO.PessoaFisicaDBOperations;
import br.com.aadeveloper.projetofinal_syscad.MODEL.PessoaFisica;
/*
* Activity responsavel tanto pelo cadastro como pela edição dos dados cadastrados,
* sendo chamada de dois pontos diferentes da aplicação, uma que vem a partir da
* MainActivity com os campos limpos e prontos para cadastro e outra a partir da
* ActivityListagem, por onde é enviado um parametro que preenche os campos com os
* dados do item selecionado, possibilitando a edição ou remoção do mesmo.
*/
public class ActivityCadastro extends AppCompatActivity {

    private PessoaFisica pessoaFisica;
    private Button btnSalvar;
    private Button btnDeletar;
    private Button btnVoltar;

    // Atributo flag para definir se a Activity esta gravando um novo registro ou editando um já existente
    private Boolean editando = false;

    private EditText edtNome;
    private EditText edtCpf;
    private EditText edtIdade;
    private EditText edtTelefone;
    private EditText edtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        btnVoltar = (Button)findViewById(R.id.btnVoltar);
        btnDeletar = (Button)findViewById(R.id.btnDeletar);
        btnSalvar = (Button)findViewById(R.id.btnSalvar);

        edtNome =   (EditText)findViewById(R.id.edtNome);
        edtCpf =    (EditText)findViewById(R.id.edtCpf);
        edtIdade =  (EditText)findViewById(R.id.edtIdade);
        edtTelefone = (EditText)findViewById(R.id.edtTelefone);
        edtEmail =  (EditText)findViewById(R.id.edtEmail);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        /*
        * Trecho que faz a validação para saber se a Activity foi aberta a partir da lista de itens,
        * mudando a flag editando para true, possibilitando a edição ou deleção do mesmo,
        * ou se foi a partir da tela inicial para um novo cadastro.
        */
        String id_person = getIntent().getStringExtra("person_id");
        // Recupera o id enviado pela ActivitiListagem e busca o contado no banco para carregar os dados
        if(id_person != null){
            DBHelper dbHelper = new DBHelper(ActivityCadastro.this, PessoaFisicaDBOperations.NOME_TABELA, 1);
            PessoaFisicaDBOperations dbOperations = new PessoaFisicaDBOperations(dbHelper);
            pessoaFisica = dbOperations.getPessoaFisica(Integer.parseInt(id_person));

            if(pessoaFisica != null){
                atualizaEditTexts();
            }
        }

        // Evento responsavel por cadastrar ou alterar um registro de acordo com o valor da variavel editando
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    DBHelper dbHelper = new DBHelper(ActivityCadastro.this, "tb_pessoa_fisica", 1);
                    PessoaFisicaDBOperations dbOperations = new PessoaFisicaDBOperations(dbHelper);
                    if(editando == false){
                        //public PessoaFisica(String nome, String cpf, Integer idade, String telefone, String email)
                        PessoaFisica novaPessoa = new PessoaFisica(
                                edtNome.getText().toString(),
                                edtCpf.getText().toString(),
                                Integer.parseInt(edtIdade.getText().toString()),
                                edtTelefone.getText().toString(),
                                edtEmail.getText().toString()
                        );
                        dbOperations.gravarPessoaFisica(novaPessoa);
                        limparEditTexts();
                        Toast.makeText(ActivityCadastro.this, "Gravado com sucesso...", Toast.LENGTH_SHORT).show();
                    }else{
                        atualizaPessoaFisica();
                        dbOperations.atualizarPessoaFisica(pessoaFisica);
                        Toast.makeText(ActivityCadastro.this, "Atualizadao com sucesso...", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception ex){
                    Toast.makeText(ActivityCadastro.this, "Dados invalidos!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Evento responsavel por deletar um registro
        btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(ActivityCadastro.this, "tb_pessoa_fisica", 1);
                PessoaFisicaDBOperations dbOperations = new PessoaFisicaDBOperations(dbHelper);
                if(pessoaFisica != null){
                    dbOperations.deletaPessoaFisica(pessoaFisica.getId());
                    Toast.makeText(ActivityCadastro.this, "Deletando Id "+pessoaFisica.getId(), Toast.LENGTH_SHORT).show();
                    limparEditTexts();
                }else{
                    Toast.makeText(ActivityCadastro.this, "Não existe registro a ser deletado!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Evento responsavel por encerrar a ActivityCadastro e retornar para MainActivity
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    // Método que atualiza o objeto PessoaFisica que esta sendo manipulado
    private void atualizaPessoaFisica(){
        pessoaFisica.setNome(edtNome.getText().toString());
        pessoaFisica.setCpf(edtCpf.getText().toString());
        pessoaFisica.setIdade(Integer.parseInt(edtIdade.getText().toString()));
        pessoaFisica.setTelefone(edtTelefone.getText().toString());
        pessoaFisica.setEmail(edtEmail.getText().toString());
    }

    // Método que atualiza os EditTexts que tenham seus valores modificados
    private void atualizaEditTexts(){
        edtNome.setText(pessoaFisica.getNome());
        edtCpf.setText(pessoaFisica.getCpf());
        edtIdade.setText(pessoaFisica.getIdade().toString());
        edtTelefone.setText(pessoaFisica.getTelefone());
        edtEmail.setText(pessoaFisica.getEmail());
        editando = true;
    }

    // Método responsavel por limpar os EditTexts após as operações pertinentes
    private void limparEditTexts(){
        edtNome.setText("");
        edtEmail.setText("");
        edtTelefone.setText("");
        edtIdade.setText("");
        edtCpf.setText("");
        edtNome.requestFocus();
    }
}
