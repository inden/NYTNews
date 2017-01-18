package com.in.den.android.popnews;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.in.den.android.popnews.data.News;
import com.in.den.android.popnews.databinding.NewsAdapterBinding;

import android.databinding.DataBindingUtil;
import android.widget.Toast;


import java.util.List;

/**
 * Created by harumi on 19/12/2016.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    private List<News.Result> newsList;
    LayoutInflater inflater = null;
    NewsPresenter newsPresenter;

    public NewsAdapter(List<News.Result> newslist) {
        super();
        newsList = newslist;
        newsPresenter = new NewsPresenter();

    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }

        NewsAdapterBinding binding = DataBindingUtil.inflate(inflater, R.layout.news_adapter, parent, false);
        NewsViewHolder viewHolder = new NewsViewHolder(binding);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        holder.bindConnection(newsList.get(position));

        holder.bindPresentor(newsPresenter);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
}
