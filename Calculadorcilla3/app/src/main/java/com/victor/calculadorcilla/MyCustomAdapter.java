package com.victor.calculadorcilla;

import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.AdapterViewHolder>{

    ArrayList<Player> players;

    MyCustomAdapter(ArrayList <Player> players){
        this.players = players;
    }


    @Override
    public MyCustomAdapter.AdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.rowlayout, viewGroup, false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyCustomAdapter.AdapterViewHolder adapterViewholder, int position) {
        String PhotoLayout = players.get(position).getPhoto();
        if (PhotoLayout==null) {
            adapterViewholder.Photo.setImageDrawable(adapterViewholder.v.getResources().
                    getDrawable(R.drawable.ic_angry));
        } else {
            //adapterViewholder.Photo.setImageDrawable(players.get(position).getPhoto());
            //TEMPORAL
            Uri mUri = Uri.parse(PhotoLayout);
            //adapterViewholder.Photo.setImageBitmap(MediaStore.Images.Media.
            // getBitmap(getContentResolver(), mUri));
        }
        adapterViewholder.Photo.setImageDrawable(adapterViewholder.v.getResources().
                getDrawable(R.drawable.ic_angry));
        adapterViewholder.User.setText(players.get(position).getUser());
        adapterViewholder.Score.setText(String.valueOf(players.get(position).getScore()));

    }

    @Override
    public int getItemCount() {
        return players.size();
    }



    public class AdapterViewHolder extends RecyclerView.ViewHolder {


        public ImageView Photo;
        public TextView User;
        public TextView Score;
        public View v;
        public AdapterViewHolder(View itemView) {
            super(itemView);
            this.v = itemView;
            this.Photo = (ImageView) itemView.findViewById(R.id.Photo);
            this.User = (TextView) itemView.findViewById(R.id.User);
            this.Score = (TextView) itemView.findViewById(R.id.Score);
        }
    }
}
