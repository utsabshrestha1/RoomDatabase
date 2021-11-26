package com.utsav.roomdatabase.Room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.utsav.roomdatabase.AddTaskActivity;
import com.utsav.roomdatabase.CompletedTasks;
import com.utsav.roomdatabase.DatabaseClient;
import com.utsav.roomdatabase.R;
import com.utsav.roomdatabase.Task;
import com.utsav.roomdatabase.TaskAdapterRecyclerView;

import java.util.List;

public class Completed extends AppCompatActivity {
    FloatingActionButton buttonAddTask;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed);

        recyclerView = findViewById(R.id.recyclerview_tasksCompleted);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //hello world

        buttonAddTask = findViewById(R.id.floating_button_addCompleted);
        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Completed.this, AddTaskActivity.class));
            }
        });

        getTasks();
    }

    private void getTasks() {
        class GetTasks extends AsyncTask<Void, Void, List<Task>> {

            @Override
            protected List<Task> doInBackground(Void... voids) {
                List<Task> taskList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .taskDao()
                        .getCompletedTask();
                return taskList;
            }

            @Override
            protected void onPostExecute(List<Task> tasks){
                super.onPostExecute(tasks);
                TaskAdapterRecyclerView adapter = new TaskAdapterRecyclerView(Completed.this, tasks);
                recyclerView.setAdapter(adapter);
            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }
}