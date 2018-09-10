package carrene.com.restfulpy_clientandroid;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;

public class Request<T extends Iproject> {

    private Client client;
    private String url;
    private List<T> response;

    public Request( Client client, String url){
        this.client = client;
        this.url = url;
    }
    public List<T> send(String url) {

        OkHttpClient okHttpClient = new OkHttpClient();
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .build();

        List<JsonObject> resp = null;

        try {
            resp = (List<JsonObject>) okHttpClient.newCall(request).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new GsonBuilder().create();

        for (JsonObject obj: resp) {
            this.response.add((T) gson.fromJson(obj, Request.class));
        }
        return response;
    }
}
