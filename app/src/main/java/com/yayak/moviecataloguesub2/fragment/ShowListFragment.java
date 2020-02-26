package com.yayak.moviecataloguesub2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yayak.moviecataloguesub2.DetailActivity;
import com.yayak.moviecataloguesub2.R;
import com.yayak.moviecataloguesub2.adapter.ListFilmAdapter;
import com.yayak.moviecataloguesub2.model.Film;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowListFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private ArrayList<Film> list = new ArrayList<>();

    public static ShowListFragment newInstance(int index) {
        ShowListFragment showListFragment = new ShowListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        showListFragment.setArguments(bundle);
        return showListFragment;
    }

    public ShowListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int index = 0;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }

        RecyclerView rvShowList = view.findViewById(R.id.rv_list);
        rvShowList.setHasFixedSize(true);
        rvShowList.setLayoutManager(new LinearLayoutManager(getContext()));
        ListFilmAdapter listFilmAdapter = new ListFilmAdapter();
        listFilmAdapter.notifyDataSetChanged();
        rvShowList.setAdapter(listFilmAdapter);

        //list.addAll(getListFilm(index));

        listFilmAdapter.setOnItemClickCallback(new ListFilmAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Film film) {
                Intent detailFilm = new Intent(getActivity(), DetailActivity.class);
                detailFilm.putExtra(DetailActivity.DETAIL_FILM, film);
                startActivity(detailFilm);
            }
        });
    }
/*
    private ArrayList<Film> getListFilm(int index) {
        TypedArray dataPoster;
        String[] dataName, dataRelease, dataOverview, dataGenre;

        // prepare()
        if (index == 0) {
            dataName = getResources().getStringArray(R.array.movie_name);
            dataRelease = getResources().getStringArray(R.array.movie_release);
            dataOverview = getResources().getStringArray(R.array.movie_overview);
            dataGenre = getResources().getStringArray(R.array.movie_genre);
            dataPoster = getResources().obtainTypedArray(R.array.movie_poster);
        } else {
            dataName = getResources().getStringArray(R.array.tvshow_name);
            dataRelease = getResources().getStringArray(R.array.tvshow_release);
            dataOverview = getResources().getStringArray(R.array.tvshow_overview);
            dataGenre = getResources().getStringArray(R.array.tvshow_genre);
            dataPoster = getResources().obtainTypedArray(R.array.tvshow_poster);
        }

        // addItem()
        ArrayList<Film> listData = new ArrayList<>();
        for (int i = 0; i < dataName.length; i++) {
            Film film = new Film();
            film.setPosterUrl(dataPoster.getResourceId(i, -1));
            film.setRelease(dataRelease[i]);
            film.setName(dataName[i]);
            film.setGenre(dataGenre[i]);
            film.setOverview(dataOverview[i]);
            listData.add(film);
        }
        dataPoster.recycle();
        return listData;
    }
*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_list, container, false);
    }
}
