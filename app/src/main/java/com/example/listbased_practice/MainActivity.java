package com.example.listbased_practice;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;

public class MainActivity extends ListActivity {
    private Button btn1, btn2, btn3;
    TextView txtMsg;
    // The n-th row in the list will consist of [icon, label] where icon = thumbnail[n] and label=items[n]
    String[] items = {
            "Phan Văn An",
            "Nguyễn Thị Bình",
            "Trần Văn Cường",
            "Lê Thị Dung",
            "Đặng Văn Em",
            "Hoàng Thị Pha",
            "Võ Văn Giàu",
            "Bùi Thị Hợp",
            "Đỗ Văn In",
            "Ngô Thị Giêng",
            "Phạm Văn Khương",
            "Huỳnh Thị Loan",
            "Dương Văn Mai",
            "Lý Thị Nhàn",
            "Tô Văn Oanh"
    };
    String[] sub_items = {
            "0987654321",
            "0912345678",
            "0909876543",
            "0934567890",
            "0971234567",
            "0967890123",
            "0923456789",
            "0898765432",
            "0887654321",
            "0945678901",
            "0956789012",
            "0812345678",
            "0823456789",
            "0834567890",
            "0845678901"
    };

    Integer[] thumbnails = {
            R.drawable.small_boy, R.drawable.small_boy, R.drawable.small_boy,
            R.drawable.small_boy, R.drawable.small_boy, R.drawable.small_boy,
            R.drawable.small_boy, R.drawable.small_boy, R.drawable.small_boy,
            R.drawable.small_boy, R.drawable.small_boy, R.drawable.small_boy,
            R.drawable.small_boy, R.drawable.small_boy, R.drawable.small_boy
    };

    private int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMsg = (TextView) findViewById(R.id.txtMsg);
        btn1  = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3  = findViewById(R.id.button3);
        showList(0, 5);
        btn1.setAlpha(.3f);

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                page  = 0;
                btn3.setAlpha(1.0f);
                btn2.setAlpha(1.0f);
                btn1.setAlpha(0.5f);
                showList(0, 5);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                page = 1;
                btn1.setAlpha(1.0f);
                btn3.setAlpha(1.0f);
                btn2.setAlpha(0.5f);
                showList(5, 10);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                page = 2;
                btn1.setAlpha(1.0f);
                btn2.setAlpha(1.0f);
                btn3.setAlpha(0.3f);
                showList(10, 15);
            }
        });


    }//onCreate

    public void showList(int start, int end){
        String[] myItems = Arrays.copyOfRange(items, start, end);
        Integer[] myThumbnails = Arrays.copyOfRange(thumbnails, start, end);

        CustomIconLabelAdapter adapter = new CustomIconLabelAdapter(MainActivity.this, R.layout.custom_row_icon_label, myItems, myThumbnails, sub_items);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        int index = page * 5 + position; // Tính index chính xác
        if (index >= 0 && index < items.length) { // Kiểm tra tránh tràn mảng
            txtMsg.setText("You choose: " + items[index]);
        } else {
            txtMsg.setText("Invalid selection");
        }
    }


}