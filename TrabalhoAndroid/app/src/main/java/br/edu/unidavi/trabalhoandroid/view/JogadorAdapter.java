package br.edu.unidavi.trabalhoandroid.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.edu.unidavi.trabalhoandroid.R;
import br.edu.unidavi.trabalhoandroid.model.Jogador;

public class JogadorAdapter extends RecyclerView.Adapter<JogadorViewHolder> {
    List<Jogador> jogadorList;
    Context context;

    public JogadorAdapter(List<Jogador> jogadorList, Context context){
        this.jogadorList = jogadorList;
        this.context = context;
    }

    @Override
    public JogadorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_jogador,
                        parent,
                        false);

        JogadorViewHolder myViewHolder = new JogadorViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(JogadorViewHolder holder, int position) {
        final Jogador myJogador = jogadorList.get(position);

        holder.labNome.setText(myJogador.getNome());
        holder.labPosicao.setText(myJogador.getPosicao());

        String imagem = "@drawable/"+myJogador.getFoto();
        int imageResource = this.context.getResources().getIdentifier(imagem, null, this.context.getPackageName());

        Drawable res = ContextCompat.getDrawable(this.context.getApplicationContext(), imageResource);
        holder.imgJogador.setImageDrawable(res);
    }

    @Override
    public int getItemCount() {
        return jogadorList.size();
    }

    public List<Jogador> getJogadorList() {
        return jogadorList;
    }

    public void setJogadorList(List<Jogador> jogadorList) {
        this.jogadorList = jogadorList;
    }
}
