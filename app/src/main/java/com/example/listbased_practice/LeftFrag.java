package com.example.listbased_practice;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LeftFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LeftFrag extends Fragment implements FragmentCallbacks {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
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

    Integer[] thumbnails = {
            R.drawable.small_boy, R.drawable.small_boy, R.drawable.small_boy,
            R.drawable.small_boy, R.drawable.small_boy, R.drawable.small_boy,
            R.drawable.small_boy, R.drawable.small_boy, R.drawable.small_boy,
            R.drawable.small_boy, R.drawable.small_boy, R.drawable.small_boy,
            R.drawable.small_boy, R.drawable.small_boy, R.drawable.small_boy
    };
    MainActivity main;
    Context context = null;
    int page = 0;
    int current = 0;
    public LeftFrag() {
        // Required empty public constructor
    }
    private Button btn1, btn2, btn3;
    public static LeftFrag newInstance() {
        LeftFrag fragment = new LeftFrag();
        Bundle args = new Bundle();
//        args.putString("strArg1", strArg);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        page = 0;
        btn1.setAlpha(0.5f);

        // Show the first 5 items
        showList(0, 5);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            context = getActivity(); // use this reference to invoke main callbacks
            main = (MainActivity) getActivity();
        } catch (IllegalStateException e) {
            throw new IllegalStateException("MainActivity must implement callbacks");
        }
    }

    TextView txtMsg;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FrameLayout layout_blue = (FrameLayout) inflater.inflate(R.layout.fragment_left, null);

        btn1  = (Button) layout_blue.findViewById(R.id.button1);
        btn2 = (Button) layout_blue.findViewById(R.id.button2);
        btn3  = (Button) layout_blue.findViewById(R.id.button3);


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
                page  = 1;
                btn3.setAlpha(1.0f);
                btn1.setAlpha(1.0f);
                btn2.setAlpha(0.5f);
                showList(5, 10);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                page  = 2;
                btn1.setAlpha(1.0f);
                btn2.setAlpha(1.0f);
                btn3.setAlpha(0.5f);
                showList(10, 15);
            }
        });

        txtMsg = (TextView) layout_blue.findViewById(R.id.txtMsg);

        ListView listView = (ListView) layout_blue.findViewById(R.id.left_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
        android.R.layout.simple_list_item_1, items);

        listView.setAdapter(adapter);
        listView.setSelection(0);
        listView.smoothScrollToPosition(0);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                current = position;
                updateInfo(position);
            }
        });


        return layout_blue;
    }

    private void updateInfo(int position) {
        Dictionary<String, String> valueToRight = new Hashtable<>();
        valueToRight.put("index", String.valueOf(position));
        valueToRight.put("id", sub_items[position + 5 * page]);
        valueToRight.put("name", items[position + 5 * page]);
        valueToRight.put("class", studentClass[position + 5 * page]);
        valueToRight.put("gpa", studentGPA[position + 5 * page]);
        valueToRight.put("isLast", String.valueOf(position == 4));

        main.onMsgFromFragToMain("LEFT", valueToRight);
        txtMsg.setText("Mã số: " + sub_items[position + 5 * page]);
    }
    public void showList(int start, int end) {
        String[] myItems = Arrays.copyOfRange(items, start, end);
        String[] mySubItems = Arrays.copyOfRange(sub_items, start, end);
        Integer[] myThumbnails = Arrays.copyOfRange(thumbnails, start, end);

        CustomIconLabelAdapter adapter = new CustomIconLabelAdapter(requireContext(),
                R.layout.custom_row_icon_label, myItems, myThumbnails, mySubItems);

        ListView listView = requireView().findViewById(R.id.left_list); // Ensure ListView reference
        listView.setAdapter(adapter);
    }


    @Override
    public void onMsgFromMainToFragment(Dictionary<String, String> value) {
        ListView listView = requireView().findViewById(R.id.left_list);
        CustomIconLabelAdapter adapter = (CustomIconLabelAdapter) listView.getAdapter();

        String command = value.get("command");
        switch (command) {
            case "next":
                if (current < 5 - 1){
                    current++;
                    updateInfo(current);
                }
                break;
            case "last":
                current = 4;
                updateInfo(current);
                break;
            case "prev":
                if (current > 0){
                    current--;
                    updateInfo(current);
                }
                break;
            case "first":
                current = 0;
                updateInfo(current);
                break;
        }
    }
}