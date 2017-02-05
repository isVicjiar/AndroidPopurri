package com.victor.calculadorcilla;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class Game extends Fragment implements View.OnClickListener {

    RelativeLayout memory;
    RelativeLayout memory_ranking;
    View rootview;

    public Game() {
        // Required empty public constructor
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rank:

                Intent i=new Intent(getActivity(), Ranking.class);
                startActivity(i);
                break;
            case R.id.mem:

                Intent e=new Intent(getActivity(), Memory_mejor.class);
                startActivity(e);
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview=inflater.inflate(R.layout.fragment_game, container, false);
        memory=((RelativeLayout) rootview.findViewById(R.id.mem));
        memory_ranking=((RelativeLayout) rootview.findViewById(R.id.rank));
        memory.setOnClickListener(this);
        memory_ranking.setOnClickListener(this);
        return rootview;
    }

}
