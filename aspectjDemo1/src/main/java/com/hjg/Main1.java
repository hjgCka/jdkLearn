package com.hjg;

import com.hjg.util.UserUtil;

/**
 * @Description
 * @Author hjg
 * @Date 2026-01-16 15:34
 */
public class Main1 {

    public static void main(String[] args) {
        String name = UserUtil.getNameById("12");

        System.out.println("get new result = " + name);
    }
}
