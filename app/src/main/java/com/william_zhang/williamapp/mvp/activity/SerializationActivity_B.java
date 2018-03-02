package com.william_zhang.williamapp.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.william_zhang.williamapp.R;
import com.william_zhang.williamapp.bean.Book;
import com.william_zhang.williamapp.bean.Dog;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by william_zhang on 2018/3/2.
 */

public class SerializationActivity_B extends AppCompatActivity {
    @BindView(R.id.tv_book)
    TextView tvBook;
    @BindView(R.id.tv_dog)
    TextView tvDog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serialication_b);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Book book = (Book) intent.getSerializableExtra("book");
        Dog dog = (Dog) intent.getParcelableExtra("dog");
        tvBook.setText(book.getBookName());
        tvDog.setText(dog.getName());
    }
}
