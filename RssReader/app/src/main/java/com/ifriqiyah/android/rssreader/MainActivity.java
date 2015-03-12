package com.ifriqiyah.android.rssreader;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.ifriqiyah.android.rssreader.adapter.MenuElementAdapter;
import com.ifriqiyah.android.rssreader.adapter.MenuElementModel;
import com.ifriqiyah.android.rssreader.reader.MenuElementReader;

import java.io.File;
import java.io.IOException;

public class MainActivity extends Activity {

    private ListView listViewMenuElement;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewMenuElement = (ListView) findViewById(R.id.listViewMenuElement);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (!new File("/data/data/" + MyApplication.getMyPackageName() + "/files/").exists()) {
                    new File("/data/data/" + MyApplication.getMyPackageName() + "/files/").mkdir();
                    try {
                        new MenuElementReader().readAndFillList();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                new MenuElementModel().refresh();
                listViewMenuElement.setAdapter(new MenuElementAdapter());
                listViewMenuElement.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
            }
        }).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
