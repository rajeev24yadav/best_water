package water.retorfit;

import android.util.Log;

import java.io.IOException;
import java.net.URLDecoder;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

public class LogInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Log.i("LogInterceptor", "inside intercept callback");
        Request request = chain.request();
        long t1 = System.nanoTime();
        String requestLog = String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers());
        if (request.method().compareToIgnoreCase("post") == 0) {
            requestLog = "\n" + requestLog + "\n" + bodyToString(request);
        }

        try {
            String decoded = URLDecoder.decode(requestLog, "UTF-8");
            Log.e("TAG", "request :" + "\n" + decoded);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("TAG", "request :" + "\n" + requestLog);
        }

        Response response = chain.proceed(request);
        long t2 = System.nanoTime();

        String responseLog = String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers());

        String bodyString = response.body().string();

        try {
            String decoded2 = URLDecoder.decode(bodyString, "UTF-8");
            Log.e("TAG", "Log response :" + "\n" + decoded2);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("TAG", "Log response :" + "\n" + bodyString);
        }

        return response.newBuilder()
                .body(ResponseBody.create(response.body().contentType(), bodyString))
                .build();

    }

    public static String bodyToString(final Request request) {
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }



}
