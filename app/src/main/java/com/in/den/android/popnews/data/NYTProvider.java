package com.in.den.android.popnews.data;



import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by harumi on 19/12/2016.
 */

public class NYTProvider {

    private static NYTService nytService = null;
    private static final String baseurl = "http://api.nytimes.com/";
    private static final String APIKEY_VALUE = "your-apikey-value-here";
    private static final String APIKEY_PARAM = "api-key";


    private NYTProvider() {
    }

    private static void init() {


        OkHttpClient httpClient =
                new OkHttpClient.Builder().addInterceptor(new Interceptor() {
              @Override
              public Response intercept(Interceptor.Chain chain) throws IOException {
                  Request original = chain.request();
                  HttpUrl originalHttpUrl = original.url();

                  HttpUrl url = originalHttpUrl.newBuilder()
                          .addQueryParameter(APIKEY_PARAM, APIKEY_VALUE)
                          .build();

                  // Request customization: add request headers
                  Request.Builder requestBuilder = original.newBuilder()
                          .url(url);

                  Request request = requestBuilder.build();
                  return chain.proceed(request);
              }
          }
        ).build();

        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl(baseurl)
                        .client(httpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();


        nytService = retrofit.create(NYTService.class);
    }

    public static NYTService getNytService() {
        if (nytService == null) {
            init();
        }
        return nytService;
    }
}
