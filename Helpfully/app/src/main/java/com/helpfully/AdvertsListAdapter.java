package com.helpfully;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.helpfully.work.Advertable;

import java.util.ArrayList;

/**
 * Created by kamila on 29.10.17.
 */

public class AdvertsListAdapter extends ArrayAdapter {
    private ArrayList<Advertable> myList;

    public AdvertsListAdapter( Context context, int resource) {
        super(context, resource);
        this.myList = new ArrayList<>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v != null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.advertitem, null);


            Advertable o = (Advertable) getItem(position); //tutaj pobrac z listy

            if (o != null) {

                ImageView imageView = v.findViewById(R.id.advertListImageView);
                TextView textView = v.findViewById(R.id.advertListTitle);
                ImageButton removeButton = v.findViewById(R.id.advertListRemoveButton);

                //tutaj zainicjalizowac obiekt

                if (imageView != null) {
                    //todo rysowanie
                }
                if (textView != null) {
                    textView.setText(o.getTitle());
                }
                if (removeButton != null) {
                    this.remove(o); // sprawdzic czy dziala
                }

            }
        }
        return v;
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
