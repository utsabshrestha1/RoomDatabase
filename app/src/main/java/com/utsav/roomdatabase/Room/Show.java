package com.utsav.roomdatabase.Room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.utsav.roomdatabase.R;
import com.utsav.roomdatabase.Room.DatabaseClient.DatabaseClient;
import com.utsav.roomdatabase.Room.Model.ModelEntity;
import com.utsav.roomdatabase.Room.RecyclerViewAdapter.ModelEntityAdapterRecyclerView;

import java.util.List;

public class Show extends AppCompatActivity {
    FloatingActionButton floatingButtonAddRoom;
    RecyclerView recyclerviewTasksRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        
        floatingButtonAddRoom = findViewById(R.id.floating_button_addRoom);
        recyclerviewTasksRoom = findViewById(R.id.recyclerview_tasksRoom);
        
        floatingButtonAddRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Show.this, Add.class));
            }
        });
        getData();
        
    }

    private void getData() {
        class GetData extends AsyncTask<Void, Void, List<ModelEntity>>{

            @Override
            protected List<ModelEntity> doInBackground(Void... voids) {
                List<ModelEntity> modelEntities = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().modelDao().getAllData();
                return modelEntities;
            }

            @Override
            protected void onPostExecute(List<ModelEntity> modelEntities) {
                super.onPostExecute(modelEntities);
                ModelEntityAdapterRecyclerView adapterRecyclerView = new ModelEntityAdapterRecyclerView(Show.this, modelEntities);
                recyclerviewTasksRoom.setAdapter(adapterRecyclerView);
            }
        }

        GetData getData = new GetData();
        getData.execute();
    }
}