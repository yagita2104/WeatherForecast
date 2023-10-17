package com.example.weatherforecast.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.HurlStack;
import com.example.weatherforecast.R;
import com.example.weatherforecast.adapter.CurrentFRAdapter;
import com.example.weatherforecast.adapter.ForecastFRAdapter;
import com.example.weatherforecast.api.ApiService;
import com.example.weatherforecast.model.ForecastDay;
import com.example.weatherforecast.model.Hour;
import com.example.weatherforecast.model.WeatherData;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForecastReport extends AppCompatActivity {
    TextView txtDateFR;
    ImageView imgBackFR;
    List<Hour> hourList= new ArrayList<>();
    CurrentFRAdapter currentFRAdapter;
    RecyclerView rvWeatherTodayFR;

    List<ForecastDay>forecastDayList = new ArrayList<>();
    ForecastFRAdapter forecastFRAdapter;
    RecyclerView rvNextForecastFR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forecast_report);
        getWidget();
        addAction();
        Intent intent = getIntent();
        getWeatherDataFromApi(intent.getStringExtra("cityname"));
    }

    private void addAction() {
        imgBackFR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void getWidget(){
        rvWeatherTodayFR = findViewById(R.id.rvWeatherTodayFR);
        rvNextForecastFR = findViewById(R.id.rvNextForecastFR);
        txtDateFR = findViewById(R.id.txtDateFR);
        imgBackFR = findViewById(R.id.imgBackFR);

    }
    private void getWeatherDataFromApi(String cityName){
        ApiService.apiService.weatherData("9946e28297874f61a90112012231203", cityName, 10, "yes", "no").enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                WeatherData weatherData = response.body();
                if(weatherData != null){
                    //
                    String inputDate = weatherData.getCurrent().getLast_update();
                    System.out.println(inputDate);
                    String outputFormat = "MMM, dd";
                    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
                    DateFormat outputDateFormat = new SimpleDateFormat(outputFormat, Locale.ENGLISH);
                    try {
                        Date date = inputFormat.parse(inputDate);
                        String outputDate = outputDateFormat.format(date);
                        txtDateFR.setText(outputDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    hourList = weatherData.getForecast().getForecastday().get(0).getHour();
                    forecastDayList = weatherData.getForecast().getForecastday();

                    currentFRAdapter = new CurrentFRAdapter(ForecastReport.this, hourList);
                    rvWeatherTodayFR.setAdapter(currentFRAdapter);
                    rvWeatherTodayFR.setLayoutManager(new LinearLayoutManager(ForecastReport.this, LinearLayoutManager.HORIZONTAL, false));

                    forecastFRAdapter = new ForecastFRAdapter(ForecastReport.this, forecastDayList);
                    rvNextForecastFR.setAdapter(forecastFRAdapter);
                    rvNextForecastFR.setLayoutManager(new LinearLayoutManager(ForecastReport.this, LinearLayoutManager.VERTICAL, false));
                }
            }
            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                Log.e("Error", "Get weather from API false");
            }
        });
    }
}