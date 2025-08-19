package com.hjg.ssia.csrf;

import com.hjg.ssia.entity.Token;
import com.hjg.ssia.repository.JpaTokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

/**
 * @Description
 * @Author hjg
 * @Date 2025-08-19 11:11
 */
@Component
public class CustomCsrfTokenRepository implements CsrfTokenRepository {

    private static final Logger logger = LoggerFactory.getLogger(CustomCsrfTokenRepository.class);

    private final JpaTokenRepository jpaTokenRepository;

    public CustomCsrfTokenRepository(JpaTokenRepository jpaTokenRepository) {
        this.jpaTokenRepository = jpaTokenRepository;
    }

    @Override
    public CsrfToken generateToken(HttpServletRequest request) {
        String uuid = UUID.randomUUID().toString();
        logger.info("token generated = {}", uuid);
        return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", uuid);
    }

    @Override
    public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
        String identifier = request.getHeader("X-IDENTIFIER");
        logger.info("saveToken identifier = {}", identifier);

        Optional<Token> existingToken = jpaTokenRepository.findTokenByIdentifier(identifier);
        if (existingToken.isPresent()) {
            Token dbToken = existingToken.get();
            //如果存在，用新生成的token进行更新
            dbToken.setToken(token.getToken());
            logger.info("updateToken token={}", dbToken.getToken());
        } else {
            //不存在就进行保存
            Token dbToken = new Token();
            dbToken.setToken(token.getToken());
            dbToken.setIdentifier(identifier);
            jpaTokenRepository.save(dbToken);
            logger.info("save dbToken = {}", dbToken);
        }
    }

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        String identifier = request.getHeader("X-IDENTIFIER");
        logger.info("loadToken identifier = {}", identifier);

        Optional<Token> existingToken = jpaTokenRepository.findTokenByIdentifier(identifier);
        if (existingToken.isPresent()) {
            Token dbToken = existingToken.get();
            return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", dbToken.getToken());
        } else {
            return null;
        }
    }
}
