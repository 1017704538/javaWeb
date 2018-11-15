//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lh.util;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;

public class UniqueUtil {
    public UniqueUtil() {
    }

    public static void saveUnique(HttpServletRequest request) {
        String id = String.valueOf((new Date()).getTime());
        request.getSession().setAttribute("UniqueId", id);
    }

    public static boolean checkUnique(HttpServletRequest request) {
        boolean bool = false;
        String uniqueId1 = request.getSession().getAttribute("UniqueId").toString();
        String uniqueId2 = request.getParameter("uniqueid");
        if (uniqueId1.equals(uniqueId2)) {
            bool = true;
        }

        return bool;
    }

    public static void clearUnique(HttpServletRequest request) {
        request.getSession().setAttribute("UniqueId", "");
    }
}
