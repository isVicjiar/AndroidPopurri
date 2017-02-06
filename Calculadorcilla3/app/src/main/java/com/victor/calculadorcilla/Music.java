package com.victor.calculadorcilla;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
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
    MediaPlayer mediaPlayer = new MediaPlayer();
    ImageView play;
    ImageView stop;
    ImageView skip;
    SharedPreferences settings;

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
        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.trololo_song);
        return rootview;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play:
                if (playing) {
                    mediaPlayer.pause();
                    playing = false;
                    flipper.flipImage(playbutton,((ImageView)rootview.findViewById(R.id.play)));
                } else {
                    mediaPlayer.start();
                    playing = true;
                    flipper.flipImage(pausebutton,((ImageView)rootview.findViewById(R.id.play)));
                    if (!started) {
                        ((TextView) rootview.findViewById(R.id.song_name)).setText("Trololo song");
                        started=true;
                    }
                }
                break;
            case R.id.stop:
                if (playing && started) {
                    mediaPlayer.stop();
                    started = false;
                    playing=false;
                    flipper.flipImage(playbutton,((ImageView)rootview.findViewById(R.id.play)));
                    ((TextView) rootview.findViewById(R.id.song_name)).setText("");
                }
                break;
            case R.id.skip:
                if (playing && started) {
                    mediaPlayer.stop();
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

