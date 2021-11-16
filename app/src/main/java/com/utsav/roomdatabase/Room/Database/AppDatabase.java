package com.utsav.roomdatabase.Room.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.utsav.roomdatabase.Room.Interface.ModelDao;
import com.utsav.roomdatabase.Room.Model.ModelEntity;

@Database (entities = {ModelEntity.class}, version =1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ModelDao modelDao();
}
