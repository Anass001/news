package com.zeneo.newsapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import com.zeneo.newsapp.Adapter.MainListAdapter;
import com.zeneo.newsapp.Model.WebData;
import com.zeneo.newsapp.Model.WebSites;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    WebData webData = new WebData();
    int [] images = webData.images;

    List<WebSites> list = new ArrayList<>();
    GridView listView;
    MainListAdapter mainListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //handler.post(runnableCode);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        listView = (GridView) findViewById(R.id.mainlist);

        for (int i = 0 ;i<images.length ;i++ ){
            list.add(new WebSites(images[i]));
        }
        mainListAdapter = new MainListAdapter(getApplicationContext(),list);
        listView.setAdapter(mainListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),NewsActivity.class);
                intent.putExtra("index",String.valueOf(i));
                startActivity(intent);
            }
        });


}

    /*Handler handler = new Handler();

    private Runnable runnableCode = new Runnable() {
        @Override
        public void run() {
            deleteCache(getApplicationContext());
            Log.d("Handlers", "Called on main thread");
            handler.postDelayed(runnableCode, hourToMilliseconds(24));
        }
    };

    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) { e.printStackTrace();}
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }

    public static int hourToMilliseconds(int h){
        int ms = h*1000*60*60;
        return ms;
    }*/

}
