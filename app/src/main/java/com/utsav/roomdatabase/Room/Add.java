package com.utsav.roomdatabase.Room;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.utsav.roomdatabase.MainActivity;
import com.utsav.roomdatabase.R;
import com.utsav.roomdatabase.Room.DatabaseClient.DatabaseClient;
import com.utsav.roomdatabase.Room.Model.ModelEntity;

public class Add extends AppCompatActivity {
    EditText editTextTaskRoom, editTextDescRoom, editTextFinishByRoom;
    Button buttonSaveRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        editTextTaskRoom = findViewById(R.id.editTextTaskRoom);
        editTextDescRoom = findViewById(R.id.editTextDescRoom);
        editTextFinishByRoom = findViewById(R.id.editTextFinishByRoom);

        buttonSaveRoom = findViewById(R.id.button_saveRoom);

        buttonSaveRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        });
    }

    private void add() {
        final String task = editTextTaskRoom.getText().toString().trim();
        final String desc = editTextDescRoom.getText().toString().trim();
        final String finishBy = editTextFinishByRoom.getText().toString().trim();

        if (task.isEmpty()){
            editTextTaskRoom.setError("Add some Task");
            editTextTaskRoom.requestFocus();
            return;
        }

        if (desc.isEmpty()){
            editTextDescRoom.setError("Add the description please.");
            editTextDescRoom.requestFocus();
            return;
        }

        if (finishBy.isEmpty()){
            editTextFinishByRoom.setError("Add when the task should be finished.");
            editTextFinishByRoom.requestFocus();
            return;
        }

        class SaveTask extends AsyncTask<Void, Void, Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                ModelEntity modelEntity = new ModelEntity();
                modelEntity.setTask(task);
                modelEntity.setDesc(desc);
                modelEntity.setFinishBy(finishBy);
                modelEntity.setFinished(false);

                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().modelDao().insert(modelEntity);
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                finish();
                startActivity(new Intent(getApplicationContext(), Show.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveTask saveTask = new SaveTask();
        saveTask.execute();

    }
}