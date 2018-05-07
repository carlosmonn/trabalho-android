package br.edu.unidavi.trabalhoandroid.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.edu.unidavi.trabalhoandroid.R;

class JogadorViewHolder extends RecyclerView.ViewHolder {
    ImageView imgJogador;
    TextView labNome;
    TextView labPosicao;

    public JogadorViewHolder(View itemView) {
        super(itemView);

        imgJogador = itemView.findViewById(R.id.imgJogador);
        labNome = itemView.findViewById(R.id.labNome);
        labPosicao = itemView.findViewById(R.id.labPosicao);
    }
}
