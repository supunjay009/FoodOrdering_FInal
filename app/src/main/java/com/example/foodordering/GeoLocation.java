package com.example.foodordering;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GeoLocation extends AppCompatActivity implements OnMapReadyCallback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_location);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.google_map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng Geolocation = new LatLng(6.962197, 79.894244);
        googleMap.addMarker(new MarkerOptions().position(Geolocation).title("We are here!"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Geolocation));

        LatLng Branch1location = new LatLng(6.038907, 80.247968);
        googleMap.addMarker(new MarkerOptions().position(Branch1location).title("Our Galle Branch!"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Branch1location));

    }
}