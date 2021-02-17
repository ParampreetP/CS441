package edu.binghamton.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView txtview;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.hello_btn);
        txtview = (TextView) findViewById(R.id.hello_txt);


        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                txtview.setText("");
            }
        });
    }

}