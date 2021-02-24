package com.aa.googlemaps00;


import android.content.Context;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ProviderMapFragment extends SupportMapFragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private static final String TAG = "ProviderMapFragment";
    private GoogleMap mMap;
    private LocationManager locationManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        try {
            Criteria criteria = new Criteria();
            String providerStr = locationManager.getBestProvider(criteria, true);

            Toast.makeText(this.getContext(), "Provider Ativo: "+providerStr, Toast.LENGTH_SHORT).show();

            mMap.setMyLocationEnabled(true);
        }catch (SecurityException ex){
            Toast.makeText(this.getContext(), "Erro!", Toast.LENGTH_SHORT).show();
        }

        mMap.setOnMapClickListener(this);

//        // Add a marker in Sydney and move the camera
        LatLng sousa = new LatLng(-6.752845,  -38.231822);
        mMap.addMarker(new MarkerOptions().position(sousa).title("Marker in Sydney"));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sousa));
    }

    @Override
    public void onMapClick(LatLng latLng) {
        Toast.makeText(this.getContext(), "Coordenadas " + latLng.toString(), Toast.LENGTH_SHORT).show();
    }
}
