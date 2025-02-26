package com.example.listbased_practice;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RightFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RightFrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RightFrag() {
        // Required empty public constructor
    }

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

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RightFrag.
     */
    // TODO: Rename and change types and number of parameters
    Button first, prev, next, last;

    public static RightFrag newInstance(String studentId, String param2) {
        RightFrag fragment = new RightFrag();
        Bundle args = new Bundle();
        args.putString("studentId", studentId);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_right, container, false);

        textViewId = view.findViewById(R.id.textViewId);
        textViewName = view.findViewById(R.id.textViewName);
        textViewClass = view.findViewById(R.id.textViewClass);
        textViewGPA = view.findViewById(R.id.textViewGPA);

        first = (Button) view.findViewById(R.id.buttonFirst);
        prev = (Button) view.findViewById(R.id.buttonPrevious);
        next = (Button) view.findViewById(R.id.buttonNext);
        last = (Button) view.findViewById(R.id.buttonLast);

        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = String.valueOf(textViewId.getText());
                updateUI(sub_items[getPageFromStudentId(id)*5 ]);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = String.valueOf(textViewId.getText());
                int index = getIndexFromStudentId(id); // Lấy vị trí hiện tại của sinh viên

                if (index == -1) return; // Nếu không tìm thấy, không làm gì cả

                int page = getPageFromStudentId(id); // Xác định trang hiện tại
                int pageStart = page * 5; // Vị trí bắt đầu của trang
                int pageEnd = pageStart + 4; // Vị trí kết thúc của trang (chỉ số tối đa)

                if (index < pageEnd && index < sub_items.length - 1) { // Nếu chưa đến cuối trang
                    updateUI(sub_items[index + 1]); // Hiển thị sinh viên tiếp theo trong trang
                } else {
                    Toast.makeText(requireContext(), "Đã hết danh sách trên trang!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = String.valueOf(textViewId.getText());
                int index = getIndexFromStudentId(id); // Lấy vị trí hiện tại của sinh viên

                if (index == -1) return; // Nếu không tìm thấy, không làm gì cả

                int page = getPageFromStudentId(id); // Xác định trang hiện tại
                int pageStart = page * 5; // Vị trí bắt đầu của trang

                if (index > pageStart) { // Nếu chưa đến đầu trang
                    updateUI(sub_items[index - 1]); // Hiển thị sinh viên trước đó trong trang
                } else {
                    Toast.makeText(requireContext(), "Đang ở đầu danh sách trang này!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = String.valueOf(textViewId.getText());
                int index = getIndexFromStudentId(id); // Lấy vị trí hiện tại của sinh viên

                if (index == -1) return; // Nếu không tìm thấy, không làm gì cả

                int page = getPageFromStudentId(id); // Xác định trang hiện tại
                int pageEnd = Math.min((page + 1) * 5 - 1, sub_items.length - 1); // Vị trí cuối trang

                if (index < pageEnd) { // Nếu chưa đến cuối trang
                    updateUI(sub_items[pageEnd]); // Hiển thị sinh viên cuối cùng của trang
                } else {
                    Toast.makeText(requireContext(), "Đang ở cuối danh sách trang này!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        Bundle arguments = getArguments();
        if (arguments != null) {
            String studentId = arguments.getString("studentId", "");
            updateUI(studentId);
        }

        return view;
    }

    private int getIndexFromStudentId(String studentId) {
        for (int i = 0; i < sub_items.length; i++) {
            if (sub_items[i].equals(studentId)) {
                return i;
            }
        }
        return -1; // Không tìm thấy
    }

    public void onMsgFromMainToFragment(String s, String strValue) {
        int index = Integer.parseInt(strValue);

        updateUI(sub_items[index]);
        Toast.makeText(requireContext(), sub_items[index], Toast.LENGTH_SHORT);
    }

    TextView textViewId, textViewName, textViewClass, textViewGPA;

    private int getPageFromStudentId(String studentId) {
        int index = -1;
        for (int i = 0; i < sub_items.length; i++) {
            if (sub_items[i].equals(studentId)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            return -1; // Không tìm thấy
        }

        return index / 5; // Mỗi trang có 5 sinh viên
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

    MainActivity main;

    private void doi() {
        main.onMsgFromFragToMain("RIGHT", textViewId.getText().toString());
    }
}