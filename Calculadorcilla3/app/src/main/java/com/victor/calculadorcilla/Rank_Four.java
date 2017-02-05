package com.victor.calculadorcilla;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Rank_Four extends Fragment {


    ArrayList<Player> players =new ArrayList<>();
    View rootview;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayout;
    peopleDB peopledb;

    public Rank_Four() {
        // Required empty public constructor
    }

    public void getRanking() {
        players=peopledb.getRanking(4);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootview=inflater.inflate(R.layout.fragment_rank__four, container, false);
        // Inflate the layout for this fragment
        peopledb = new peopleDB(getActivity());
        getRanking();

        //findViewById del layout activity_main
        mRecyclerView = (RecyclerView) rootview.findViewById(R.id.mRecyclerView);

        //LinearLayoutManager necesita el contexto de la Activity.
        //El LayoutManager se encarga de posicionar los items dentro del recyclerview
        //Y de definir la politica de reciclaje de los items no visibles.
        mLinearLayout = new LinearLayoutManager(getActivity());

        //Asignamos el LinearLayoutManager al recycler:
        mRecyclerView.setLayoutManager(mLinearLayout);


        //El adapter se encarga de  adaptar un objeto definido en el c�digo a una vista en xml
        //seg�n la estructura definida.
        //Asignamos nuestro custom Adapter
        mRecyclerView.setAdapter(new MyCustomAdapter(players));
        return rootview;
    }

}
