package com.in.den.android.popnews;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.in.den.android.popnews.data.Movie;
import com.in.den.android.popnews.data.NYTProvider;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    private ArrayList<Movie.Result> arrayList;
    MovieAdapter mAdapter;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rViewMovie);
        arrayList = new ArrayList<Movie.Result>();

        mAdapter = new MovieAdapter(arrayList);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(),2));
        recyclerView.setAdapter(mAdapter);

        fetchMovie();

        return view;

    }

    protected void fetchMovie() {
        Call<Movie> newsCall = NYTProvider.getNytService().getMoviesAll();


        newsCall.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                int i = response.code();
                if (i == 200) {
                    if(mAdapter != null) {
                        arrayList.clear();
                        arrayList.addAll(response.body().getResults());
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });

    }

}
