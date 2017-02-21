package com.lohika.myazin.octane.tests;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by amyazin on 2/19/2017.
 */
public class TestRegexp {

    @Test
    public void testRegex(){
        String s = "ui-widget-content slick-row odd item-id-5001 item-index-1";
        String p = ".*\\bitem-index-(\\d+)\\b.*";

        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(s);
        if (matcher.find() && matcher.groupCount() > 0){
            System.out.println(matcher.group(0));
            System.out.println(Integer.parseInt(matcher.group(1)));
        }
    }
}
