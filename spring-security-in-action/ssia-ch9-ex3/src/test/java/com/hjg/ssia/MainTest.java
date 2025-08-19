package com.hjg.ssia;

import com.hjg.ssia.entity.Token;
import com.hjg.ssia.repository.JpaTokenRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description
 * @Author hjg
 * @Date 2025-08-19 12:46
 */
@SpringBootTest
public class MainTest {

    @Autowired
    private JpaTokenRepository jpaTokenRepository;

    @Test
    public void saveToken() {
        Token dbToken = new Token();
        dbToken.setToken("222222222");
        dbToken.setIdentifier("11111111111");
        jpaTokenRepository.save(dbToken);
    }
}
