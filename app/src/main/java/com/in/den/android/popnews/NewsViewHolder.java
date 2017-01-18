package com.in.den.android.popnews;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

import com.in.den.android.popnews.data.News;
import com.in.den.android.popnews.databinding.NewsAdapterBinding;

/**
 * Created by harumi on 19/12/2016.
 */

public class NewsViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder  {

    NewsAdapterBinding binding;
    CardView cardView;

    public NewsViewHolder(NewsAdapterBinding binding) {
        super(binding.getRoot());

        this.binding = binding;
    }

    public void bindConnection(News.Result news) {

        binding.setNews(news);
    }

    public void bindPresentor(NewsPresenter newsPresenter) {

        binding.setPresenter(newsPresenter);
    }
}
