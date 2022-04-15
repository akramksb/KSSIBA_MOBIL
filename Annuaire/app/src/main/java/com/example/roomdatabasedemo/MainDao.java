package com.example.roomdatabasedemo;
import android.app.UiAutomation;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

 @Dao
public interface MainDao {

    // Insert query
    @Insert(onConflict =REPLACE)
    void insert(MainData mainData);
     // Delete query

     @Delete
     void reset(List<MainData> mainData);
     //to do

     // Update query
//     @Query("UPDATE table_name SET first_name= :first_name, last_name= :last_name,job =:job, phone =:phone, email=:email  where ID=:sID")
//     void update(int sID, String first_name, String last_name, String job, String phone, String email );

     @Update
     void update( MainData mainData );

     @Delete
     void delete( MainData mainData );

     // Get all data query
     @Query("SELECT * FROM table_name")
     List<MainData> getAll();


}
