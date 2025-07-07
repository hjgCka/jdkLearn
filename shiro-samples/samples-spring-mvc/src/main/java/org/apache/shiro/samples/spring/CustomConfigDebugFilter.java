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
package org.apache.shiro.samples.spring;

import org.apache.shiro.samples.spring.config.DebugFilter;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.InitializingBean;

/**
 * @Description
 * @Author hjg
 * @Date 2025-07-05 23:05
 */
public class CustomConfigDebugFilter implements InitializingBean {

    //ShiroFilterFactoryBean shiroFilterFactoryBean;
    /**
     * ShiroFilterFactoryBean是一个FactoryBean，将它的产品注入就行。
     */
    AbstractShiroFilter abstractShiroFilter;
    DebugFilter debugFilter;

    /*public void setShiroFilterFactoryBean(ShiroFilterFactoryBean shiroFilterFactoryBean) {
        this.shiroFilterFactoryBean = shiroFilterFactoryBean;
    }*/

    public void setAbstractShiroFilter(AbstractShiroFilter abstractShiroFilter) {
        this.abstractShiroFilter = abstractShiroFilter;
    }

    public void setDebugFilter(DebugFilter debugFilter) {
        this.debugFilter = debugFilter;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        FilterChainResolver resolver = abstractShiroFilter.getFilterChainResolver();
        debugFilter.setFilterChainResolver(resolver);
    }
}
