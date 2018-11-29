package com.futech.smartbirth;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import androidx.fragment.app.FragmentActivity;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    double v, v1;
    MarkerOptions markerOptions;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //view = findViewById(R.id.view);
        //view.setVisibility(View.INVISIBLE);
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

        v = -34;
        v1 = 151;
        LatLng sydney = new LatLng(-34, 151);

        //mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMinZoomPreference(15.0f);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        markerOptions = new MarkerOptions();
        //markerOptions.position(mMap.getCameraPosition().target);
        //mMap.addMarker(markerOptions);


        mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {

                markerOptions.position(mMap.getCameraPosition().target);
                mMap.addMarker(markerOptions);
                Log.d("camera","idle" + mMap.getCameraPosition().target);
               // view.setVisibility(View.VISIBLE);

            }
        });

        mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {

                Log.d("camera","move");
               // view.setVisibility(View.INVISIBLE);

            }
        });



    }
}
