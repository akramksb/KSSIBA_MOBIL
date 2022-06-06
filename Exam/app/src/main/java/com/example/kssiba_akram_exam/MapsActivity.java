package com.example.kssiba_akram_exam;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.kssiba_akram_exam.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private double lon;
    private double lat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng default_city = new LatLng(32.86394050098598, -6.570182971876561);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(default_city));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(default_city));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(default_city,10));
        mMap.getUiSettings().setZoomControlsEnabled(true);

        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            final GoogleMap gmap=googleMap;
            @Override
            public void onMapClick(LatLng latLng) {
                System.out.println("Akram : You clicked on this position");
                System.out.println(latLng);

                Intent i = new Intent(getApplicationContext(), MeteoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putDouble("lat", latLng.latitude);
                bundle.putDouble("lon", latLng.longitude);
                i.putExtras(bundle);
                startActivity(i);
            }});
    }
}