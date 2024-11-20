package com.marlenepaper.meh.ui.fr.manager;



import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.marlenepaper.meh.fr.aficiones.HolaCoffee;
import com.marlenepaper.meh.fr.aficiones.Sobrevivir;

public class Paginador extends FragmentPagerAdapter {

    private final Context mContext;

    public Paginador(Context context, FragmentManager fm){
        super(fm);
        mContext=context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new Sobrevivir();
            case 1:
                return new HolaCoffee();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}

