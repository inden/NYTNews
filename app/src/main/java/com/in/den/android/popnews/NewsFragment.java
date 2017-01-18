package com.in.den.android.popnews;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.in.den.android.popnews.data.News;
import com.in.den.android.popnews.data.NYTProvider;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {

    ArrayList<News.Result> arrayList;
    NewsAdapter mAdapter = null;
    String section = "";
    String period = "1";

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rView);
        arrayList = new ArrayList<News.Result>();

        mAdapter = new NewsAdapter(arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(mAdapter);

        fetchNews();

        return view;
    }

    protected void setSection(String section) {
        this.section = section;
    }

    protected void fetchNews() {
        Call<News> newsCall;
        if(section.isEmpty()) {
            newsCall = NYTProvider.getNytService().getNewsAllsectionsToday();
        }
        else {
            newsCall = NYTProvider.getNytService().getNews(section,period);
        }

        newsCall.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
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
            public void onFailure(Call<News> call, Throwable t) {

            }
        });

    }

}
