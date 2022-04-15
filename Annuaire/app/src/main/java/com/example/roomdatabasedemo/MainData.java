package com.example.roomdatabasedemo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName ="table_name")

public class MainData implements Serializable {

    //Create id column
    @PrimaryKey(autoGenerate = true)
    private int ID;

    // Create text column
    @ColumnInfo(name="first_name")
    private String first_name;
    @ColumnInfo(name="last_name")
    private String last_name;
    @ColumnInfo(name="job")
    private String job;
    @ColumnInfo(name="phone")
    private String phone;
    @ColumnInfo(name="email")
    private String email;

    //Generate getters and setters


    public void setID(int ID) {
        this.ID = ID;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getID() {
        return ID;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getJob() {
        return job;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
