package edu.binghamton.multiplescreens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        TextView textView;
        textView = (TextView)findViewById(R.id.textView);

        String name = getIntent().getStringExtra("name");
        textView.setText(name);
    }
}