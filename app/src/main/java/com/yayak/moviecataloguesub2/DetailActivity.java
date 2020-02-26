package com.yayak.moviecataloguesub2;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.yayak.moviecataloguesub2.model.Film;

public class DetailActivity extends AppCompatActivity {
    public static final String DETAIL_FILM = "detail_film";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imgPoster = findViewById(R.id.img_poster);
        TextView tvRelease = findViewById(R.id.tv_release);
        TextView tvName = findViewById(R.id.tv_name);
        TextView tvGenre = findViewById(R.id.tv_genre);
        TextView tvOverview = findViewById(R.id.tv_overview);

        String posterAddress = "https://image.tmdb.org/t/p/w185";

        Film film = getIntent().getParcelableExtra(DETAIL_FILM);
        if (film != null) {
            Glide.with(this).load(posterAddress + film.getPosterUrl()).into(imgPoster);
            tvRelease.setText(film.getRelease());
            tvName.setText(film.getName());
            tvGenre.setText(film.getGenre());
            tvOverview.setText(film.getOverview());
        }

        if (getSupportActionBar() != null) {
            if (film != null) {
                getSupportActionBar().setTitle(film.getName());
            }
        }
    }
}
