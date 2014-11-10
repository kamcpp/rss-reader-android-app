package com.ifriqiyah.android.rssreader.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ifriqiyah.android.rssreader.R;
import com.ifriqiyah.android.rssreader.menu.MenuElement;
import com.ifriqiyah.android.rssreader.menu.MenuUpdaterTask;
import com.ifriqiyah.android.rssreader.services.RssFetcherService;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(getApplicationContext(), RssFetcherService.class));
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void buttonLoadMenuClicked(View view) {
        MenuUpdaterTask menuUpdaterTask = new MenuUpdaterTask(getApplicationContext());
        menuUpdaterTask.execute(new Void[] {});
        try {
            String text= "";
            List<MenuElement> menuElements = menuUpdaterTask.get();
            for(MenuElement menuElement : menuElements) {
                text += menuElement.getText() + "\r\n";
            }
            TextView menuTextView = (TextView)findViewById(R.id.menuTextView);
            menuTextView.setText(text);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
