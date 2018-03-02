package com.william_zhang.williamapp.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by william_zhang on 2018/3/2.
 */

public class Dog implements Parcelable {
    private String name;

    public Dog(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;// 内容接口描述，默认返回0即可。
    }
    //序列化使用
    @Override
        public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
    }
    //在Parcelable.Creator中 反序列化中使用
    protected Dog(Parcel in) {
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Dog> CREATOR = new Parcelable.Creator<Dog>() {
        @Override
        public Dog createFromParcel(Parcel source) {
            return new Dog(source);// 在构造函数里面完成了 读取 的工作
        }
        //供反序列化本类数组时调用的
        @Override
        public Dog[] newArray(int size) {
            return new Dog[size];
        }
    };
}
