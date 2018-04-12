package com.library.csusb.librarymaps;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.SquareCap;

import org.json.JSONException;
import org.json.JSONObject;

import com.library.csusb.librarymaps.Background.OnJsonTransmitionCompleted;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, Background.OnJsonTransmitionCompleted {

    LatLng book;
    String floor;
    String shelf;
    String call_number;
    String show;
    SupportMapFragment mapFragment;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);


        Intent i = getIntent();
        show = i.getStringExtra("Input");


        Background background = new Background(this);
        background.execute(show);

        SwipeRefreshLayout swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                finish();
                startActivity(getIntent());
            }
        });



    }


    @Override
    public void onTransmitionCompleted(String jsonObject) {


        if (jsonObject == null) {
            System.out.println("Null" + jsonObject);


        }


        try {
            JSONObject obj = new JSONObject(jsonObject);

            double latitude = Double.parseDouble(obj.getString("Latitude"));
            double longitude = Double.parseDouble(obj.getString("Longitude"));


            call_number = obj.getString("call_number");
            shelf = obj.getString("shelf");
            floor = obj.getString("Floor");
            book = new LatLng(latitude, longitude);


            //   LatLng elevator = new LatLng(34.18265415799397, -117.32386540621519);

            mapFragment.getMapAsync(this);


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
        LatLng library = new LatLng(34.182599, -117.324019);
         mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(library, 12));


        GroundOverlayOptions fpOverlay = new GroundOverlayOptions()
                .position(library, 82.7f, 72.0f).bearing(32.163f);


        switch (floor) {
            case "1":


                fpOverlay.image(BitmapDescriptorFactory.fromResource(R.drawable.floor1));


                break;

            case "3":
                fpOverlay.image(BitmapDescriptorFactory.fromResource(R.drawable.floor3));


                break;

            case "4":
                fpOverlay.image(BitmapDescriptorFactory.fromResource(R.drawable.floor4));

                break;
            case "5":
                fpOverlay.image(BitmapDescriptorFactory.fromResource(R.drawable.floor5));

                break;
        }


        mMap.addGroundOverlay(fpOverlay);



     mMap.addMarker(new MarkerOptions().position(book));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(book, 20));






       /* mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {


                System.out.println(point);


                mMap.addMarker(new MarkerOptions().position(point).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

            }

        });


        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                System.out.println(latLng);


                mMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));


            }
        });*/
    }
}







