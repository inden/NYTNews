package com.in.den.android.popnews.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by harumi on 19/12/2016.
 */

public interface NYTService {
    @GET("/svc/mostpopular/v2/mostviewed/{section}/{time-period}.json")
    Call<News> getNews(@Path("section") String section,
                       @Path("time-period") String timeperiod);

    @GET("/svc/mostpopular/v2/mostviewed/all-sections/1.json")
    Call<News> getNewsAllsectionsToday();

    @GET("/svc/movies/v2/reviews/all.json")
    Call<Movie> getMoviesAll();
}
