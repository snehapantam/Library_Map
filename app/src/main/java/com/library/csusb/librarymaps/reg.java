package com.library.csusb.librarymaps;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

class reg {


    Context context;
    String z1 = null, l1 = null, num1 = null, zinput = null, linput = null, numinput = null;
    String z2 = null, l2 = null, num2 = null;
    String value;


    String re1 = "(^[A-Z*])";    // Any Single Character 1
    String re2 = "([A-Z]?)";    // Any Single Character 2
    String re3 = "(\\d+)";// Integer Number 1

    String shelf = "";
    Pattern p = Pattern.compile(re1 + re2 + re3, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    List<String[]> result = new ArrayList<String[]>();
    List<String> shelf_list = new ArrayList<>();
    List<String> call_number = new ArrayList<>();
    List<String> latitude = new ArrayList<>();
    List<String> longitude = new ArrayList<>();
    List<String> floor = new ArrayList<>();


    Matcher m_input;


    public void reg(Context context) {
        context = this.context;


    }


    public boolean validate(String input) {

        m_input = p.matcher(input);

        System.out.println("Actually input is" + input);


        if (m_input.find()) {


            zinput = m_input.group(1);
            linput = m_input.group(2);
            numinput = m_input.group(3);


            System.out.println("Input is:" + "(" + zinput.toString() + ")" + "(" + linput.toString() + ")" + "(" + numinput.toString() + ")" + "\n");


            return true;

        }



        else {
            Log.d("tag", "not validated " + m_input.find());
            return false;

        }


    }


    public String matchword(String result, String show) throws JSONException {
        JSONArray jsonArray = new JSONArray(result);
        JSONObject jsonObject1 = null, jsonObject2 = null;
        
        String jsonObject3 = "";
        

    


        for (int i = 0; i < jsonArray.length() - 1; i++) {
            jsonObject1 = jsonArray.getJSONObject(i);
            jsonObject2 = jsonArray.getJSONObject(i + 1);


            String s1 = jsonObject1.getString("call_number");
            String s2 = jsonObject2.getString("call_number");


            Matcher m_first = p.matcher(s1);


            if (m_first.find()) {


                z1 = m_first.group(1);
                l1 = m_first.group(2);
                num1 = m_first.group(3);


            }


            m_input = p.matcher(show);


            if (m_input.find()) {


                zinput = m_input.group(1);
                linput = m_input.group(2);
                numinput = m_input.group(3);
            }


            //if (i != jsonArray.length()) {


                Matcher m_second = p.matcher(s2);
                if (m_second.find()) {


                    z2 = m_second.group(1);
                    l2 = m_second.group(2);
                    num2 = m_second.group(3);


                }

            //}


            if (zinput.equals(z1) && (l1.equals(l2)))

            {

                if (linput.equals(l1)) {
                    Log.d("tag", "inside 0");


                    if ((Integer.parseInt(numinput) == Integer.parseInt(num1))) {
                        System.out.println("Input is:" + "(" + zinput.toString() + ")" + "(" + linput.toString() + ")" + "(" + numinput.toString() + ")" + "\n");

                        Log.d("tag", "inside 1");
                        
                        jsonObject3= jsonObject1.toString();


                    } else if ((Integer.parseInt(numinput) > Integer.parseInt(num1)) && (Integer.parseInt(numinput) < Integer.parseInt(num2))) {

                        {


                            Log.d("tag", "inside 2");

                            System.out.println("Input is:" + "(" + z1.toString() + ")" + "(" + l1.toString() + ")" + "(" + num1.toString() + ")" + "\n");

                            System.out.println("Input is:" + "(" + zinput.toString() + ")" + "(" + linput.toString() + ")" + "(" + numinput.toString() + ")" + "\n");

                            System.out.println("Input is:" + "(" + z2.toString() + ")" + "(" + l2.toString() + ")" + "(" + num2.toString() + ")" + "\n");

                            jsonObject3= jsonObject1.toString();
                        }


                    }
                }
            }


            if (zinput.equals(z1) && (l1.equals(l2) == false))

            {


                Log.d("tag", "inside 1!");

                System.out.println("Input is:" + "(" + z1.toString() + ")" + "(" + l1.toString() + ")" + "(" + num1.toString() + ")" + "\n");

                System.out.println("Input is:" + "(" + zinput.toString() + ")" + "(" + linput.toString() + ")" + "(" + numinput.toString() + ")" + "\n");

                System.out.println("Input is:" + "(" + z2.toString() + ")" + "(" + l2.toString() + ")" + "(" + num2.toString() + ")" + "\n");

                if (linput.equals(l1) && (Integer.parseInt(numinput) == Integer.parseInt(num1))) {

                }


                if ((linput.equals(l1)) && (Integer.parseInt(numinput) > Integer.parseInt(num1) && (Integer.parseInt(numinput) < 10000))) {


                    jsonObject3= jsonObject1.toString();
                } else {
                    if ((linput.equals(l2)) && (Integer.parseInt(numinput) > 0 && (Integer.parseInt(numinput) < Integer.parseInt(num2))))

                    {
                        jsonObject3= jsonObject1.toString();


                    }
                }


            }

        }
        return jsonObject3;

    }


    public reg(Context current) {

        this.context = current;
    }





}

