package com.helpfully;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.helpfully.work.Advertable;

import java.util.ArrayList;

/**
 * Created by kamila on 29.10.17.
 */

public class AdvertsListAdapter extends BaseAdapter {
    private ArrayList<Advertable> myList;
    public Activity activity;

    public AdvertsListAdapter( ArrayList<Advertable> list,Activity activity) {
        super();
        this.myList = list;
        this.activity = activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        Advertable o = (Advertable) getItem(position);

        ImageView imageView;
        TextView textView;
        ImageButton removeButton;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.advertitem, null);
            imageView = convertView.findViewById(R.id.advertListImageView);
            textView = convertView.findViewById(R.id.advertListTitle);
            removeButton = convertView.findViewById(R.id.advertListRemoveButton);
        } else {
            imageView = convertView.findViewById(R.id.advertListImageView);
            textView = convertView.findViewById(R.id.advertListTitle);
            removeButton = convertView.findViewById(R.id.advertListRemoveButton);
        }
        //tutaj zainicjalizowac obiekt

        if (imageView != null) {
            //todo rysowanie
        }
        if (textView != null) {
            textView.setText(o.getTitle());
        }
        if (removeButton != null) {
            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            }); // sprawdzic czy dziala
        }

        return convertView;
    }

    @Override
    public int getCount() {
        return myList.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
