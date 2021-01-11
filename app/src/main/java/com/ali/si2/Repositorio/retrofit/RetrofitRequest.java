package com.ali.si2.Repositorio.retrofit;

import android.content.Context;
import android.content.SharedPreferences;


import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class RetrofitRequest {
    private static Retrofit retrofit;
    private static final String BASE_URL="http://si2-web.herokuapp.com/api/";
    public static Retrofit getRetrofitInstance(Context context) {

        if (retrofit == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("userToken", Context.MODE_PRIVATE);
            String token = sharedPreferences.getString("token","");
            OkHttpClient okHttpClient=new OkHttpClient().newBuilder()
                    .addInterceptor(new Interceptor() {
                        @NotNull
                        @Override
                        public Response intercept(@NotNull Chain chain) throws IOException {
                            Request newRequest=chain.request().newBuilder()
                                    .addHeader("Authorization","Bearer "+token)
                                    .build();
                            return chain.proceed(newRequest);
                        }
                    })
                    .connectTimeout(2, TimeUnit.MINUTES)
                    .readTimeout(2, TimeUnit.MINUTES)
                    .writeTimeout(2, TimeUnit.MINUTES)
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public static void delete(){
        retrofit=null;
    }

}
