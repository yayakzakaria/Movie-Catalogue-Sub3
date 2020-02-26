package com.yayak.moviecataloguesub2.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Film implements Parcelable {
    private String name, release, overview, genre, posterUrl;

    public Film() {
    }

    protected Film(Parcel in) {
        name = in.readString();
        release = in.readString();
        overview = in.readString();
        genre = in.readString();
        posterUrl = in.readString();
    }

    public static final Creator<Film> CREATOR = new Creator<Film>() {
        @Override
        public Film createFromParcel(Parcel in) {
            return new Film(in);
        }

        @Override
        public Film[] newArray(int size) {
            return new Film[size];
        }
    };

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(release);
        dest.writeString(overview);
        dest.writeString(genre);
        dest.writeString(posterUrl);
    }
}
