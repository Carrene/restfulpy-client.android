package carrene.com.restfulpy_clientandroid;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;


public class MainActivity extends AppCompatActivity{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        Client server = new Client();
        List<Project> projects;
        projects = server.request("http://localhost:3000/posts/1", "get").send();
        Log.e("project id", String.valueOf(projects.get(0)));
    }
}
