package com.hjg.util;

import com.hjg.aop.StatusCheck;

/**
 * @Description
 * @Author hjg
 * @Date 2026-01-16 11:25
 */
public class UserUtil {

    @StatusCheck(urlIndex = 0)
    public static String getNameById(String id) {
        String name = "Jack";
        return name;
    }
}
