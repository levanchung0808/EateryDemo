package com.example.eaterydemo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;

import com.example.eaterydemo.R;

public class ProfileActivity extends AppCompatActivity {

    CardView card, monan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_thongtin);


        setContentView(R.layout.item_monan);
    }
}