package br.edu.unidavi.trabalhoandroid.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.edu.unidavi.trabalhoandroid.R;
import br.edu.unidavi.trabalhoandroid.model.Clube;

public class ClubeAdapter extends RecyclerView.Adapter<ClubeViewHolder> {
    List<Clube> clubeList;
    Context context;

    public ClubeAdapter(List<Clube> clubeList, Context context){
        this.clubeList = clubeList;
        this.context = context;
    }

    @Override
    public ClubeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_clube,
                        parent,
                        false);

        ClubeViewHolder myViewHolder = new ClubeViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final ClubeViewHolder holder, final int position) {
        final Clube myClube = clubeList.get(position);

        holder.labNome.setText(myClube.getNome());
        holder.labAbreviacao.setText(myClube.getAbreviacao());

        String imagem = "@drawable/"+myClube.getEscudo();
        int imageResource = this.context.getResources().getIdentifier(imagem, null, this.context.getPackageName());

        Drawable res = ContextCompat.getDrawable(this.context.getApplicationContext(), imageResource);
        holder.imgClube.setImageDrawable(res);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Clube clube = clubeList.get(position);

                Intent intent = new Intent(v.getContext(), JogadorActivity.class);
                intent.putExtra("clubeId", clube.getId());

                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return clubeList.size();
    }

    public List<Clube> getClubeList() {
        return clubeList;
    }

    public void setClubeList(List<Clube> clubeList) {
        this.clubeList = clubeList;
    }
}
