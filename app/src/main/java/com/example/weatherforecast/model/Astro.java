package com.example.weatherforecast.model;

public class Astro {
    private String sunrise;
    private String sunset;
    private String moonrise;
    private String moonset;
    private String moon_phase;
    private String moon_illumination;
    private int is_moon_up;
    private int is_sun_up;

    public Astro(String sunrise, String sunset, String moonrise, String moonset, String moon_phase, String moon_illumination, int is_moon_up, int is_sun_up) {
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.moonrise = moonrise;
        this.moonset = moonset;
        this.moon_phase = moon_phase;
        this.moon_illumination = moon_illumination;
        this.is_moon_up = is_moon_up;
        this.is_sun_up = is_sun_up;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getMoonrise() {
        return moonrise;
    }

    public void setMoonrise(String moonrise) {
        this.moonrise = moonrise;
    }

    public String getMoonset() {
        return moonset;
    }

    public void setMoonset(String moonset) {
        this.moonset = moonset;
    }

    public String getMoon_phase() {
        return moon_phase;
    }

    public void setMoon_phase(String moon_phase) {
        this.moon_phase = moon_phase;
    }

    public String getMoon_illumination() {
        return moon_illumination;
    }

    public void setMoon_illumination(String moon_illumination) {
        this.moon_illumination = moon_illumination;
    }

    public int getIs_moon_up() {
        return is_moon_up;
    }

    public void setIs_moon_up(int is_moon_up) {
        this.is_moon_up = is_moon_up;
    }

    public int getIs_sun_up() {
        return is_sun_up;
    }

    public void setIs_sun_up(int is_sun_up) {
        this.is_sun_up = is_sun_up;
    }
}
