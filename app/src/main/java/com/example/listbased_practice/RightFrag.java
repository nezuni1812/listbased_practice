package com.example.listbased_practice;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RightFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RightFrag extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public RightFrag() {
        // Required empty public constructor
    }

    Button first, prev, next, last;

    public static RightFrag newInstance() {
        RightFrag fragment = new RightFrag();
        Bundle args = new Bundle();
//        args.putString("studentId", studentId);
//        args.putString(ARG_PARAM2, param2);
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
                doi("first");
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doi("next");
            }
        });


        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doi("prev");
            }
        });

        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doi("last");
            }
        });


        Bundle arguments = getArguments();
//        if (arguments != null) {
//            String studentId = arguments.getString("studentId", "");
//            updateUI(studentId);
//        }

        return view;
    }

    public void onMsgFromMainToFragment(Dictionary<String, String> value) {
        int index = Integer.parseInt(value.get("index"));
        String id = value.get("id");
        String name = value.get("name");
        String studentClass = value.get("class");
        String gpa = value.get("gpa");
        boolean isLast = Boolean.parseBoolean(value.get("isLast"));

        first.setEnabled(true);
        next.setEnabled(true);
        prev.setEnabled(true);
        last.setEnabled(true);

        first.setAlpha(1);
        next.setAlpha(1);
        prev.setAlpha(1);
        last.setAlpha(1);

        if (isLast){
            next.setAlpha(0.5f);
            next.setEnabled(false);

            last.setAlpha(0.5f);
            last.setEnabled(false);
        }

        if (index == 0){
            prev.setAlpha(0.5f);
            prev.setEnabled(false);

            first.setAlpha(0.5f);
            first.setEnabled(false);
        }

        updateUI(id, name, studentClass, gpa);
    }

    TextView textViewId, textViewName, textViewClass, textViewGPA;

    private void updateUI(String studentId, String studenttName, String studenttClass, String studenttGPA) {
        if (studentId != null) {
            textViewId.setText(studentId);
            textViewName.setText(studenttName);
            textViewClass.setText(studenttClass);
            textViewGPA.setText(studenttGPA);
        } else {
            textViewId.setText("Không tìm thấy");
            textViewName.setText("-");
            textViewClass.setText("-");
            textViewGPA.setText("-");
        }
    }

    MainActivity main;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof MainActivity) {
            main = (MainActivity) context;
        }
    }

    private void doi(String commandValue) {
        Dictionary<String, String> valueToLeft = new Hashtable<>();
        valueToLeft.put("command", commandValue);

        main.onMsgFromFragToMain("RIGHT", valueToLeft);
    }
}