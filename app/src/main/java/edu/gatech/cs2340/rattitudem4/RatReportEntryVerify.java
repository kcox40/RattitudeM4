package edu.gatech.cs2340.rattitudem4;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by kcox8 on 11/20/2017.
 */

public class RatReportEntryVerify {

    public static String checkZip(String zip) {
        if (!"".equals(zip)) {
            if (zip.length() == 5) {
                boolean containsChar = false;
                int i = 0;
                while ((i < zip.length()) && (!containsChar)) {
                    if (Character.isLetter(zip.charAt(i))) {
                        containsChar = true;
                    }
                    i++;
                }
                if (!containsChar) {
                    return zip;
                } else {
                    return "Zip Must Contain Digits Only";
                }
            } else {
                return "Zip must be 5 digits long";
            }
        }
        return "Zip field is empty";
    }


}
