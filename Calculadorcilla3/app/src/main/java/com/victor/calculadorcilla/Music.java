package com.victor.calculadorcilla;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.material.joanbarroso.flipper.CoolImageFlipper;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */
public class Music extends Fragment implements View.OnClickListener {

    View rootview;
    ImageView play;
    ImageView stop;
    ImageView skip;
    SharedPreferences settings;
    private Intent music;
    private MyService myService;
    private Boolean musicBound=false;

    Boolean playing = false;
    Boolean started = false;

    CoolImageFlipper flipper;
    Drawable playbutton;
    Drawable pausebutton;

    public Music() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_music, container, false);
        settings=getActivity().getSharedPreferences("MYAPP", Context.MODE_PRIVATE);
        play = ((ImageView) rootview.findViewById(R.id.play));
        stop = ((ImageView) rootview.findViewById(R.id.stop));
        skip = ((ImageView) rootview.findViewById(R.id.skip));
        play.setOnClickListener(this);
        stop.setOnClickListener(this);
        skip.setOnClickListener(this);
        flipper= new CoolImageFlipper(getActivity());
        playbutton=getResources().getDrawable(R.drawable.ic_play_button);
        pausebutton=getResources().getDrawable(R.drawable.ic_pause);
        return rootview;
    }

    private ServiceConnection musicConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MusicBinder binder=(MyService.MusicBinder)service;
            myService=binder.getService();
            musicBound=true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicBound=false;
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play:
                if (playing) {
                    getActivity().stopService(music);
                    playing = false;
                    flipper.flipImage(playbutton,((ImageView)rootview.findViewById(R.id.play)));
                } else {
                    if (music==null) {
                        music=new Intent(getActivity(),MyService.class);
                        myService.bindService(music,musicConnection,Context.BIND_AUTO_CREATE);
                    }
                }
                break;
            case R.id.stop:
                if (playing && started) {
                    getActivity().stopService(music);
                    started = false;
                    playing=false;
                    flipper.flipImage(playbutton,((ImageView)rootview.findViewById(R.id.play)));
                    ((TextView) rootview.findViewById(R.id.song_name)).setText("");
                }
                break;
            case R.id.skip:
                if (playing && started) {
                    getActivity().stopService(music);
                    started = false;
                    playing=false;
                    flipper.flipImage(playbutton,((ImageView)rootview.findViewById(R.id.play)));
                    ((TextView) rootview.findViewById(R.id.song_name)).setText("");
                }
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        SharedPreferences.Editor editor=settings.edit();
        editor.putString("curr_fragment","Music");
        editor.apply();
    }

}

