package br.com.aadeveloper.projetofinal_syscad.MODEL;
/*********************************************************
 * Instituto Federal de São Paulo - Campus Sertãozinho
 * Disciplina......: M4DADM-201802
 * Programação de Computadores e Dispositivos Móveis
 * Aluno...........: Antonio Gonçalves de Abrantes Neto
 *********************************************************/

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.aadeveloper.projetofinal_syscad.ActivityCadastro;
import br.com.aadeveloper.projetofinal_syscad.R;
/*
*  Classe responsavel por gerar, popular e manipular os dados de cada
*  item da lista na ActivityListagem.
*/
public class PessoaAdapter extends RecyclerView.Adapter<PessoaAdapter.PessoaViewHolder>{

    private List<PessoaFisica> pessoas;

    //Método construtor que recebe os dados para carregar na listagem
    public PessoaAdapter(List<PessoaFisica> pessoas){
        this.pessoas = pessoas;
    }

    //Inner class statica responsavel por carregar os componentes do layout
    public static class PessoaViewHolder extends RecyclerView.ViewHolder {
        TextView nomeView;
        TextView idadeView;
        TextView indexView;
        TextView idView;
        ImageView editView;

        PessoaViewHolder(View itemView) {
            super(itemView);
            nomeView = (TextView)itemView.findViewById(R.id.person_name);
            idadeView = (TextView)itemView.findViewById(R.id.person_tel);
            indexView = (TextView)itemView.findViewById(R.id.person_index);
            idView = (TextView)itemView.findViewById(R.id.person_id);
            editView = (ImageView)itemView.findViewById(R.id.person_edit);
        }
    }

    // Sobrescrita do metodo da classe que herda de RecyclerView.Adapter responsavel por carregar o layout da lista
    @Override
    public PessoaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        PessoaViewHolder pvh = new PessoaViewHolder(v);
        return pvh;
    }

    // Sobrescrita do metodo da classe que herda de RecyclerView.Adapter responsavel por carregar os dados em cada item da lista
    @Override
    public void onBindViewHolder(final PessoaViewHolder viewHolder, int i) {
        viewHolder.nomeView.setText(pessoas.get(i).getNome());
        viewHolder.idadeView.setText(pessoas.get(i).getTelefone());
        viewHolder.indexView.setText(String.valueOf(i));
        viewHolder.idView.setText(String.valueOf(pessoas.get(i).getId()));

        // Método que gera um evento de click para cada item da lista
        viewHolder.editView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                // Envio do parametro ID do item selecionado na lista para ActivityCadastro em modo de edição
                bundle.putString("person_id", viewHolder.idView.getText().toString());
                Intent intent = new Intent(view.getContext(), ActivityCadastro.class);
                intent.putExtras(bundle);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pessoas.size();
    }

    //Método para atualizar a lista após alterações
    public void atualizaLista(ArrayList<PessoaFisica> lista){
        this.pessoas = lista;
        notifyDataSetChanged();
    }
}
