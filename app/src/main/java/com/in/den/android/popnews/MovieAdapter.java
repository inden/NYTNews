package com.in.den.android.popnews;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.in.den.android.popnews.data.Movie;
import com.in.den.android.popnews.databinding.MovieAdapterBinding;
import com.in.den.android.popnews.databinding.NewsAdapterBinding;

import java.util.List;

/**
 * Created by harumi on 23/12/2016.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private List<Movie.Result> listMovie;
    MoviePresenter moviePresenter;
    LayoutInflater inflater = null;

    public MovieAdapter(List<Movie.Result> movies) {
        super();
        listMovie = movies;
        moviePresenter = new MoviePresenter();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }

        MovieAdapterBinding binding = DataBindingUtil.inflate(inflater, R.layout.movie_adapter, parent, false);
        return new MovieViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie.Result result = listMovie.get(position);
        holder.bindMovie(result);
        holder.bindPresenter(moviePresenter);
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }
}
