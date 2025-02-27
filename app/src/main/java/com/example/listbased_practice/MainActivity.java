package com.example.listbased_practice;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends FragmentActivity implements MainCallback {
    private Button btn1, btn2, btn3;
    TextView txtMsg;

    // The n-th row in the list will consist of [icon, label] where icon = thumbnail[n] and label=items[n]

    Integer[] thumbnails = {
            R.drawable.small_boy, R.drawable.small_boy, R.drawable.small_boy,
            R.drawable.small_boy, R.drawable.small_boy, R.drawable.small_boy,
            R.drawable.small_boy, R.drawable.small_boy, R.drawable.small_boy,
            R.drawable.small_boy, R.drawable.small_boy, R.drawable.small_boy,
            R.drawable.small_boy, R.drawable.small_boy, R.drawable.small_boy
    };

    private int page = 0;

//    @Override
//    public void onAttachFragment(Fragment fragment) {
//        super.onAttachFragment(fragment);
//// get a reference to each fragment attached to the GUI
//        if (fragment.getClass() == FragmentRed.class ){ redFragment = (FragmentRed) fragment; }
//        if (fragment.getClass() == FragmentBlue.class ){ blueFragment = (FragmentBlue) fragment; }
//    }


    FragmentTransaction ft;
    RightFrag rightFragment;
    LeftFrag leftFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ft = getSupportFragmentManager().beginTransaction();
        leftFragment = LeftFrag.newInstance("first");
        ft.replace(R.id.left, leftFragment); ft.commit();

        ft = getSupportFragmentManager().beginTransaction();
        rightFragment = RightFrag.newInstance("second", "");
        ft.replace(R.id.right, rightFragment); ft.commit();

    }//onCreate

    @Override
    public void onMsgFromFragToMain(String sender, String strValue) {
// show message arriving to MainActivity
//        Toast.makeText(getApplication(), " MAIN GOT>> " + sender + "\n"" + strValue, Toast.LENGTH_LONG).show();
        Toast.makeText(this, sender + strValue, Toast.LENGTH_SHORT).show();

        if (sender.equals("LEFT")) { /* TODO: if needed, do here something on behalf of the RED fragment*/
            rightFragment.onMsgFromMainToFragment("",strValue);
            Toast.makeText(this, "hioghrohgior", Toast.LENGTH_SHORT);

        }
        if (sender.equals("RIGHT")) {
            try { // forward blue-data to redFragment using its callback method
                leftFragment.onMsgFromMainToFragment(strValue);
            }
            catch (Exception e) { Log.e("ERROR", "onStrFromFragToMain " + e.getMessage()); }
            Toast.makeText(this, "left", Toast.LENGTH_SHORT);
        }
    }
}