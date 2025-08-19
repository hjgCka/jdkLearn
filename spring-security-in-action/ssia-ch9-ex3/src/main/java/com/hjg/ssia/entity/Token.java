package com.hjg.ssia.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * @Description
 * @Author hjg
 * @Date 2025-08-19 11:03
 */
@Data
@Entity
public class Token {

    @Id
    //依赖数据库自增字段，适用于支持自增的数据库（如 MySQL）
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String identifier;
    private String token;
}
