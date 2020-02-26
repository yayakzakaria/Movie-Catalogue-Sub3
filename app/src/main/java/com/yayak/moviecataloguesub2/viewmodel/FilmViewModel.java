package com.yayak.moviecataloguesub2.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.yayak.moviecataloguesub2.model.Film;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class FilmViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Film>> listFilm = new MutableLiveData<>();

    public void setListFilm(final int index) {
        final ArrayList<Film> listData = new ArrayList<>();

        String apiKey = "729728efb3931dd73dff230d8a5230ee";
        String url = "https://api.themoviedb.org/3/discover/";

        if(index == 0) {
            url += "movie?language=en-US&api_key=" + apiKey;
        } else {
            url += "tv?language=en-US&api_key=" + apiKey;
        }

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    //parsing json
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject item = list.getJSONObject(i);
                        Film film = new Film();
                        film.setName(item.getString("name"));
                        film.setRelease(item.getString("first_air_date"));
                        film.setOverview(item.getString("overview"));
                        //film.setGenre(item.getString("genre_ids"));
                        film.setPosterUrl(item.getString("poster_path"));
                        listData.add(film);
                    }
                    listFilm.postValue(listData);
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }

    public LiveData<ArrayList<Film>> getListFilms() {
        return listFilm;
    }
}
