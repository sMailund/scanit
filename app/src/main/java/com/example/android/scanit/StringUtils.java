package com.example.android.scanit;

/**
 * Created by Simen on 2017-10-29.
 */

public class StringUtils {

    /**
     * Capitalizes given String
     * @param input String to be capitalized
     * @return capitalized input String
     */
    public static String capitalize(String input) {
        return input.substring(0,1).toUpperCase() + input.substring(1).toLowerCase();
    }

}
