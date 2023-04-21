package com.poc.app.utils;

import org.apache.commons.text.RandomStringGenerator;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

public class Common {

    public static String getRandomPhoneNumber() {
        // It will generate 5 digit random Number.
        // from 0 to 99999
        Random rnd = new Random();
        int number = rnd.nextInt(99999);
        // this will convert any number sequence into 5 character.
        return String.format("00008%05d", number);
    }

    public static String changeFormatDate(String OLD_FORMAT, String NEW_FORMAT, String oldDateString){
        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
        Date d = null;
        try {
            d = sdf.parse(oldDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf.applyPattern(NEW_FORMAT);
        return sdf.format(d);
    }

    public static String generateRandomString(int num) {
        RandomStringGenerator generator = new RandomStringGenerator
                .Builder()
                .withinRange('0', 'z')
                .filteredBy(LETTERS, DIGITS)
                .build();
        return generator.generate(num);
    }
}
