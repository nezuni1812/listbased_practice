package com.example.listbased_practice;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FragmentRed extends Fragment implements FragmentCallbacks{
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
            "A1_1", "A1_2", "A1_3", "A1_4", "A1_5",
            "A2_1", "A2_2", "A2_3", "A2_4", "A2_5",
            "A3_1", "A3_2", "A3_3", "A3_4", "A3_5"
    };

    String[] studentClass = {
            "A1", "A1", "A1", "A1", "A1",
            "A2", "A2", "A2", "A2", "A2",
            "A3", "A3", "A3", "A3", "A3"
    };

    String[] studentGPA = {
            "7.2", "7.4", "7.6", "7.8", "8.0",
            "8.2", "8.4", "8.6", "8.8", "9.0",
            "9.2", "9.4", "9.6", "9.8", "10.0"
    };

    TextView textViewId, textViewName, textViewClass, textViewGPA;

    public static FragmentRed newInstance(String studentId) {
        FragmentRed fragment = new FragmentRed();
        Bundle bundle = new Bundle();
        bundle.putString("studentId", studentId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.layout_red, container, false);

        textViewId = view.findViewById(R.id.textViewId);
        textViewName = view.findViewById(R.id.textViewName);
        textViewClass = view.findViewById(R.id.textViewClass);
        textViewGPA = view.findViewById(R.id.textViewGPA);

        Bundle arguments = getArguments();
        if (arguments != null) {
            String studentId = arguments.getString("studentId", "");
            updateUI(studentId);
        }

        return view;
    }

    private void updateUI(String studentId) {
        int index = -1;
        for (int i = 0; i < sub_items.length; i++) {
            if (sub_items[i].equals(studentId)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            textViewId.setText(sub_items[index]);
            textViewName.setText(items[index]);
            textViewClass.setText(studentClass[index]);
            textViewGPA.setText(studentGPA[index]);
        } else {
            textViewId.setText("Không tìm thấy");
            textViewName.setText("-");
            textViewClass.setText("-");
            textViewGPA.setText("-");
        }
    }

    MainActivity main; TextView txtRed; Button btnRedClock;
};
