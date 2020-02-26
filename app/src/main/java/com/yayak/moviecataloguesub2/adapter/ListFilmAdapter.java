package com.yayak.moviecataloguesub2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yayak.moviecataloguesub2.R;
import com.yayak.moviecataloguesub2.model.Film;

import java.util.ArrayList;

public class ListFilmAdapter extends RecyclerView.Adapter<ListFilmAdapter.ListViewHolder> {
    private ArrayList<Film> listFilm = new ArrayList<>();
    private OnItemClickCallback onItemClickCallback;

    public interface OnItemClickCallback {
        void onItemClicked(Film film);
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public void setData(ArrayList<Film> list) {
        listFilm.clear();
        listFilm.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListFilmAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_show_list, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListFilmAdapter.ListViewHolder holder, int position) {
        String posterAddress = "https://image.tmdb.org/t/p/w185";
        Film film = listFilm.get(position);

        Glide.with(holder.itemView.getContext())
                .load(posterAddress + film.getPosterUrl())
                .apply(new RequestOptions().override(100, 150))
                .into(holder.imgPoster);

        holder.tvName.setText(film.getName());
        holder.tvOverview.setText(film.getOverview());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listFilm.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listFilm.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPoster;
        TextView tvName, tvOverview;

        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPoster = itemView.findViewById(R.id.img_poster);
            tvName = itemView.findViewById(R.id.tv_name);
            tvOverview = itemView.findViewById(R.id.tv_overview);
        }
    }
}
