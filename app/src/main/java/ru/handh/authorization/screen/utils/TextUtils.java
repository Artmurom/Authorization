package ru.handh.authorization.screen.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TextUtils {

    private static final Pattern VALID_EMAIL_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static final Pattern VALID_PASS_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}$");

    private TextUtils() {

    }

    public static boolean isEmpty(String string) {
        return string == null || string.length() == 0;
    }


    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_REGEX.matcher(emailStr);
        return matcher.matches();
    }

    public static boolean validatePass(String pass) {
        Matcher matcher = VALID_PASS_REGEX.matcher(pass);
        return matcher.matches();
    }
}
