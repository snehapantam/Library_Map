package com.library.csusb.librarymaps;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

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
    List<String[]> result= new ArrayList<String[]>();
    List<String> shelf_list = new ArrayList<>();
    List<String> call_number = new ArrayList<>();
    List<String> latitude= new ArrayList<>();
    List<String> longitude= new ArrayList<>();
    List<String> id= new ArrayList<>();
    List<String> floor= new ArrayList<>();



    Matcher m_input;
    public boolean validate(String input){

    m_input = p.matcher(input);

        System.out.println("Actually input is"+input);





        if (m_input.find()) {



            zinput = m_input.group(1);
            linput = m_input.group(2);
            numinput = m_input.group(3);


            System.out.println("Input is:" + "(" + zinput.toString() + ")" + "(" + linput.toString() + ")" + "(" + numinput.toString() + ")" + "\n");


            return true;

        }
        else {
            Log.d("tag","not validated "+m_input.find());
            return false;

        }


    }



    public int main(String show) {





        //System.out.println("Input is:" + "(" + zinput.toString() + ")" + "(" + linput.toString() + ")" + "(" + numinput.toString() + ")" + "\n");


        System.out.print("Inside main");


        InputStream is = context.getResources().openRawResource(R.raw.books);
        BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

        String line = " ";
        try {
            while ((line = br.readLine()) != null) {


                String[] row = line.split(",");
                result.add(row);
                id.add(row[0]);
floor.add(row[1]);
                shelf_list.add(row[2]);
                call_number.add(row[3]);
                latitude.add(row[4]);
                longitude.add(row[5]);





            }
        } catch (IOException e) {

            Log.d("tag", "error" + e);
            e.printStackTrace();
        }


int val=Integer.parseInt(matchword(shelf_list, call_number,show));

        return val;
    }






    public String matchword(List<String> a, List<String> b, String show) {




        int i = 0;
        Log.d("tag", "call_number size" + b.size());


        for (i = 1; i < b.size() - 1; i++) {


            Matcher m_first = p.matcher(b.get(i));


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


                if (i != b.size()) {


                    Matcher m_second = p.matcher(b.get(i + 1));
                    if (m_second.find()) {


                        z2 = m_second.group(1);
                        l2 = m_second.group(2);
                        num2 = m_second.group(3);


                    }

                }



                if (zinput.equals(z1) && (l1.equals(l2)))

                {

                    if (linput.equals(l1)) {
                        Log.d("tag", "inside 0");


                        if ((Integer.parseInt(numinput) == Integer.parseInt(num1))) {
                            System.out.println("Input is:" + "(" + zinput.toString() + ")" + "(" + linput.toString() + ")" + "(" + numinput.toString() + ")" + "\n");

                            Log.d("tag", "inside 1");

                            shelf = a.get(i);
                            value = id.get(i);

                        }

                        else if ((Integer.parseInt(numinput) > Integer.parseInt(num1)) && (Integer.parseInt(numinput) < Integer.parseInt(num2))) {

                            {


                                Log.d("tag", "inside 2");

                                System.out.println("Input is:" + "(" + z1.toString() + ")" + "(" + l1.toString() + ")" + "(" + num1.toString() + ")" + "\n");

                                System.out.println("Input is:" + "(" + zinput.toString() + ")" + "(" + linput.toString() + ")" + "(" + numinput.toString() + ")" + "\n");

                                System.out.println("Input is:" + "(" + z2.toString() + ")" + "(" + l2.toString() + ")" + "(" + num2.toString() + ")" + "\n");

                                shelf = a.get(i);
                                value = id.get(i);
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

if(linput.equals(l1)&& (Integer.parseInt(numinput) == Integer.parseInt(num1))){
    shelf = a.get(i);
    value = id.get(i);

}




                    if ((linput.equals(l1)) && (Integer.parseInt(numinput) > Integer.parseInt(num1) && (Integer.parseInt(numinput) < 10000))) {


                        shelf = a.get(i);
                        value = id.get(i);
                    } else {
                        if ((linput.equals(l2)) && (Integer.parseInt(numinput) > 0 && (Integer.parseInt(numinput) < Integer.parseInt(num2))))

                        {
                            shelf = a.get(i);
                            value = id.get(i);


                        }
                    }


                }

            }
            System.out.println("Value is " + value);
        return value;

        }









    public reg(Context current) {

        this.context= current;
    }











    public LatLng getlocation(int value1){

      String l1=latitude.get(value1);
        String l2=longitude.get(value1);





        double latitude = Double.parseDouble(l1);
        double longitude = Double.parseDouble(l2);



       LatLng latLng= new LatLng(latitude,longitude);
       return latLng;



    }

    public String getFloor(int value) {


        return floor.get(value);
    }

    public String getShelf(int value) {


        return shelf_list.get(value);
    }

    public String getCallnumber(int value) {


        return call_number.get(value);
    }
}