package com.example.foodordering.Activity.Activity;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.example.foodordering.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.foodordering.R;
//import com.example.foodordering.Activity.Activity.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

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

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Colombo and move the camera
        LatLng colombo = new LatLng(6.906482592490116, 79.87075258937644);
        mMap.addMarker(new MarkerOptions().position(colombo).title("We are here!"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(colombo));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(10));

        LatLng gampaha = new LatLng(7.093394850917027, 79.99366869064785);
        mMap.addMarker(new MarkerOptions().position(gampaha).title("Our Gampaha branch."));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(gampaha));
    }
}