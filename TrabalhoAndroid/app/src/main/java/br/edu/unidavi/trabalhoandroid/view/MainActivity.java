package br.edu.unidavi.trabalhoandroid.view;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import br.edu.unidavi.trabalhoandroid.R;
import br.edu.unidavi.trabalhoandroid.controller.ClubeController;
import br.edu.unidavi.trabalhoandroid.model.Clube;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewClube;
    private ClubeAdapter adapterClube;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ClubeController clubeController = new ClubeController(getBaseContext());
        List<Clube> listaClubes = clubeController.listar();

        if (listaClubes.size() > 0) {
            findViewById(R.id.recycler_clubes).setVisibility(View.VISIBLE);
            findViewById(R.id.container_empty).setVisibility(View.GONE);
        }

        recyclerViewClube = findViewById(R.id.recycler_clubes);
        recyclerViewClube.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewClube.setHasFixedSize(true);

        adapterClube = new ClubeAdapter(new ArrayList<Clube>(),this);
        adapterClube.setClubeList(listaClubes);
        adapterClube.notifyDataSetChanged();
        recyclerViewClube.setAdapter(adapterClube);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(List<Clube> clubeList){

        if(clubeList.size() == 0) {
            findViewById(R.id.container_empty).setVisibility(View.VISIBLE);
            findViewById(R.id.recycler_clubes).setVisibility(View.GONE);
        }else{
            findViewById(R.id.recycler_clubes).setVisibility(View.VISIBLE);
            findViewById(R.id.container_empty).setVisibility(View.GONE);
            adapterClube.setClubeList(clubeList);
            adapterClube.notifyDataSetChanged();
        }

    }

    @Subscribe
    public void onEvent(Error error){
        Snackbar.make(findViewById(R.id.container), error.getMessage(), Snackbar.LENGTH_LONG).show();
    }
}
