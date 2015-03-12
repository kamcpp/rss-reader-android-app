package com.ifriqiyah.android.rssreader.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ifriqiyah.android.rssreader.MyApplication;
import com.ifriqiyah.android.rssreader.R;
import com.ifriqiyah.android.rssreader.domain.MenuElement;
import com.ifriqiyah.android.rssreader.util.BitmapUtility;
import com.j256.ormlite.dao.Dao;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

public class MenuElementAdapter extends ArrayAdapter<MenuElement> {

    @Inject
    List<MenuElement> menuElements;

    @Inject
    Dao<MenuElement, Integer> menuElementDao;

    public MenuElementAdapter() {
        super(MyApplication.getContext(), R.layout.menu_element_layout);
        try {
            Field mObjects1 = this.getClass().getSuperclass().getDeclaredField("mObjects");
            MyApplication.getObjectGraph().inject(this);
            mObjects1.setAccessible(true);
            mObjects1.set(this, menuElements);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) MyApplication.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View menuElementView = layoutInflater.inflate(R.layout.menu_element_layout, null);
        MenuElement menuElement = null;
        try {
            menuElement = menuElementDao.queryForId(position);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ImageView viewById = (ImageView) menuElementView.findViewById(R.id.imageViewBigIcon);
        if (menuElement.getBigIcon() != null) {
            viewById.setImageBitmap(BitmapUtility.getImage(menuElement.getBigIcon()));
        }
        TextView viewById1 = (TextView) menuElementView.findViewById(R.id.textViewText);
        viewById1.setText(menuElement.getText());
        TextView viewById2 = (TextView) menuElementView.findViewById(R.id.textViewTextEn);
        viewById2.setText(menuElement.getEnglishText());

        return menuElementView;
    }
}
