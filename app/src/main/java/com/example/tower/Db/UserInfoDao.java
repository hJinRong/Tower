package com.example.tower.Db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserInfoDao {

    @Query("SELECT * FROM userinfo")
    UserInfo getAll();

    @Insert
    void insertOne(UserInfo userInfo);
}
