package com.in.den.android.popnews;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.in.den.android.popnews.data.Movie;
import com.in.den.android.popnews.databinding.MovieAdapterBinding;

/**
 * Created by harumi on 23/12/2016.
 */

public class MovieViewHolder extends RecyclerView.ViewHolder {

    MovieAdapterBinding binding;

    public MovieViewHolder(MovieAdapterBinding binding) {

        super(binding.getRoot());
        this.binding = binding;
    }

    public void bindPresenter(MoviePresenter presenter) {

        binding.setPresenter(presenter);
    }

    public void bindMovie(Movie.Result movie) {

        binding.setMovie(movie);
    }
}
