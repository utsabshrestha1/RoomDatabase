package com.utsav.roomdatabase.Room.DatabaseClient;

import android.content.Context;

import androidx.room.Room;

import com.utsav.roomdatabase.Room.Database.AppDatabase;

public class DatabaseClient {
    private static DatabaseClient databaseClient;
    private Context context;
    private AppDatabase appDatabase;

    public DatabaseClient(Context context) {
        this.context = context;

        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "Data").build();
    }

    public static synchronized DatabaseClient getInstance(Context context){
        if ( databaseClient == null)
            databaseClient = new DatabaseClient(context);

        return databaseClient;
    }

    public AppDatabase getAppDatabase(){
        return appDatabase;
    }
}
