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
    private String selectedStudentId;

    public CustomIconLabelAdapter( Context context, int layoutToBeInflated, String[] items, Integer[] thumbnails, String[] phones) {
        super(context, R.layout.custom_row_icon_label, items);
        this.context = context;
        this.thumbnails = thumbnails;
        this.items = items;
        this.phones = phones;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View row = inflater.inflate(R.layout.custom_row_icon_label, null);
//        TextView label = (TextView) row.findViewById(R.id.label);
        TextView phone = (TextView) row.findViewById(R.id.phone);
        ImageView icon = (ImageView) row.findViewById(R.id.icon);
//        label.setText(items[position]);
        phone.setText(phones[position]);
        icon.setImageResource(thumbnails[position]);
        return (row);
    }

    public void setSelectedStudentId(String studentId) {
        this.selectedStudentId = studentId;
        notifyDataSetChanged(); // Cập nhật UI ngay sau khi thay đổi ID
    }
    private void updateBackground(View rowView, String studentId) {
        if (studentId.equals(selectedStudentId)) {
            rowView.setBackgroundColor(Color.LTGRAY); // Màu xám nhạt khi được chọn
        } else {
            rowView.setBackgroundColor(Color.TRANSPARENT); // Màu bình thường
        }
    }
} // CustomAdapter