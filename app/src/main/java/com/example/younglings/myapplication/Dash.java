package com.example.younglings.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Dash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);

        TextView tvname= (TextView)(findViewById(R.id.tvName));


        Intent intent= getIntent();
        String name=intent.getStringExtra("User");
        tvname.setText(name);
    }
}
