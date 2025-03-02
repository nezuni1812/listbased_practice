package com.example.listbased_practice;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class CustomIconLabelAdapter extends ArrayAdapter<String> {
    Context context;
    Integer[] thumbnails;
    String[] items;
    String[] phones;
    private int selectedPosition = -1;

    public CustomIconLabelAdapter( Context context, int layoutToBeInflated, String[] items, Integer[] thumbnails, String[] phones) {
        super(context, R.layout.custom_row_icon_label, items);
        this.context = context;
        this.thumbnails = thumbnails;
        this.items = items;
        this.phones = phones;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.custom_row_icon_label, parent, false);
            holder = new ViewHolder();
            holder.phone = convertView.findViewById(R.id.phone);
            holder.icon = convertView.findViewById(R.id.icon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.phone.setText(phones[position]);
        holder.icon.setImageResource(thumbnails[position]);

        if (position == selectedPosition) {
            convertView.setBackgroundColor(Color.parseColor("#0096FF"));
        } else {
            convertView.setBackgroundColor(Color.TRANSPARENT);
        }

        return convertView;
    }

    static class ViewHolder {
        TextView phone;
        ImageView icon;
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
        notifyDataSetChanged();
    }
}