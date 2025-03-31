package com.hjg;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Description
 * @Author hjg
 * @Date 2025-03-31 17:26
 */
@SpringBootTest
@AutoConfigureMockMvc
public class SpringBoot3TestApp {

    @Test
    void testHello(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/hello")).andExpect(status().isOk())
                .andExpect(content().string("hello Jack"));
    }
}
