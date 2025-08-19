package com.hjg.ssia.repository;

import com.hjg.ssia.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @Description
 * @Author hjg
 * @Date 2025-08-19 11:08
 */
public interface JpaTokenRepository extends JpaRepository<Token, Integer> {

    Optional<Token> findTokenByIdentifier(String identifier);
}
