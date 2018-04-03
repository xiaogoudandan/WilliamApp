package com.william_zhang.williamapp.widget;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.KeyboardUtils;
import com.william_zhang.williamapp.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by william_zhang on 2018/4/2.
 */

public class KeyBoardEditText extends AppCompatEditText {
    private Context context;
    private Keyboard keyboradNumber;
    private Keyboard keyboradLetter;
    private KeyboardView keyboardView;
    private ViewGroup viewGroup;

    OnKeyboardStateChangeListener listener = null;

    public void setOnKeyboardStateListener(OnKeyboardStateChangeListener listener) {
        this.listener = listener;
    }

    public KeyBoardEditText(Context context) {
        this(context, null);
    }

    public KeyBoardEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KeyBoardEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    /**
     * 键盘对象
     */
    private void initView() {
        keyboradLetter = new Keyboard(context, R.xml.keyboard_letter);
        keyboradNumber = new Keyboard(context, R.xml.keyboard_number);
    }

    public void setKeyboardType(final ViewGroup viewGroup, final KeyboardView keyboardView, boolean number) {
        this.keyboardView = keyboardView;
        this.viewGroup = viewGroup;
        if (number) {
            keyboardView.setKeyboard(keyboradNumber);
        }
        keyboardView.setPreviewEnabled(true);
        keyboardView.setOnKeyboardActionListener(new KeyboardView.OnKeyboardActionListener() {
            @Override
            public void onPress(int i) {
                conShowPreview(i);
            }

            @Override
            public void onRelease(int i) {

            }

            @Override
            public void onKey(int primaryCode, int[] keyCodes) {
                Editable editable = getEditableText();
                int start = getSelectionStart();
                // 删除功能
                if (primaryCode == Keyboard.KEYCODE_DELETE) {
                    if (!editable.toString().isEmpty() && start > 0) {
                        editable.delete(start - 1, start);
                    }
                }
                // 字母键盘与数字键盘切换
                else if (primaryCode == Keyboard.KEYCODE_MODE_CHANGE) {
                    changeKeyBoard(!changeLetter);
                }
                // 完成
                else if (primaryCode == Keyboard.KEYCODE_DONE) {
                    keyboardView.setVisibility(View.GONE);
                    viewGroup.setVisibility(View.GONE);
                    if (listener != null)
                        listener.hide();
                }
                // 切换大小写
                else if (primaryCode == Keyboard.KEYCODE_SHIFT) {
                    changeCapital(!isCapital);
                    keyboardView.setKeyboard(keyboradLetter);
                } else {
                    editable.insert(start, Character.toString((char) primaryCode));
                }
            }

            @Override
            public void onText(CharSequence charSequence) {

            }

            @Override
            public void swipeLeft() {

            }

            @Override
            public void swipeRight() {

            }

            @Override
            public void swipeDown() {

            }

            @Override
            public void swipeUp() {

            }
        });
    }

    // 是否发生键盘切换
    boolean changeLetter = false;

    // 是否为大写字母
    boolean isCapital = false;

    private void changeKeyBoard(Boolean letter) {
        changeLetter = letter;
        if (changeLetter) {
            keyboardView.setKeyboard(keyboradLetter);
        } else {
            keyboardView.setKeyboard(keyboradNumber);
        }
    }

    /**
     * 改变大小写
     *
     * @param isCapital
     */
    private void changeCapital(Boolean isCapital) {
        String lowercase = "abcdefghijklmnopqrstuvwxyz";

        List<Keyboard.Key> keyList = keyboradLetter.getKeys();
        for (Keyboard.Key it : keyList) {
            if (it.label != null && lowercase.indexOf(it.label.toString().toLowerCase()) != -1) {
                if (isCapital) {
                    it.label = it.label.toString().toUpperCase();
                    it.codes[0] -= 32;
                } else {
                    it.label = it.label.toString().toLowerCase();
                    it.codes[0] += 32;
                }
            }
            if (it.label != null && it.label.equals("小写") && isCapital) {
                it.label = "大写";
            } else if (it.label != null && it.label.equals("大写") && !isCapital) {
                it.label = "小写";
            }
        }
        this.isCapital = isCapital;
    }

    private void conShowPreview(int i) {
        int[] list = {Keyboard.KEYCODE_SHIFT, Keyboard.KEYCODE_MODE_CHANGE, Keyboard.KEYCODE_CANCEL,
                Keyboard.KEYCODE_DONE, Keyboard.KEYCODE_DELETE, Keyboard.KEYCODE_ALT, 32};
        List arrayList = Arrays.asList(list);
        keyboardView.setPreviewEnabled(arrayList.contains(i));
    }

    /**
     *点击输入框
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        KeyboardUtils.hideSoftInput(this);
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (keyboardView.getVisibility() != View.VISIBLE) {
                keyboardView.setVisibility(View.VISIBLE);
                viewGroup.setVisibility(View.VISIBLE);
                if (listener != null)
                    listener.show();
            }
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && keyboardView.getVisibility() != View.GONE
                && viewGroup.getVisibility() != View.GONE) {
            keyboardView.setVisibility(View.GONE);
            viewGroup.setVisibility(View.GONE);
            if (listener != null)
                listener.hide();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        KeyboardUtils.hideSoftInput(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        KeyboardUtils.hideSoftInput(this);
    }

    public interface OnKeyboardStateChangeListener {
        void show();

        void hide();
    }
}
