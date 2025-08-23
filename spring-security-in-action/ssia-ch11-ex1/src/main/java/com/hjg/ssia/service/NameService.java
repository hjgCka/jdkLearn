package com.hjg.ssia.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author hjg
 * @Date 2025-08-23 23:49
 */
@Service
public class NameService {

    private Map<String, List<String>> secretNames = Map.of(
            "john", List.of("Jo", "Perfecto"),
            "bill", List.of("Billy")
    );

    @PreAuthorize("hasAuthority('write')")
    public String getName() {
        return "Fantastico";
    }

    @PreAuthorize(
            "#name == authentication.principal.username"
    )
    public List<String> getSecretNames(String name) {
        return secretNames.get(name);
    }
}
