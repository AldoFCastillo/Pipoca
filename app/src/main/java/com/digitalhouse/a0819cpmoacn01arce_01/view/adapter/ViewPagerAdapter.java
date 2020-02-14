package com.digitalhouse.a0819cpmoacn01arce_01.view.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.CarteleraFragment;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.ExtrasFragment;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.HomeFragment;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.LoginFragment;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.NoticiasFragment;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.PeliculasFragment;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.SerieFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragmentList;

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        fragmentList = new ArrayList<>();


        fragmentList.add(new PeliculasFragment());
        fragmentList.add(new NoticiasFragment());
        fragmentList.add(new CarteleraFragment());
        fragmentList.add(new ExtrasFragment());
        fragmentList.add(new SerieFragment());




    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return this.fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return this.fragmentList.size();
    }


}


