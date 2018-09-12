package carrene.com.restfulpy_clientandroid;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class Request<T extends Iproject> {

    private Client client;
    private String url;
    private List<T> response;
    private String method;
    private okhttp3.Request.Builder requestBuilder;
    private okhttp3.Request request;
    private RequestBody requestBody;
    private boolean hasBody = false;
    private HttpUrl.Builder httpBuilder;

    private static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public Request( Client client, String url, String verb){
        this.client = client;
        this.url = url;
        this.method = verb;
        requestBuilder = new okhttp3.Request.Builder();
        httpBuilder = HttpUrl.parse(url).newBuilder();
    }

    public List<T> send() {

        OkHttpClient okHttpClient = new OkHttpClient();
        List<JsonObject> resp = null;

        if(!hasBody)
            request = requestBuilder.url(url).build();
        else {
            switch (method) {
                case "post":
                    request = requestBuilder.url(url).post(requestBody).build();

                case "put":
                    request = requestBuilder.url(url).put(requestBody).build();

                case "delete":
                    request = requestBuilder.url(url).delete().build();

                default:
                    request = requestBuilder.url(url).build();
            }
        }

        try {
            resp = (List<JsonObject>) okHttpClient.newCall(request).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new GsonBuilder().create();
        if (resp != null){
        for (JsonObject obj: resp) {
            this.response.add((T) gson.fromJson(obj, Request.class));
        }
        return response;
        }
        else return null;
    }


    public Request<T> addParameteres(){
        return this;
    }

    public Request<T> addHeader(String key, String value){
        requestBuilder.addHeader(key, value);

        return this;
    }

    public Request<T> addQueryString(String key, String value){
        httpBuilder.addQueryParameter(key, value);
        url = String.valueOf(httpBuilder.build());
        Log.e("query", url );
        return this;
    }

    public Request<T> setBody(String json){
        requestBody = RequestBody.create(JSON, json);
        hasBody = true;
        return this;
    }
}
