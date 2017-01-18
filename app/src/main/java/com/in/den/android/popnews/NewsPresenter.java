package com.in.den.android.popnews;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import com.in.den.android.popnews.data.News;

import java.util.Calendar;

/**
 * Created by harumi on 20/12/2016.
 */

public class NewsPresenter {


    public NewsPresenter() {
    }

    private static String today;

    private static String yesterday;

    static {

        Calendar cal = Calendar.getInstance();

        today = cal.get(Calendar.YEAR) + "-" + getMonth(cal.get(Calendar.MONTH)) + "-" + cal.get(Calendar.DATE);

        cal.add(Calendar.DATE, -1);

        yesterday = cal.get(Calendar.YEAR) + "-" + getMonth(cal.get(Calendar.MONTH)) + "-" + cal.get(Calendar.DATE);
    }

    private static String getMonth(int imonth) {
        imonth = imonth + 1;
        String month;
        if(imonth < 10) {
            month = "0" + imonth;
        }
        else {
            month = String.valueOf(imonth);
        }
        return month;
    }


    public void show(View view) {

        Toast.makeText(view.getContext(), "hello", Toast.LENGTH_SHORT).show();
    }

    public void readMore(View view, News.Result result) {

        Uri uri= Uri.parse(result.getUrl());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        view.getContext().startActivity(intent);
    }

    public Drawable getSectionColor(String section) {

        //return new ColorDrawable( Color.parseColor("#00aacc"));
        String color;
        switch(section.toLowerCase()) {
            case "u.s." : color = "#995790"; break;
            case "world" : color = "#579960"; break;
            case "sport" : color = "#576F99"; break;
            case "health" : color = "#998157"; break;
            case "arts" : color = "#5C67E0"; break;
            case "opinion" : color = "#5CE093"; break;
            default:  color = "#E0D55C"; break;
        }

        return new ColorDrawable( Color.parseColor(color));
    }

    public String getPubdateFormated(String publishedDate) {
        String sday;
        if(publishedDate.equals(today)) {
            sday = "today";
        }
        else if(publishedDate.equals(yesterday)) {
            sday = "yesterday";
        }
        else {
            sday = publishedDate;
        }
        return sday;
    }
}