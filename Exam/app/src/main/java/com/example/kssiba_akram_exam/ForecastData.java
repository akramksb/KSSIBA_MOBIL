package com.example.kssiba_akram_exam;

public class ForecastData {
    private String date;
    private int temp;
    private String icon;
    private String meteo;

    public ForecastData(String date, int temp, String icon, String meteo) {
        this.date = date;
        this.temp = temp;
        this.icon = icon;
        this.meteo = meteo;
    }

    public String getDate() {
        return date;
    }

    public int getTemp() {
        return temp;
    }

    public String getIcon() {
        return icon;
    }

    public String getMeteo() {
        return meteo;
    }
}

