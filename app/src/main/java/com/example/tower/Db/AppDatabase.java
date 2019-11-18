package com.example.tower.Db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {UserInfo.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserInfoDao userInfoDao();
}
