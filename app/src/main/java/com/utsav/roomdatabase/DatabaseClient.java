package com.utsav.roomdatabase;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {
    private Context context;
    private static DatabaseClient databaseClient;
    private AppDatabase appDatabase;

    public DatabaseClient(Context context) {
        this.context = context;
        //creating the app database with Room database builder
        //MyToDos is the name of the database
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "MyToDos").build();
    }

    public static synchronized DatabaseClient getInstance(Context context){
        if (databaseClient == null)
            databaseClient = new DatabaseClient(context);
        return databaseClient;
    }

    public AppDatabase getAppDatabase(){
        return appDatabase;
    }
}
