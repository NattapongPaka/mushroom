package app.cpe.mushroom.manager;

import java.io.IOException;


import app.cpe.mushroom.BuildConfig;
import app.cpe.mushroom.Config;
import app.cpe.mushroom.utils.ToStringConverterFactory;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by PONG on 10/7/2016 AD.
 */

public class HttpManager {
    private static HttpManager instatance;

    public static HttpManager getInstatance() {
        if (instatance == null)
            instatance = new HttpManager();
        return instatance;
    }

    private ApiService service;
    private Retrofit retrofit;

    private HttpManager() {

        retrofit = new Retrofit.Builder()
                .baseUrl(Config.url + "/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())     //Converter to gson
                .addConverterFactory(new ToStringConverterFactory())    //Converter to string
                .client(customClient())
                .build();

        service = retrofit.create(ApiService.class);
    }

    public OkHttpClient customClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.followRedirects(true);
        okHttpClient.followSslRedirects(true);
        okHttpClient.retryOnConnectionFailure(true);

//        okHttpClient.addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request original = chain.request();
//
//                String token = (String) PreferenceUtil.getInstance().getValue(Constance.KEY_TOKEN);
//                LogUtil.D("Last Token %s", token != null ? token : "Empty");
//                //TODO Custom header
//                Request request = original.newBuilder()
//                        .header("Authorization", token != null ? "Bearer " + token : "")
//                        .build();
//                return chain.proceed(request);
//            }
//        });

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClient.addNetworkInterceptor(httpLoggingInterceptor);
        }
        return okHttpClient.build();
    }


    public ApiService getService() {
        return service;
    }
}
