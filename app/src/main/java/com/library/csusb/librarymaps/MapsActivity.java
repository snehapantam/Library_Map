package com.library.csusb.librarymaps;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.library.csusb.librarymaps.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    int value;

    reg reg= new reg(this);
    LatLng book;

   String floor;
   String shelf;
   String call_number;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent i=getIntent();
        String show=i.getStringExtra("Input");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        System.out.println("calling main re "+show.toUpperCase());


        value=reg.main(show.toUpperCase());
        System.out.println("Value is"+value);

        book= reg.getlocation(value);

 floor=reg.getFloor(value);
 shelf=reg.getShelf(value);
      call_number=reg.getCallnumber(value);




    }


    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
        LatLng library=new LatLng(34.182599,-117.324019);
        // Add shelf_list marker in Sydney and move the camera
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(library,20));

        GroundOverlayOptions fpOverlay = new GroundOverlayOptions()
                .position(library,82.7f,72.0f).bearing(32.163f);



        switch(floor){
            case "1":


                fpOverlay.image(BitmapDescriptorFactory.fromResource(R.drawable.floor1));


                break;

            case "3":
                fpOverlay.image(BitmapDescriptorFactory.fromResource(R.drawable.floor3));


                break;

        }



                mMap.addGroundOverlay(fpOverlay);










        //System.out.println(book);



   mMap.addMarker(new MarkerOptions().position(book).title("Call number: "+call_number).snippet("Shelf number: "+shelf));


        //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(book,18));













    }


}
