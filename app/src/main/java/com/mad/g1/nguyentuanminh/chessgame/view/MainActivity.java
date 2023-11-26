package com.mad.g1.nguyentuanminh.chessgame.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mad.g1.nguyentuanminh.chessgame.R;

public class MainActivity extends AppCompatActivity {
    private Button startBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startBTN= findViewById(R.id.startbtn);
        startBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PlayActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}