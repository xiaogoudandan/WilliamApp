package com.william_zhang.williamapp.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.william_zhang.williamapp.R;
import com.william_zhang.williamapp.bean.Book;
import com.william_zhang.williamapp.bean.Dog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by william_zhang on 2018/3/2.
 */

public class SerializationActivity_A extends AppCompatActivity {
    @BindView(R.id.et_book)
    EditText etBook;
    @BindView(R.id.et_dog)
    EditText etDog;

    @OnClick(R.id.btn_submit)
    void submit() {
        String et_text = etBook.getText().toString();
        String et_dog = etDog.getText().toString();
        if (!et_text.equals("") && !et_dog.equals("")) {
            Intent intent = new Intent(SerializationActivity_A.this, SerializationActivity_B.class);
            intent.putExtra("book", new Book("01", et_text));
            intent.putExtra("dog", new Dog(et_dog));
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serialication);
        ButterKnife.bind(this);
    }
}
