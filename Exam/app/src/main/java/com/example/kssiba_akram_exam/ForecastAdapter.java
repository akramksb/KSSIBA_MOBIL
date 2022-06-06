package com.example.kssiba_akram_exam;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder> {

    private Activity context;
    private List<ForecastData> dataList;

    public ForecastAdapter(Activity context, List<ForecastData> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ForecastAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_col_meteo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastAdapter.ViewHolder holder, int position) {
        final ForecastData data = dataList.get(position);
        holder.date.setText(String.valueOf(data.getDate()) );
        holder.temp.setText(data.getTemp() +"°C" );
        holder.meteo.setText(data.getMeteo());

        Picasso.get().load(data.getIcon()).into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView temp, date, meteo;
        ImageView icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.meteo_list_date);
            temp =itemView.findViewById(R.id.meteo_list_temp);
            icon = itemView.findViewById(R.id.imageView);
            meteo = itemView.findViewById(R.id.meteo_list_meteo);
        }
    }
}
