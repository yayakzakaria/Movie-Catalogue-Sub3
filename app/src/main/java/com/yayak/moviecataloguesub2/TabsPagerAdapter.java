package com.yayak.moviecataloguesub2;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.yayak.moviecataloguesub2.fragment.ShowListFragment;
import com.yayak.moviecataloguesub2.viewmodel.FilmViewModel;

public class TabsPagerAdapter extends FragmentPagerAdapter {
    private final Context context;
    private FilmViewModel filmViewModel;

    TabsPagerAdapter(Context context, FragmentManager fm, FilmViewModel filmViewModel) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context = context;
        this.filmViewModel = filmViewModel;
    }

    @StringRes
    private final int[] TAB_TITLES = new int[]{
            R.string.tab_movie, R.string.tab_tvshow
    };

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = ShowListFragment.newInstance(position);
        filmViewModel.setListFilm(position);
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
