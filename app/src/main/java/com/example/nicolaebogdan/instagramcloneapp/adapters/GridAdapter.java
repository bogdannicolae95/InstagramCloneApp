package com.example.nicolaebogdan.instagramcloneapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.nicolaebogdan.instagramcloneapp.R;


public class GridAdapter extends BaseAdapter {

    private int[] icons;
    private Context context;
    private LayoutInflater inflater;

    public GridAdapter(Context context,int icons[]){
        this.context = context;
        this.icons = icons;
    }

    @Override
    public int getCount() {
        return icons.length;
    }

    @Override
    public Object getItem(int position) {
        return icons[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View gridView = convertView;

        if(convertView == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            gridView = inflater.inflate(R.layout.list_item,null);

        }

        ImageView icon = gridView.findViewById(R.id.imageView);


        icon.setImageResource(R.drawable.boy);





        return gridView;
    }
}
