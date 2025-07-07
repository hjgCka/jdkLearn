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

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.samples.spring.BootstrapDataPopulator;
import org.apache.shiro.samples.spring.CustomConfigDebugFilter;
import org.apache.shiro.samples.spring.DefaultSampleManager;
import org.apache.shiro.samples.spring.config.DebugFilter;
import org.apache.shiro.samples.spring.config.JspViewsConfig;
import org.apache.shiro.samples.spring.config.RemotingServletConfig;
import org.apache.shiro.samples.spring.realm.SaltAwareJdbcRealm;
import org.apache.shiro.spring.config.ShiroAnnotationProcessorConfiguration;
import org.apache.shiro.spring.config.ShiroBeanConfiguration;
import org.apache.shiro.spring.remoting.SecureRemoteInvocationExecutor;
import org.apache.shiro.spring.web.config.*;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Application bean definitions.
 */
@Configuration
@PropertySource("classpath:application.properties")
@Import({ShiroBeanConfiguration.class,
        ShiroAnnotationProcessorConfiguration.class,
        ShiroWebConfiguration.class,
        ShiroWebFilterConfiguration.class,
        JspViewsConfig.class,
        RemotingServletConfig.class,
        ShiroRequestMappingConfig.class})
@ComponentScan("org.apache.shiro.samples.spring")
public class ApplicationConfig {

    /**
     * Populates the sample database with sample users and roles.
     *
     * @param dataSource
     * @return
     */
    @Bean
    protected BootstrapDataPopulator bootstrapDataPopulator(DataSource dataSource) {
        BootstrapDataPopulator populator = new BootstrapDataPopulator();
        populator.setDataSource(dataSource);
        return populator;
    }


    /**
     * Used by the SecurityManager to access security data (users, roles, etc.).
     * Many other realm implementations can be used too (PropertiesRealm,
     * LdapRealm, etc.
     *
     * @param dataSource
     * @return
     */
    @Bean
    protected SaltAwareJdbcRealm jdbcRealm(DataSource dataSource) {

        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("SHA-256");
        credentialsMatcher.setStoredCredentialsHexEncoded(false);

        SaltAwareJdbcRealm jdbcRealm = new SaltAwareJdbcRealm();
        jdbcRealm.setName("jdbcRealm");
        jdbcRealm.setCredentialsMatcher(credentialsMatcher);
        jdbcRealm.setDataSource(dataSource);

        return jdbcRealm;
    }


    /**
     * Let's use some enterprise caching support for better performance.  You can replace this with any enterprise
     * caching framework implementation that you like (Terracotta+Ehcache, Coherence, GigaSpaces, etc
     *
     * @return
     */
    @Bean
    protected EhCacheManager cacheManager() {

        EhCacheManager ehCacheManager = new EhCacheManager();

        // Set a net.sf.ehcache.CacheManager instance here if you already have one.
        // If not, a new one will be created with a default config:
        // ehCacheManager.setCacheManager(...);

        // If you don't have a pre-built net.sf.ehcache.CacheManager instance to inject, but you want
        // a specific Ehcache configuration to be used, specify that here.  If you don't, a default
        //will be used.:
        // ehCacheManager.setCacheManagerConfigFile("classpath:some/path/to/ehcache.xml");

        return ehCacheManager;
    }

    /**
     * Secure Spring remoting:  Ensure any Spring Remoting method invocations can be associated
     * with a Subject for security checks.
     *
     * @param securityManager
     * @return
     */
    @Bean
    protected SecureRemoteInvocationExecutor secureRemoteInvocationExecutor(SecurityManager securityManager) {

        SecureRemoteInvocationExecutor executor = new SecureRemoteInvocationExecutor();
        executor.setSecurityManager(securityManager);

        return executor;
    }


    /**
     * Simulated business-tier "Manager", not Shiro related, just an example
     *
     * @return
     */
    @Bean
    protected DefaultSampleManager sampleManager() {
        return new DefaultSampleManager();
    }

    /**
     * Sample RDBMS data source that would exist in any application - not Shiro related.
     *
     * @return
     */
    @Bean
    protected DriverManagerDataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        dataSource.setUrl("jdbc:hsqldb:mem:shiro-spring");
        dataSource.setUsername("sa");

        return dataSource;
    }

    /**
     * 会被注入到AbstractShiroWebFilterConfiguration的map的bean，它是ShiroWebFilterConfiguration引入。
     * @return
     */
    @Bean
    public org.apache.shiro.samples.spring.config.DebugFilter debugFilter() {
        org.apache.shiro.samples.spring.config.DebugFilter debugFilter = new org.apache.shiro.samples.spring.config.DebugFilter();
        return debugFilter;
    }

    @Bean
    public CustomConfigDebugFilter customConfigDebugFilter(AbstractShiroFilter abstractShiroFilter, DebugFilter debugFilter) {
        CustomConfigDebugFilter customDebugFilter = new CustomConfigDebugFilter();
        customDebugFilter.setAbstractShiroFilter(abstractShiroFilter);
        customDebugFilter.setDebugFilter(debugFilter);
        return customDebugFilter;
    }

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
//        chainDefinition.addPathDefinition("/login.html", "authc"); // need to accept POSTs from the login form
//        chainDefinition.addPathDefinition("/logout", "logout");

        // 添加自定义调试过滤器到最前面
        //chainDefinition.addPathDefinition("/**", "debugFilter");

        chainDefinition.addPathDefinition("/favicon.ico", "anon");
        chainDefinition.addPathDefinition("/logo.png", "anon");
        chainDefinition.addPathDefinition("/shiro.css", "anon");

        chainDefinition.addPathDefinition("/s/login", "debugFilter, anon");
        chainDefinition.addPathDefinition("/s/unauthorized", "debugFilter, anon");
        chainDefinition.addPathDefinition("/s/index", "debugFilter, authc");
        chainDefinition.addPathDefinition("/s/logout", "debugFilter, authc");

        //controller会将请求转发到jsp(通过视图解析器完成)，这个内部的转发也会被shiro所拦截，所以DebugFilter会打印URL: /WEB-INF/resources/login.jsp
        //将对应jsp页面也要加上配置
        chainDefinition.addPathDefinition("/WEB-INF/resources/login.jsp", "anon");
        chainDefinition.addPathDefinition("/WEB-INF/resources/include.jsp", "anon");
        chainDefinition.addPathDefinition("/WEB-INF/resources/sampleIndex.jsp", "authc");

        //allow WebStart to pull the jars for the swing app
        chainDefinition.addPathDefinition("/*.jar", "anon");
        // protected using SecureRemoteInvocationExecutor
        chainDefinition.addPathDefinition("/remoting/**", "anon");

        //如果请求走到上面的filter的话，就不会到这里被debugFilter和authc处理，这样就不会输出日志；
        //如果能到这里，那么debugFilter会输出所有的过滤器链，包括authc
        // /**的配置是兜底的，/s/logout会到这里
        chainDefinition.addPathDefinition("/**", "debugFilter, authc");

        return chainDefinition;
    }


}
