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
import com.example.weatherforecast.model.Day;
import com.example.weatherforecast.model.ForecastDay;
import com.example.weatherforecast.model.Hour;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ForecastFRAdapter extends RecyclerView.Adapter<ForecastFRAdapter.ViewHolder>{
    Context context;
    List list;
    public ForecastFRAdapter(Context context, List list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ForecastFRAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_weather_forecast, parent, false);
        return new ForecastFRAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ForecastDay forecastDay = (ForecastDay) list.get(position);
        Day day = forecastDay.getDay();
        holder.txtTempFR2.setText(day.getAvgtemp_c() + "°C");
        String imageUrl = "https:" + day.getCondition().getIcon();
        Picasso.get().load(imageUrl).into(holder.imgWeatherFR2);

        //Chỉnh sửa định dạng ngày
        String inputDate = forecastDay.getDate();
        String outputFormat = "MMM, dd";

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        DateFormat outputDateFormat = new SimpleDateFormat(outputFormat, Locale.ENGLISH);

        try {
            Date date = inputFormat.parse(inputDate);
            String outputDate = outputDateFormat.format(date);
            holder.txtTimeFR2.setText(outputDate);
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
        TextView txtTempFR2, txtTimeFR2;
        ImageView imgWeatherFR2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTempFR2 = itemView.findViewById(R.id.txtTempFR2);
            txtTimeFR2 = itemView.findViewById(R.id.txtTimeFR2);
            imgWeatherFR2 = itemView.findViewById(R.id.imgWeatherFR2);
        }
    }

}
