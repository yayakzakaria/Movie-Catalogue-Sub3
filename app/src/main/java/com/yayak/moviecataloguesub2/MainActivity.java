package com.yayak.moviecataloguesub2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.yayak.moviecataloguesub2.adapter.ListFilmAdapter;
import com.yayak.moviecataloguesub2.fragment.ChangeLangFragment;
import com.yayak.moviecataloguesub2.model.Film;
import com.yayak.moviecataloguesub2.viewmodel.FilmViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListFilmAdapter listFilmAdapter;
    private FilmViewModel filmViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        filmViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(FilmViewModel.class);
        filmViewModel.getListFilms().observe(this, new Observer<ArrayList<Film>>() {
            @Override
            public void onChanged(ArrayList<Film> films) {
                if(films != null) {
                    listFilmAdapter.setData(films);
                }
            }
        });
        filmViewModel.setListFilm(0);

        TabsPagerAdapter tabsPagerAdapter = new TabsPagerAdapter(this, getSupportFragmentManager(), filmViewModel);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(tabsPagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        if (getSupportActionBar() != null)
            getSupportActionBar().setElevation(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_lang) {
            ChangeLangFragment changeLangFragment = new ChangeLangFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            changeLangFragment.show(fragmentManager, ChangeLangFragment.class.getSimpleName());
        }
        return super.onOptionsItemSelected(item);
    }
}
