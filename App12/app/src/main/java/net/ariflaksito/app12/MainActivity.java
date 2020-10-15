package net.ariflaksito.app12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;
import com.google.android.material.snackbar.Snackbar;

import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap gMap;
    public static final int LOCATION_UPDATE_MIN_DISTANCE = 10;
    public static final int LOCATION_UPDATE_MIN_TIME = 5000;
    static final int LOCATION_PERMISSION_REQUEST_CODE = 100;
    private LocationManager mLocationManager;
    Button btnLoc, btnLine;
    Location location;
    static LatLng tuguLoc = new LatLng(-7.797068, 110.370529);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.g_map);
        mapFragment.getMapAsync(this);

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (checkLocationPermission()) {
            Log.d("PERMISSION --", "Yes");
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_UPDATE_MIN_TIME, LOCATION_UPDATE_MIN_DISTANCE, this);
        }

        btnLine = findViewById(R.id.btn_line);
        btnLoc = findViewById(R.id.btn_loc);

        btnLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (location != null)
                    drawMarker(location);
            }
        });

        btnLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (location != null) {
                    LatLng myLoc = new LatLng(location.getLatitude(), location.getLongitude());
                    drawLine(tuguLoc, myLoc);
                }
            }
        });

    }

    private boolean checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            return true;
        else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
            return false;
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_UPDATE_MIN_TIME, LOCATION_UPDATE_MIN_DISTANCE, this);
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;

        LatLng tugu = tuguLoc;
        gMap.addMarker(new MarkerOptions().position(tugu).title("Tugu Jogja"));
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tugu, 14));
        gMap.getUiSettings().setZoomControlsEnabled(true);
    }

    private void drawMarker(Location location) {
        if (gMap != null) {
            LatLng gps = new LatLng(location.getLatitude(), location.getLongitude());
            gMap.addMarker(new MarkerOptions()
                    .position(gps)
                    .title("Current Position")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(gps, 14));
        }
    }

    private void drawLine(LatLng src, LatLng dst) {
        PolylineOptions plo = new PolylineOptions();
        plo.add(src);
        plo.add(dst);
        plo.color(Color.RED);
        plo.geodesic(true);
        plo.startCap(new RoundCap());
        plo.width(10);
        plo.jointType(JointType.BEVEL);

        gMap.addPolyline(plo);

    }

    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
        Toast.makeText(this, "Current location available", Toast.LENGTH_LONG).show();
        Log.d("LOCATION -- ", "Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }
}