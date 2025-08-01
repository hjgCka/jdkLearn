/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.shiro.samples.spring.config;

import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.filter.mgt.NamedFilterList;
import org.apache.shiro.web.servlet.OncePerRequestFilter;
import org.apache.shiro.web.servlet.ProxiedFilterChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.stream.Collectors;

/**
 * 查看url对应的过滤器
 */
public class DebugFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(DebugFilter.class);

    private FilterChainResolver filterChainResolver;

    public void setFilterChainResolver(FilterChainResolver filterChainResolver) {
        this.filterChainResolver = filterChainResolver;
    }

    @Override
    protected void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            FilterChain resolvedChain = filterChainResolver.getChain(request, response, chain);
            if (resolvedChain instanceof ProxiedFilterChain) {
                inspectFilterChain((ProxiedFilterChain) resolvedChain, request);
            }
        } catch (Exception e) {
            LOGGER.warn("Failed to inspect filter chain", e);
        }

        chain.doFilter(request, response);
    }

    private void inspectFilterChain(ProxiedFilterChain proxiedChain, ServletRequest request) {
        String url = ((HttpServletRequest) request).getRequestURI();

        try {
            Field filtersField = ProxiedFilterChain.class.getDeclaredField("filters");
            filtersField.setAccessible(true);
            NamedFilterList filters = (NamedFilterList) filtersField.get(proxiedChain);

            String configName = filters.getName();
            String filterClasses = filters.stream()
                    .map(e -> e.getClass().getName())
                    .collect(Collectors.joining(" → "));

            LOGGER.debug("┌────── Filter Chain Debug ───────");
            LOGGER.debug("│ URL: {}", url);
            LOGGER.debug("│ Chain Name: {}", configName);
            LOGGER.debug("│ Filters: {}", filterClasses);
            LOGGER.debug("└────────────────────────────────");
        } catch (Exception e) {
            LOGGER.warn("Failed to inspect filter chain", e);
        }
    }

}
