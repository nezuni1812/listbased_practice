package com.example.listbased_practice;

import java.util.Dictionary;

public interface MainCallback {
    void onMsgFromFragToMain(String sender, Dictionary<String, String> strValue);
}
