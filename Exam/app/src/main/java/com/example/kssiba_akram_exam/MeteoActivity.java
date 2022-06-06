package com.example.kssiba_akram_exam;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MeteoActivity extends AppCompatActivity {
    private static final DecimalFormat df = new DecimalFormat("0.00");

    private TextView tv_name, tv_coord, tv_date, tv_weather_main, tv_temp, tv_feels_like, tv_temp_min, tv_temp_max, tv_pressure, tv_humidity, tv_sea_level, tv_wind_speed;
    private ImageView iv_icon;
    private double lon;
    private double lat;

    RecyclerView recyclerView;
    LinearLayoutManager horizontalLayoutManagaer;
    private List<ForecastData> dataList;
    private ForecastAdapter adapter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meteo);

        tv_name = findViewById(R.id.meto_name);
        tv_coord = findViewById(R.id.meto_coord);
        tv_date = findViewById(R.id.meteo_date);
        tv_weather_main = findViewById(R.id.meteo_main);
        tv_temp = findViewById(R.id.meteo_temp);
        tv_feels_like = findViewById(R.id.meteo_feels_like);
        tv_temp_min = findViewById(R.id.meteo_temp_min);
        tv_temp_max = findViewById(R.id.meteo_temp_max);
        tv_pressure = findViewById(R.id.meteo_pressure);
        tv_humidity = findViewById(R.id.meteo_humidity);
        tv_sea_level = findViewById(R.id.meteo_sea_level);
        tv_wind_speed = findViewById(R.id.meteo_wind_speed);
        iv_icon =  (ImageView) findViewById(R.id.meteo_icon);

        recyclerView = findViewById(R.id.meteo_recycleView);

        Bundle bundle = getIntent().getExtras();
        lat = bundle.getDouble("lat");
        lon = bundle.getDouble("lon");
        tv_coord.setText(df.format(lat) + ", " + df.format(lon));
        fillFields();

        // Akram : recycle view

        dataList = new ArrayList<>();

        horizontalLayoutManagaer=new LinearLayoutManager(MeteoActivity.this, LinearLayoutManager.HORIZONTAL, false);

        getForecast();


    }

    private void getForecast() {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://api.openweathermap.org/data/2.5/forecast?lat="+lat+"&lon="+lon+"&appid=16e348ee47af9f162b3413f746d1aad5";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);

                    JSONArray jsonObjectArray = jsonResponse.getJSONArray("list");

                    for (int i=0; i<7; i++ ){
                        JSONObject jsonObject = jsonObjectArray.getJSONObject(i);

                        JSONObject main = jsonObject.getJSONObject("main");
//                        JSONObject wind = jsonObject.getJSONObject("wind");
                        JSONArray weather = jsonObject.getJSONArray("weather");
//
//                        String cityName = jsonObject.getString("name");
                        String meteo = weather.getJSONObject(0).getString("main");
                        String icon = weather.getJSONObject(0).getString("icon");


                        int Temp = (int) (main.getDouble("temp") - 273.15);
                        Date date = new Date(jsonObject.getLong("dt") * 1000);
                        SimpleDateFormat simpleDateFormat =
                                new SimpleDateFormat("dd MMM");
                        String dateString = simpleDateFormat.format(date);

                        String iconUrl = "https://openweathermap.org/img/w/" + icon + ".png";

                        dataList.add( new ForecastData(dateString, Temp, iconUrl, meteo) );


                    }

                    recyclerView.setLayoutManager(horizontalLayoutManagaer);
                    adapter=new ForecastAdapter(MeteoActivity.this, dataList);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MeteoActivity.this,
                                "Error", Toast.LENGTH_LONG).show();


                    }
                });

        queue.add(stringRequest);
    }

    public void fillFields() {
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            String url = "https://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&appid=16e348ee47af9f162b3413f746d1aad5";
            //https://api.openweathermap.org/data/2.5/weather?q=London&appid=e457293228d5e1465f30bcbe1aea456b

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    try {

                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject main = jsonObject.getJSONObject("main");
                        JSONObject wind = jsonObject.getJSONObject("wind");
                        JSONArray weather = jsonObject.getJSONArray("weather");

                        Date date = new Date(jsonObject.getLong("dt") * 1000);
                        SimpleDateFormat simpleDateFormat =
                                new SimpleDateFormat("dd MMM yyyy  HH:mm");
                        String dateString = simpleDateFormat.format(date);

                        String cityName = jsonObject.getString("name");
                        String meteo = weather.getJSONObject(0).getString("main");
                        String icon = weather.getJSONObject(0).getString("icon");


                        int Temp = (int) (main.getDouble("temp") - 273.15);
                        int FeelsLike = (int) (main.getDouble("feels_like") - 273.15);
                        int TempMin = (int) (main.getDouble("temp_min") - 273.15);
                        int TempMax = (int) (main.getDouble("temp_max") - 273.15);
                        int Pression = (int) (main.getDouble("pressure"));
                        int Humidite = (int) (main.getDouble("humidity"));
                        double WindSpeed = (double) (wind.getDouble("speed"));
                        int SeaLevel = -1;
                        if (main.has("sea_level")){
                            SeaLevel = (int) (main.getDouble("sea_level"));
                        }


                        tv_name.setText(cityName);
                        tv_weather_main.setText(meteo);
                        tv_date.setText(dateString);
                        tv_temp.setText(Temp + "°C");
                        tv_feels_like.setText("feels like : "+FeelsLike+ "°C");
                        tv_temp_min.setText("min temp : "+TempMin+ "°C");
                        tv_temp_max.setText("max temp : "+ TempMax+ "°C");
                        tv_pressure.setText("pressure : "+Pression +" mbar");
                        tv_humidity.setText("humidity : "+Humidite + "%");
                        tv_sea_level.setText("sea level : "+SeaLevel);
                        tv_wind_speed.setText("wind : "+df.format(WindSpeed));


                        String iconUrl = "https://openweathermap.org/img/w/" + icon + ".png";
                        System.out.println(iconUrl);
                        Picasso.get().load(iconUrl).into(iv_icon);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MeteoActivity.this,
                                    "Error", Toast.LENGTH_LONG).show();


                        }
                    });

            queue.add(stringRequest);
    }
}
