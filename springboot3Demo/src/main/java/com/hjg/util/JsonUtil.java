package com.hjg.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Description
 * @Author hjg
 * @Date 2025-05-29 17:05
 */
public class JsonUtil {

    public static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
    }
}
