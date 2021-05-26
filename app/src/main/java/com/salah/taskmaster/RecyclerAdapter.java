package com.salah.taskmaster;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<Task> tasks;
    private Activity context;
    private RoomDB database;
//    Context context;


    public RecyclerAdapter(List<Task> tasks, Activity context) {
        this.tasks = tasks;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cutome_design, parent, false);
//        View view = layoutInflater.inflate();
//        ViewHolder viewHolder = new ViewHolder(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull RecyclerAdapter.ViewHolder holder, int position) {
        // initialize tasks data
        Task data = tasks.get(position);
        // initialize database
        database = RoomDB.getInstance(context);
        // set text on text view
        holder.textView.setText(data.getTitle());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                // initialize Task data
                Task d = tasks.get(holder.getAbsoluteAdapterPosition());
                // Get id
                int sId = d.getId();
                // Get Title
                String sTitle = d.getTitle();
                // create dialog
//                Dialog dialog = new Dialog(context);
                // Set content view ???
//                dialog.setContentView(R.layout.cutome_design);

                Toast.makeText(context, "Going to ==>" + sTitle, Toast.LENGTH_SHORT).show();
                Intent goToDetail = new Intent(context, TaskDetail.class);
                goToDetail.putExtra("detailTitle", sTitle);
                goToDetail.putExtra("taskId", sId);
                context.startActivity(goToDetail);
//                SharedPreferences sharedPreferences = context.getSharedPreferences("myTask", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("task", data[position]);
//                editor.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.task_recycler_view);
        }
    }
}
