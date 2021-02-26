package edu.binghamton.multiplescreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final TextView textview;
        Button button;

        button = (Button) findViewById(R.id.button);
        textview = (TextView) findViewById(R.id.textView);
        String name = getIntent().getStringExtra("name");
        textview.setText(name);


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String name = textview.getText().toString();
                Intent myIntent = new Intent(getApplicationContext(),MainActivity3.class);
                myIntent.putExtra("name", name);
                MainActivity2.this.startActivity(myIntent);
            }
        });
}}