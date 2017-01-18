package com.in.den.android.popnews;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.in.den.android.popnews.data.Movie;
import com.in.den.android.popnews.databinding.FragmentMovieDialogBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDialogFragment extends DialogFragment {

    MoviePresenter presenter;
    Movie.Result movieResult;

    public MovieDialogFragment() {
        // Required empty public constructor

        presenter = null;
        movieResult = null;
    }

    public void setMoviePresenter(MoviePresenter moviePresenter) {
        presenter = moviePresenter;
    }

    public void setMovieResult(Movie.Result result) {
        movieResult = result;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        FragmentMovieDialogBinding binding = DataBindingUtil.
                inflate(inflater, R.layout.fragment_movie_dialog, container, false);

        binding.setMovie(movieResult);
        binding.setPresenter(presenter);

        return binding.getRoot();
    }

    /*
    DialogFragment size problem
    Code here copied from
    http://stackoverflow.com/questions/12478520/how-to-set-dialogfragments-width-and-height
     */

    @Override
    public void onResume() {
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);

        super.onResume();
    }


}

