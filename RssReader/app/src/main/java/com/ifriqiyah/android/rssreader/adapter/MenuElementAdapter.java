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

import java.sql.SQLException;

public class MenuElementAdapter extends ArrayAdapter<MenuElement>{

    public MenuElementAdapter() {
        super(MyApplication.getContext() , R.layout.menu_element_layout,MyApplication.getMenuElementEntities());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) MyApplication.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View menuElementView = layoutInflater.inflate(R.layout.menu_element_layout, null);
        Dao<MenuElement, Integer> menuElementDao = MyApplication.getMenuElementDao();
        MenuElement menuElement = null;
        try {
            menuElement = menuElementDao.queryForId(position);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ImageView viewById = (ImageView) menuElementView.findViewById(R.id.imageViewBigIcon);
        viewById.setImageBitmap(BitmapUtility.getImage(menuElement.getBigIcon()));
        TextView viewById1 = (TextView) menuElementView.findViewById(R.id.textViewText);
        viewById1.setText(menuElement.getText());
        TextView viewById2 = (TextView) menuElementView.findViewById(R.id.textViewTextEn);
        viewById2.setText(menuElement.getEnglishText());

        return menuElementView;
    }
}
