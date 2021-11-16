package com.utsav.roomdatabase.Room.Interface;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.utsav.roomdatabase.Room.Model.ModelEntity;

import java.util.List;

@Dao
public interface ModelDao {
    @Query("SELECT * FROM ModelEntity")
    List<ModelEntity> getAllData();

    @Insert
    void insert(ModelEntity modelEntity);

    @Delete
    void delete(ModelEntity modelEntity);

    @Update
    void update(ModelEntity modelEntity);
}
