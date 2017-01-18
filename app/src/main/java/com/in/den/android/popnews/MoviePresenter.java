package com.in.den.android.popnews;

import android.app.Activity;

import android.content.Context;
import android.content.ContextWrapper;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.databinding.BindingAdapter;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.in.den.android.popnews.data.Movie;
import com.squareup.picasso.Picasso;

/**
 * Created by harumi on 23/12/2016.
 */

public class MoviePresenter {

    private final String FRAGTAG = "moviefragment";

    Fragment fragment = null;
    public MoviePresenter(){
    }

    public MoviePresenter(Fragment fragment) {
        this.fragment = fragment;
    }

    public void showDetail(View view, Movie.Result movie) {

        AppCompatActivity appCompatActivity = (AppCompatActivity)view.getContext();

        MovieDialogFragment movieDialogFragment = new MovieDialogFragment();
        movieDialogFragment.setMovieResult(movie);
        movieDialogFragment.setMoviePresenter(new MoviePresenter(movieDialogFragment));

        FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();

        movieDialogFragment.show(fragmentManager, FRAGTAG);

    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.movie)
                .into(view);
    }


    //fragment
    public void dismiss() {
        if(fragment != null) {
            ((MovieDialogFragment)fragment).dismiss();
        }
    }

    //methode used in the dialog fragment
    @BindingAdapter({"bind:loadUrl"})
    public static void loadUrl(WebView view, String url) {
        if(url == null || url.isEmpty()) {
            view.setVisibility(WebView.GONE);
        }
        else {
            view.setWebViewClient(new MyBrowser());
            view.getSettings().setLoadsImagesAutomatically(true);
            view.getSettings().setJavaScriptEnabled(true);
            view.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            view.loadUrl(url);
        }
    }

    private static class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
