package com.hjc.base.bean.db;

import android.content.Context;

import androidx.room.Room;

public class DBInstance {
    private static final String DB_NAME = "room_test";
    public static AppDataBase appDataBase;

    public static AppDataBase getInstance(Context context) {
        if (appDataBase == null) {
            synchronized (DBInstance.class) {
                if (appDataBase == null) {
                    appDataBase = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, DB_NAME)
                            //表示允许主线程进行数据库操作，但是不推荐这样做。
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return appDataBase;
    }
}
