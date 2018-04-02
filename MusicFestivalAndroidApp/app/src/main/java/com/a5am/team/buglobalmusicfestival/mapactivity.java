package com.a5am.team.buglobalmusicfestival;




import android.os.Bundle;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;



/**
 * Created by sunruoshi on 3/31/18.
 */

public class mapactivity extends BaseNavActivity implements OnMapReadyCallback{


    private GoogleMap mMap;


    // 2
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);
        initializeToolbar();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    // 3
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng gsu = new LatLng(42.351156, -71.108983);
        mMap.addMarker(new MarkerOptions().position(gsu).title("Events in GSU"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(gsu));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(gsu, 15));
        LatLng cas = new LatLng(42.350297, -71.104797);
        mMap.addMarker(new MarkerOptions().position(cas).title("Events in CAS"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(cas));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cas, 15));
    }

}





