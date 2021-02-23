package edu.binghamton.multiplescreens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView textview;
        textview = (TextView) findViewById(R.id.textView);
        String name = getIntent().getStringExtra("name");
        textview.setText(name);

    }
}