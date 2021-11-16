package com.utsav.roomdatabase.Room;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.utsav.roomdatabase.R;
import com.utsav.roomdatabase.Room.DatabaseClient.DatabaseClient;
import com.utsav.roomdatabase.Room.Model.ModelEntity;

public class Update extends AppCompatActivity {
    private EditText editTextTaskRoom, editTextDescRoom, editTextFinishByRoom;
    private CheckBox checkBoxFinishedRoom;
    private Button buttonUpdateRoom, buttonDeleteRoom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        editTextTaskRoom = findViewById(R.id.editTextTaskRoom2);
        editTextDescRoom  = findViewById(R.id.editTextDescRoom2);
        editTextFinishByRoom = findViewById(R.id.editTextFinishByRoom2);

        checkBoxFinishedRoom = findViewById(R.id.checkBoxFinishedRoom);
        buttonUpdateRoom = findViewById(R.id.button_updateRoom);
        buttonDeleteRoom = findViewById(R.id.button_deleteRoom);

        final ModelEntity modelEntity = (ModelEntity) getIntent().getSerializableExtra("task");
        loadData(modelEntity);

        buttonUpdateRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Update.this, "Updating", Toast.LENGTH_SHORT).show();
                updateData(modelEntity);
            }
        });

        buttonDeleteRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Update.this);
                builder.setTitle("You wanna delete this task?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteData(modelEntity);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    private void deleteData(ModelEntity modelEntity) {

        class DeleteData extends AsyncTask<Void, Void, Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().modelDao().delete(modelEntity);
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                Toast.makeText(Update.this, "Deleted", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(Update.this, Show.class));
            }
        }

        DeleteData deleteData = new DeleteData();
        deleteData.execute();
    }

    private void updateData(ModelEntity modelEntity) {
        final String task = editTextTaskRoom.getText().toString().trim();
        final String desc = editTextDescRoom.getText().toString().trim();
        final String finishBy = editTextFinishByRoom.getText().toString().trim();

        if (task.isEmpty()){
            editTextTaskRoom.setError("Add some Task");
            editTextTaskRoom.requestFocus();
            return;
        }

        if (desc.isEmpty()){
            editTextDescRoom.setError("Add some Descriptions");
            editTextDescRoom.requestFocus();
            return;
        }

        if (finishBy.isEmpty()){
            editTextFinishByRoom.setError("When should it be finished?");
            editTextFinishByRoom.requestFocus();
            return;
        }
        class UpDateData extends AsyncTask<Void, Void, Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                modelEntity.setTask(task);
                modelEntity.setDesc(desc);
                modelEntity.setFinishBy(finishBy);
                modelEntity.setFinished(checkBoxFinishedRoom.isChecked());
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().modelDao().update(modelEntity);
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                Toast.makeText(Update.this, "Updated", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(Update.this, Show.class));
            }
        }

        UpDateData upDateTask = new UpDateData();
        upDateTask.execute();
    }

    private void loadData(ModelEntity modelEntity) {
        editTextTaskRoom.setText(modelEntity.getTask());
        editTextDescRoom.setText(modelEntity.getDesc());
        editTextFinishByRoom.setText(modelEntity.getFinishBy());
        checkBoxFinishedRoom.setChecked(modelEntity.getFinished());
    }
}