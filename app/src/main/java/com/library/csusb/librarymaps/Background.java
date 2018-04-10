package com.library.csusb.librarymaps;

import android.content.Context;
import android.os.AsyncTask;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.SquareCap;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by niapantam on 3/28/18.
 */

public class Background extends AsyncTask<String, Void, String> {


    Context context;
    String url = "http://10.0.2.2/android/get.php";
    String s;
    String jsonObject;
    reg reg = new reg(context);
    private OnJsonTransmitionCompleted mCallback;





    public Background(OnJsonTransmitionCompleted callback) {
        this.mCallback = callback;


    }


    @Override
    protected String doInBackground(String... strings) {
        s = strings[0];
        try {
            URL link = new URL(url);


            HttpURLConnection httpURLConnection = null;

            httpURLConnection = (HttpURLConnection) link.openConnection();


            InputStream inputStream = null;

            inputStream = httpURLConnection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));


            String line;
            StringBuilder result = new StringBuilder();


            while ((line = bufferedReader.readLine()) != null) {
                result.append(line + "\n");

            }
            bufferedReader.close();


            inputStream.close();

            httpURLConnection.disconnect();

            System.out.println(result.toString());


            return result.toString();


        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;


    }

    @Override
    protected void onPostExecute(String result) {

        try {
            jsonObject = reg.matchword(result, s.toUpperCase());
            System.out.println(jsonObject);


        } catch (JSONException e) {
            e.printStackTrace();
        }


        this.mCallback.onTransmitionCompleted(jsonObject);


    }



    public interface OnJsonTransmitionCompleted {
        void onTransmitionCompleted(String jsonObject);
    }


}




