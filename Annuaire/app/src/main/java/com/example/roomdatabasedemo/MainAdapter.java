package com.example.roomdatabasedemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    // Initialize variable
    private List<MainData> dataList;
    private Activity context;
    private RoomDB database;

    //Create constructor
    public MainAdapter(Activity context, List<MainData> dataList) {
        this.context = context;
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Initialize view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_main, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MainAdapter.ViewHolder holder, int position) {

        // Initialize main data
        final MainData data = dataList.get(position);

        // Initialize database
        database = RoomDB.getInstance(context);

        // Set text on text view
        holder.textFullName.setText(data.getFirst_name() + " " + data.getLast_name());
        holder.textJob.setText(data.getJob());
        holder.textPhone.setText(data.getPhone());
        holder.textEmail.setText(data.getEmail());

        holder.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainData d = dataList.get(holder.getAdapterPosition());
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + d.getPhone()));
                view.getContext().startActivity(intent);

            }
        });

        holder.listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainData d = dataList.get(holder.getAdapterPosition());

                Intent intent = new Intent(view.getContext() , EditActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", d.getID());

                intent.putExtras(bundle);
                view.getContext().startActivity(intent);
            }
        });

        holder.listItem.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View view) {
               AlertDialog.Builder builder = new AlertDialog.Builder(context);
               builder.setCancelable(true);
               builder.setTitle("Delete");
               builder.setMessage("Are you sure want to delete this contact?");
               builder.setPositiveButton("Confirm",
                       new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               database.mainDao().delete( data );
                               dataList.remove( holder.getAdapterPosition() );
                               notifyDataSetChanged();
                           }
                       });
               builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                   }
               });

               AlertDialog dialog = builder.create();
               dialog.show();

               return false;
           }
       });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //Initialize variable
        TextView textFullName;
        TextView textJob;
        TextView textPhone;
        TextView textEmail;
        LinearLayout listItem;

        ImageView btnCall;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Assign variable

            textFullName = itemView.findViewById(R.id.textFullName);
            textJob = itemView.findViewById(R.id.textJob);
            textPhone = itemView.findViewById(R.id.textPhone);
            textEmail = itemView.findViewById(R.id.textEmail);
            btnCall = itemView.findViewById(R.id.btnCall);
            listItem = itemView.findViewById(R.id.listItem);
        }
    }
}
