package br.edu.unidavi.trabalhoandroid.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.edu.unidavi.trabalhoandroid.R;

class ClubeViewHolder extends RecyclerView.ViewHolder {
    ImageView imgClube;
    TextView labNome;
    TextView labAbreviacao;

    public ClubeViewHolder(View itemView) {
        super(itemView);

        imgClube = itemView.findViewById(R.id.imgClube);
        labNome = itemView.findViewById(R.id.labNome);
        labAbreviacao = itemView.findViewById(R.id.labAbreviacao);
    }
}
