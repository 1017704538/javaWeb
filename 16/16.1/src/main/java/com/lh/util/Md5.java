//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lh.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {
    public Md5() {
    }

    public static final String getMD5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(str.getBytes());
            byte[] mdBytes = md.digest();
            String hash = "";

            for(int i = 0; i < mdBytes.length; ++i) {
                int temp;
                if (mdBytes[i] < 0) {
                    temp = 256 + mdBytes[i];
                } else {
                    temp = mdBytes[i];
                }

                if (temp < 16) {
                    hash = hash + "0";
                }

                hash = hash + Integer.toString(temp, 16);
            }

            return hash;
        } catch (NoSuchAlgorithmException var6) {
            var6.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(getMD5("111").length());
    }
}
