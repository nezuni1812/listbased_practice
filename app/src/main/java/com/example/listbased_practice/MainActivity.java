package com.example.listbased_practice;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import java.util.Dictionary;

public class MainActivity extends FragmentActivity implements MainCallback {
    FragmentTransaction ft;
    RightFrag rightFragment;
    LeftFrag leftFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ft = getSupportFragmentManager().beginTransaction();
        leftFragment = LeftFrag.newInstance();
        ft.replace(R.id.left, leftFragment);
        ft.commit();

        ft = getSupportFragmentManager().beginTransaction();
        rightFragment = RightFrag.newInstance();
        ft.replace(R.id.right, rightFragment);
        ft.commit();

    }//onCreate

    @Override
    public void onMsgFromFragToMain(String sender, Dictionary<String, String> value) {
        if (sender.equals("LEFT")) {
            rightFragment.onMsgFromMainToFragment(value);

        }
        if (sender.equals("RIGHT")) {
            try {
                leftFragment.onMsgFromMainToFragment(value);
            }
            catch (Exception e) { Log.e("ERROR", "onStrFromFragToMain " + e.getMessage()); }
            Toast.makeText(this, "left", Toast.LENGTH_SHORT);
        }
    }
}