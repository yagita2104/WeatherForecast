package com.example.weatherforecast.model;

import java.util.List;

public class Forecast {
    private List<ForecastDay> forecastday;

    public Forecast(List<ForecastDay> forecastday) {
        this.forecastday = forecastday;
    }

    public List<ForecastDay> getForecastday() {
        return forecastday;
    }

    public void setForecastday(List<ForecastDay> forecastday) {
        this.forecastday = forecastday;
    }
}
