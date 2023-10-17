package com.example.weatherforecast.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherforecast.R;
import com.example.weatherforecast.api.ApiService;
import com.example.weatherforecast.model.WeatherData;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherCurrent extends AppCompatActivity{
    TextView txtCityName, txtDateCurrent, txtTempCurrent, txtWeatherCurrent, txtWindCurrent, txtHumidityCurrent;
    ImageView imgLocation, imgNoti, imgWeatherCurrent;
    Button btnForecastReport;
    public String cityName = "Ha Noi";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_current);
        getWidget();
        getWeatherDataFromApi(cityName);
        addAction();
    }
    private void addAction() {
        imgLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSearch(Gravity.TOP);
            }
        });
        btnForecastReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WeatherCurrent.this, ForecastReport.class);
                intent.putExtra("cityname", cityName);
                startActivity(intent);
            }
        });
    }
    private void openSearch(int gravity){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.search_current);

        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        if(Gravity.TOP == gravity){
            dialog.setCancelable(true);
        }else {
            dialog.setCancelable(false);
        }
        dialog.show();
        ImageView imgIconBackSearch = dialog.findViewById(R.id.imgIconBackSearch);
        EditText edtSearchCity = dialog.findViewById(R.id.edtSearchCity);
        ImageView imgIconSearch = dialog.findViewById(R.id.imgIconSearch);

        imgIconBackSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        imgIconSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = edtSearchCity.getText().toString();
                cityName = city;
                getWeatherDataFromApi(city);
                dialog.dismiss();
            }
        });
    }
    private void getWidget() {
        //TextView
        txtCityName = findViewById(R.id.txtCityName);
        txtDateCurrent = findViewById(R.id.txtDateCurrent);
        txtTempCurrent = findViewById(R.id.txtTempCurrent);
        txtWeatherCurrent = findViewById(R.id.txtWeatherCurrent);
        txtWindCurrent = findViewById(R.id.txtWindCurrent);
        txtHumidityCurrent = findViewById(R.id.txtHumidityCurrent);
        //Image
        imgLocation = findViewById(R.id.imgLocation);
        imgNoti = findViewById(R.id.imgNoti);
        imgWeatherCurrent = findViewById(R.id.imgWeatherCurrent);
        //Button
        btnForecastReport = findViewById(R.id.btnForecastReport);
    }
    private void setEveryThing(WeatherData weatherData){
        txtCityName.setText(weatherData.getLocation().getName());
        //Chỉnh sửa định dạng ngày
        String inputDate = weatherData.getCurrent().getLast_update();
        System.out.println(inputDate);
        String outputFormat = "dd MMMM";

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
        DateFormat outputDateFormat = new SimpleDateFormat(outputFormat, Locale.ENGLISH);

        try {
            Date date = inputFormat.parse(inputDate);
            String outputDate = outputDateFormat.format(date);
            txtDateCurrent.setText("Today, " + outputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        txtTempCurrent.setText(weatherData.getCurrent().getTemp_c() + "°");
        txtWeatherCurrent.setText(weatherData.getCurrent().getCondition().getText());
        txtWindCurrent.setText(weatherData.getCurrent().getWind_kph() + " km/h");
        txtHumidityCurrent.setText(weatherData.getCurrent().getHumidity() + "%");
        //
        String imageUrl = "https:" + weatherData.getCurrent().getCondition().getIcon();
        Picasso.get().load(imageUrl).into(imgWeatherCurrent);
    }
    private void getWeatherDataFromApi(String cityName){
        ApiService.apiService.weatherData("9946e28297874f61a90112012231203", cityName, 7, "yes", "no").enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                WeatherData weatherData = response.body();
                if(weatherData != null){
                    setEveryThing(weatherData);
                }
            }
            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                Toast.makeText(WeatherCurrent.this, "Không lấy được dữ liệu", Toast.LENGTH_SHORT).show();
            }
        });
    }
}