package com.example.googlemapsapp;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.googlemapsapp.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String ville;
    private double lon;
    private double lat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
// Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Bundle bundle = getIntent().getExtras();
        ville = bundle.getString("ville");
        lon = bundle.getDouble("lon");
        lat = bundle.getDouble("lat");
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng twincenter = new LatLng(lat, lon);
        mMap.addMarker(new MarkerOptions().position(twincenter).title(ville));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(twincenter));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(twincenter,10));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(twincenter);
        circleOptions.radius(700);
        circleOptions.fillColor(Color.TRANSPARENT);
        circleOptions.strokeWidth(6);
        mMap.addCircle(circleOptions);
    }
}