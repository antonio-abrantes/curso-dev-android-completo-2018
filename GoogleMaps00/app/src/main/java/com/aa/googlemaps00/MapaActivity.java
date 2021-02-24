package com.aa.googlemaps00;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapaActivity extends SupportMapFragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    public static GoogleMap mMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        String providerStr = LocationManager.NETWORK_PROVIDER;

        LocationManager locationManager = (LocationManager) this.getContext().getSystemService(Context.LOCATION_SERVICE);

//        for(String provider : locationManager.getAllProviders()) {
//            Toast.makeText(this.getContext().getApplicationContext(), provider, Toast.LENGTH_LONG).show();
//        }

        mMap = googleMap;

        mMap.setOnMapClickListener(this);

//        // Add a marker in Sydney and move the camera
        LatLng sousa = new LatLng(-6.752845,  -38.231822);
        mMap.addMarker(new MarkerOptions().position(sousa).title("Marker in Sydney"));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sousa, 8));

        mMap.addPolyline(new PolylineOptions().geodesic(true)
                .add(new LatLng(-33.866, 151.195))  // Sydney
                .add(new LatLng(-18.142, 178.431))  // Fiji
                .add(new LatLng(21.291, -157.821))  // Hawaii
                .add(new LatLng(37.423, -122.091))  // Mountain View
        );
    }

    @Override
    public void onMapClick(LatLng latLng) {
        Toast.makeText(this.getContext(), "Coordenadas " + latLng.toString(), Toast.LENGTH_SHORT).show();
    }
}
