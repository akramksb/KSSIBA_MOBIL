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

     @Update
     void update( MainData mainData );

     @Delete
     void delete( MainData mainData );

     // Get all data query
     @Query("SELECT * FROM table_name")
     List<MainData> getAll();

     @Query( "SELECT * from table_name where ID = :id" )
     MainData findByID( int id );

     @Query("SELECT * FROM table_name where first_name like :key or last_name like :key ")
     List<MainData> findByKeyword( String key );

}
