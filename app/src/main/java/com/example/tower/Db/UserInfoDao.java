package com.example.tower.Db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserInfoDao {

    @Query("SELECT * FROM userinfo")
    UserInfo getAll();

    @Insert
    void insertOne(UserInfo userInfo);

    @Update
    void updateOne(UserInfo userInfo);

    @Delete
    void deleteAll(UserInfo userInfo);
}
