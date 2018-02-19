package com.example.alejandroquiros.examen2t;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public List<Lugar> lugares;
    //Handler del SQL Lite
    DatabaseHandler databaseHandler = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        lugares = databaseHandler.getAllContacts();

        Toast.makeText(this, "Cantidad: "+lugares.size(), Toast.LENGTH_SHORT).show();

        Toast.makeText(this, "Nombre: "+lugares.get(0).getNombre(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Longitud: "+lugares.get(0).getLon(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Latitud: "+lugares.get(0).getLat(), Toast.LENGTH_SHORT).show();
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

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));






        for(int l=0; l<lugares.size(); l++){

            //Toast.makeText(this, "Nombre: "+lugares.get(l).getNombre(), Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, "Longitud: "+lugares.get(l).getLon(), Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, "Latitud: "+lugares.get(l).getLat(), Toast.LENGTH_SHORT).show();
            LatLng p = new LatLng(lugares.get(l).getLat(), lugares.get(l).getLon());
            mMap.addMarker(new MarkerOptions().position(p).title(lugares.get(l).getNombre()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(p));
        }

    }
}
