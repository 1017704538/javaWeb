//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lh.util;

import java.util.Calendar;

public class CalendarUtil {
    public CalendarUtil() {
    }

    public static String getParticularDateTime(Calendar calendar) {
        if (calendar == null) {
            return "";
        } else {
            StringBuffer sb = new StringBuffer();
            sb.append(calendar.get(1));
            sb.append("-");
            sb.append(calendar.get(2) + 1);
            sb.append("-");
            sb.append(calendar.get(5));
            sb.append(" ");
            sb.append(calendar.get(11));
            sb.append(":");
            sb.append(calendar.get(12));
            sb.append(":");
            sb.append(calendar.get(13));
            return sb.toString();
        }
    }
}
