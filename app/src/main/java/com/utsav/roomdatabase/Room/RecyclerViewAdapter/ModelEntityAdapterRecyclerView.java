package com.utsav.roomdatabase.Room.RecyclerViewAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.utsav.roomdatabase.R;
import com.utsav.roomdatabase.Room.Model.ModelEntity;
import com.utsav.roomdatabase.Room.Update;

import java.util.List;

public class ModelEntityAdapterRecyclerView extends RecyclerView.Adapter<ModelEntityAdapterRecyclerView.ViewHOlder> {
    private Context context;
    private List<ModelEntity> modelEntities;

    public ModelEntityAdapterRecyclerView(Context context, List<ModelEntity> modelEntities) {
        this.context = context;
        this.modelEntities = modelEntities;
    }

    @NonNull
    @Override
    public ViewHOlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_tasks, parent, false);
        return new ViewHOlder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHOlder holder, int position) {
        ModelEntity modelEntity = modelEntities.get(position);

        holder.textViewDesc.setText(modelEntity.getDesc());
        holder.textViewTask.setText(modelEntity.getTask());
        holder.textViewFinishBy.setText(modelEntity.getFinishBy());

        if (modelEntity.getFinished()){
            holder.textViewStatus.setText("Completed");
        }else {
            holder.textViewStatus.setText("Is yet to be Completed");
        }

    }

    @Override
    public int getItemCount() {
        return modelEntities.size();
    }

    public class ViewHOlder extends RecyclerView.ViewHolder {
        TextView textViewStatus, textViewTask, textViewDesc, textViewFinishBy;

        public ViewHOlder(@NonNull View itemView) {
            super(itemView);
            textViewStatus = itemView.findViewById(R.id.textViewStatus);
            textViewTask = itemView.findViewById(R.id.textViewTask);
            textViewDesc = itemView.findViewById(R.id.textViewDesc);
            textViewFinishBy = itemView.findViewById(R.id.textViewFinishBy);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ModelEntity modelEntity = modelEntities.get(getAdapterPosition());

                    Intent intent = new Intent(context, Update.class);
                    intent.putExtra("data", modelEntity);

                    context.startActivity(intent);
                }
            });
        }
    }
}
