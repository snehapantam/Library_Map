package com.library.csusb.librarymaps;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by niapantam on 4/11/18.
 */

public class Validate {
    String re1 = "(^[A-Z*])";    // Any Single Character 1
    String re2 = "([A-Z]?)";    // Any Single Character 2
    String re3 = "(\\d+|)";
    String zinput = null, linput = null, numinput = null;



    Pattern p = Pattern.compile(re1 + re2 + re3, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);




    Matcher m_input;

    public boolean validate(String input) {

        m_input = p.matcher(input);

        System.out.println("Actually input is" + input);


        if (m_input.find()) {


            zinput = m_input.group(1);
            linput = m_input.group(2);
            numinput = m_input.group(3);


            System.out.print("(" + zinput.toString() + ")" + "(" + linput.toString() + ")" + "(" + numinput.toString() + ")" + "\n");

return true;


        }
        else {
            return false;
        }


    }
}
