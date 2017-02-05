package com.victor.calculadorcilla;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by inlab on 31/01/2017.
 */

public class PagerAdapter extends FragmentPagerAdapter {


    private String tabTitles[] = new String[] { "Ranking 4x4", "Ranking 6x6" };
    private Fragment tab = null;

    //creadora
    PagerAdapter(FragmentManager fm) {
        super(fm);
    }


    //crea las tabas, siempre tiene que retornar con el numero de tabs que queremos mostrar
    @Override
    public int getCount() {
        return tabTitles.length;
    }

    //Lanza el fragment asociado con el número de tab
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                tab = new Rank_Four();
                break;
            case 1:
                tab = new Rank_Six();
                break;
        }
        return tab;
    }

    //pone el nombre en cada tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Genera le título en función de la posición
        return tabTitles[position];
    }
}