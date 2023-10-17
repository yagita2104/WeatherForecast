package com.example.weatherforecast.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherforecast.R;
import com.example.weatherforecast.model.Hour;
import com.example.weatherforecast.model.WeatherData;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CurrentFRAdapter extends RecyclerView.Adapter<CurrentFRAdapter.ViewHolder> {
    Context context;
    List list;
    public CurrentFRAdapter(Context context, List list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CurrentFRAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_weather_today, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrentFRAdapter.ViewHolder holder, int position) {
        Hour hour = (Hour) list.get(position);
        holder.txtTempFR1.setText(hour.getTemp_c() + "°C");
        String imageUrl = "https:" + hour.getCondition().getIcon();
        Picasso.get().load(imageUrl).into(holder.imgWeatherFR1);

        //Chỉnh sửa định dạng ngày
        String inputDate = hour.getTime();
        String outputFormat = "HH:mm";

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
        DateFormat outputDateFormat = new SimpleDateFormat(outputFormat, Locale.ENGLISH);

        try {
            Date date = inputFormat.parse(inputDate);
            String outputDate = outputDateFormat.format(date);
            holder.txtTimeFR1.setText(outputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if(list == null){
            return 0;
        }
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTempFR1, txtTimeFR1;
        ImageView imgWeatherFR1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTempFR1 = itemView.findViewById(R.id.txtTempFR1);
            txtTimeFR1 = itemView.findViewById(R.id.txtTimeFR1);
            imgWeatherFR1 = itemView.findViewById(R.id.imgWeatherFR1);
        }
    }
}
