package com.example.dynamic_tabs;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class book_act extends AppCompatActivity {

    private TextView title,des,cat;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_act);

        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Title");
        String Des = intent.getExtras().getString("Description");
        String Cat = intent.getExtras().getString("Category");
        int Thumbnail = intent.getExtras().getInt("Thumbnail");

        title = (TextView)findViewById(R.id.booktitle);
        des = (TextView)findViewById(R.id.des);
        cat = (TextView)findViewById(R.id.cat);
        imageView = (ImageView)findViewById(R.id.bookimg);

        title.setText(Title);
        des.setText(Des);
        cat.setText(Cat);
        imageView.setImageResource(Thumbnail);

    }
}
