package com.library.csusb.librarymaps;


import android.app.SearchManager;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    reg reg = new reg(this);


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SearchView simpleSearchView = (SearchView) findViewById(R.id.search);


        // inititate shelf_list search view
        simpleSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                System.out.print("Calling from main");
                boolean res = reg.validate(query);


                if (res == true) {
                    Intent myIntent = new Intent(MainActivity.this, MapsActivity.class);
                    myIntent.putExtra("Input", query);
                    MainActivity.this.startActivity(myIntent);

                } else {
                    Toast.makeText(getApplicationContext(), "Not Valid", Toast.LENGTH_LONG).show();

                    Log.d("Nope", query);


                }


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                return false;
            }
        });


    }


}










