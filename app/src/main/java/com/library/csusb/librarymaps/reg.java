package com.library.csusb.librarymaps;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.*;

class reg {


    Context context;
    String z1 = null, l1 = null, num1 = null;
    String zinput = null, linput = null, numinput = null;
    String z2 = null, l2 = null, num2 = null;
    JSONObject jsonObject1 = null, jsonObject2 = null;
    String jsonObject3 = "";




    String re1 = "(^[A-Z*])";    // Any Single Character 1
    String re2 = "([A-Z]?)";    // Any Single Character 2
    String re3 = "(\\d+|)";
    String re4="(\\.?)";	// Any Single Character 1
    String re5="((?:[a-z]?[a-z0-9_]*))";	//Variable 1
// Integer Number 1

    Pattern p = Pattern.compile(re1 + re2 + re3, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);


    Matcher m_input;









    public String matchword(String result, String show) throws JSONException {
        JSONArray jsonArray = new JSONArray(result);


        String s;
        s = null;
        for (int i = 0; i < jsonArray.length()-1; i++) {
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




            Matcher m_second = p.matcher(s2);
            if (m_second.find()) {


                z2 = m_second.group(1);
                l2 = m_second.group(2);
                num2 = m_second.group(3);


            }




if(zinput.equals("*")){
                if(zinput.equals(z1)&&linput.equals(l1)){
                    s=jsonObject1.toString();

    }
}



            //}


            if (zinput.equals(z1) && (l1.equals(l2))&&(zinput.equals("*")==false))

            {

                s = g1();




            }


            if (zinput.equals(z1) && (l1.equals(l2) == false)&&(zinput.equals("*")==false))

            {
                s = g2();


            }




        }
        return s;
    }



    private String g1() {


        if (linput.equals(l1)) {

            Log.d("tag", "inside 0");


            if ((Integer.parseInt(numinput) == Integer.parseInt(num1))) {
                System.out.println("Input is:" + "(" + zinput.toString() + ")" + "(" + linput.toString() + ")" + "(" + numinput.toString() + ")" + "\n");

                Log.d("tag", "inside 1");

                jsonObject3= jsonObject1.toString();


            } else if ((Integer.parseInt(numinput) > Integer.parseInt(num1)) && (Integer.parseInt(numinput) < Integer.parseInt(num2))) {

                {


                    Log.d("tag", "inside 2");


                    jsonObject3= jsonObject1.toString();
                }


            }
        }
        return jsonObject3;


    }

    private String g2() {
        Log.d("tag", "inside 1!");


        if (linput.equals(l1) && (Integer.parseInt(numinput) == Integer.parseInt(num1))) {
            jsonObject3 = jsonObject1.toString();


        }


        if ((linput.equals(l1)) && (Integer.parseInt(numinput) > Integer.parseInt(num1) && (Integer.parseInt(numinput) < 10000))) {


            jsonObject3 = jsonObject1.toString();
        } else {
            if ((linput.equals(l2)) && (Integer.parseInt(numinput) > 0 && (Integer.parseInt(numinput) < Integer.parseInt(num2))))

            {
                jsonObject3 = jsonObject1.toString();


            }
        }

return jsonObject3;
    }





    public reg(Context current) {

        this.context = current;
    }





}

