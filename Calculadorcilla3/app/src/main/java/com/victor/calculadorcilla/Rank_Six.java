package com.victor.calculadorcilla;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.media.AudioTrack;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

import static android.support.v7.recyclerview.R.styleable.RecyclerView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Rank_Six extends Fragment {


    ArrayList<Player> players =new ArrayList<>();
    View rootview;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayout;
    Realm realm;

    public Rank_Six() {
        // Required empty public constructor
    }

    public void getRanking() {
        RealmResults realmResults=realm.allObjects(User.class);
        realmResults.sort("best_score6");
        if (!realmResults.isEmpty()) {
            User u;
            for (int i=0; i<realmResults.size(); ++i) {
                u= (User) realmResults.get(i);
                String user=u.getName();
                String photo=u.getPhoto();
                int best_score=u.getBest_score6();
                if (best_score!=0) {
                    Player p=new Player(photo,user,best_score);
                    players.add(p);
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootview=inflater.inflate(R.layout.fragment_rank__four, container, false);
        setHasOptionsMenu(true);
        realm= Realm.getDefaultInstance();
        getRanking();

        mRecyclerView = (RecyclerView) rootview.findViewById(R.id.mRecyclerView);

        mLinearLayout = new LinearLayoutManager(getActivity());

        mRecyclerView.setLayoutManager(mLinearLayout);


        mRecyclerView.setAdapter(new MyCustomAdapter(players));
        return rootview;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_rank, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reset:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setTitle("Reset stats");
                builder.setMessage("Are you sure you want to reset stats? All progress will be lost");

                builder.setPositiveButton("Reset!",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                RealmResults realmResults = realm.allObjects(User.class);
                                if (!realmResults.isEmpty()) {
                                    User u;
                                    for (int i = 0; i < realmResults.size(); ++i) {
                                        User current=(User) realmResults.get(i);
                                        realm.beginTransaction();
                                        current.setBest_score6(0);
                                        realm.copyToRealmOrUpdate(current);
                                        realm.commitTransaction();
                                    }
                                }
                            }
                        });
                builder.setNegativeButton("Back",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();
                break;

        }
        return true;
    }
}
