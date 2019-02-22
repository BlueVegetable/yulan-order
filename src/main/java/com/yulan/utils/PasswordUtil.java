package com.yulan.utils;

public class PasswordUtil {
    public static String stringEncrypter(String pwd)
    {
        return  stringEncrypter(pwd,true);
    }

    public static  String stringEncrypter(String pwd,boolean notERP) {
        if(pwd.trim().equals(""))
            return "-1";

        int pos = 0;
        int pwdLength;
        int pwdValue;
        String transPwdStr = "";
        String transStr = "";
        String pwdStr = pwd;
        pwdLength = pwd.length();

        String keycode = "gdut_erpsoft2002cims";

        if (pwdLength < 20) {

            for (int i = pwdLength; i < 20; i++) {
                pos = pos + 1;
                if (pos > pwdLength) {
                    pos = 1;
                }
                pwdStr = pwdStr + pwd.charAt(pos - 1);
            }
        }

        for (int i = 0; i < 20; i++) {
            pwdValue = (int) pwdStr.charAt(i) + (int) keycode.charAt(i);
            while (pwdValue > 127) {
                pwdValue = pwdValue - 127;
            }
            transStr = transStr + (char) pwdValue;
        }
        for (int i = 1; i <= 20; i++) {
            transPwdStr = transPwdStr +
                    (char) ( (int) transStr.charAt(i - 1) + i + pwdLength);
        }

        if(notERP)
        {
            char c = '\'';
            transPwdStr = transPwdStr.replace(c, 'x');
        }

        return transPwdStr;
    }
}
