package com.example.chy.challenge.login.regular;

import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/10/13 0013.
 */

public class RegexUtil {
    public static boolean Mobile_phone(String isphone){
        String regex = "(\\+\\d+)?1[34578]\\d{9}$";
        return Pattern.matches(regex,isphone);
    }
}
