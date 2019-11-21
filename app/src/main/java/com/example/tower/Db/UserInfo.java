package com.example.tower.Db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserInfo {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "account")
    public String account = "";

    @ColumnInfo(name = "name")
    public String name = "";

    @ColumnInfo(name = "no")
    public String no = "";

    @ColumnInfo(name = "gender")
    public String gender = "";

    @ColumnInfo(name = "eduStartDay")
    public String eduStartDay = "";

    @ColumnInfo(name = "faculty")
    public String faculty = "";

    @ColumnInfo(name = "major")
    public String major = "";

    @ColumnInfo(name = "dormitory")
    public String dormitory = "";
}
