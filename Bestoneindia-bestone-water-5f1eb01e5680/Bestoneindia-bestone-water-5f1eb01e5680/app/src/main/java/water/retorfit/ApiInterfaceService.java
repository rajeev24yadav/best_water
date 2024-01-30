package water.retorfit;

import java.util.concurrent.TimeUnit;

import water.utils.Constants;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiInterfaceService {
    public static final int RC_SIGN_IN = 100;
    public static String accessToken;
    public static String firebaseDeviceToken = "";
    public static String userId;
    public static String apikey = "";
    public static double post_lat = 0.0d;
    public static double post_lng = 0.0d;


/*    private OkHttpClient.Builder getHttpClient() {
        if (BuildConfig.DEBUG) {

            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            httpClient.addInterceptor(new LogInterceptor());
            httpClient.networkInterceptors().add(interceptor);
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);

            httpClient.addInterceptor(loggingInterceptor);
            httpClient.networkInterceptors().add(interceptor);
        }
        return httpClient;
    }


    private static Retrofit retrofit;

    public  ApiInterface getApiService() {
       *//* if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client()
                    .build();*//*
        }
        return retrofit.create(ApiInterface.class);
    }*/

    public static ApiInterface initRetrofit() {
        // For logging request & response (Optional)
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Interceptor interceptor = chain -> {
            Request request = chain.request();
            Request newRequest = request.newBuilder()
                    //.addHeader("secret_key", Global.ACCESS_TOKEN)
                    .build();
            return chain.proceed(newRequest);
        };
        OkHttpClient.Builder builder =
                new OkHttpClient.Builder();
        builder.networkInterceptors().add(interceptor);
        OkHttpClient client = builder.addInterceptor(loggingInterceptor)
                .connectTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        return retrofit.create(ApiInterface.class);
    }

}
