package com.william_zhang.williamapp.mvp.activity;

import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.william_zhang.williamapp.R;
import com.william_zhang.williamapp.widget.KeyBoardEditText;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by william_zhang on 2018/4/3.
 */

public class KeyBoardActivity extends AppCompatActivity {
    KeyboardView keyboardView;
    @BindView(R.id.ed_main)
    KeyBoardEditText edMain;
    @BindView(R.id.layout_root)
    LinearLayout layoutRoot;
    @BindView(R.id.view_keyboard)
    KeyboardView viewKeyboard;
    @BindView(R.id.layout_main)
    LinearLayout layoutMain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);
        ButterKnife.bind(this);
        Utils.init(this);
        edMain.setKeyboardType(layoutMain, viewKeyboard, true);
        edMain.setOnKeyboardStateListener(new KeyBoardEditText.OnKeyboardStateChangeListener() {
            int height=0;
            @Override
            public void show() {
                edMain.post(new Runnable() {
                    @Override
                    public void run() {
                        int[] rect = new int[2];
                        edMain.getLocationOnScreen(rect);
                        int y = rect[1];
                        viewKeyboard.getLocationOnScreen(rect);
                        int key_y = rect[1];
                        height = y - (key_y - edMain.getHeight());
                        layoutRoot.scrollBy(0, height);
                    }
                });
                ToastUtils.showShort("show");
            }

            @Override
            public void hide() {
                ToastUtils.showShort("hide");
                layoutRoot.scrollBy(0, -height);
            }
        });
    }
}
