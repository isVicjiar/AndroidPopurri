package com.victor.calculadorcilla;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class Game extends Fragment implements View.OnClickListener {

    RelativeLayout memory4;
    RelativeLayout memory6;
    RelativeLayout memory_ranking;
    View rootview;
    SharedPreferences settings;

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
            case R.id.mem_f:
                SharedPreferences.Editor editor=settings.edit();
                editor.putInt("GameMode",4);
                editor.apply();
                Intent e=new Intent(getActivity(), Memory.class);
                startActivity(e);
                break;
            case R.id.mem_s:
                SharedPreferences.Editor edit=settings.edit();
                edit.putInt("GameMode",6);
                edit.apply();
                Intent in=new Intent(getActivity(), Memory.class);
                startActivity(in);
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview=inflater.inflate(R.layout.fragment_game, container, false);
        memory4=((RelativeLayout) rootview.findViewById(R.id.mem_f));
        memory6=((RelativeLayout) rootview.findViewById(R.id.mem_s));
        memory_ranking=((RelativeLayout) rootview.findViewById(R.id.rank));
        memory4.setOnClickListener(this);
        memory6.setOnClickListener(this);
        memory_ranking.setOnClickListener(this);
        settings=getActivity().getSharedPreferences("MYAPP", Context.MODE_PRIVATE);
        return rootview;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        SharedPreferences.Editor editor=settings.edit();
        editor.putString("curr_fragment","Game");
        editor.apply();
    }

}
