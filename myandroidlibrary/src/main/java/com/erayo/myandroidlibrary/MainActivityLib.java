package com.erayo.myandroidlibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivityLib extends AppCompatActivity {

    public static final String GET_SET_JOKE = "get_set_joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lib);

        TextView textView = findViewById(R.id.tv_joke);

        String joke;

        if (getIntent() != null) {
            String joke2 = getIntent().getExtras().getString(GET_SET_JOKE);
            joke = joke2 != null ? joke2 : "Joke Is Null";
        } else {
            joke = "Joke is not available";
        }

        textView.setText(joke);
    }
}
