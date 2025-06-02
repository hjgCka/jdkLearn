/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hjg.websocketdemo.websocket;

import java.util.HashSet;
import java.util.Set;

import com.hjg.websocketdemo.websocket.drawboard.DrawboardEndpoint;
import com.hjg.websocketdemo.websocket.echo.EchoEndpoint;
import jakarta.websocket.Endpoint;
import jakarta.websocket.server.ServerApplicationConfig;
import jakarta.websocket.server.ServerEndpointConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExamplesConfig implements ServerApplicationConfig {

    private static final Logger log = LoggerFactory.getLogger(ExamplesConfig.class);

    @Override
    public Set<ServerEndpointConfig> getEndpointConfigs(
            Set<Class<? extends Endpoint>> scanned) {

        log.info("getEndpointConfigs, scanned.size={}", scanned.size());

        Set<ServerEndpointConfig> result = new HashSet<>();

        if (scanned.contains(EchoEndpoint.class)) {
            result.add(ServerEndpointConfig.Builder.create(
                    EchoEndpoint.class,
                    "/websocket/echoProgrammatic").build());
        }

        if (scanned.contains(DrawboardEndpoint.class)) {
            result.add(ServerEndpointConfig.Builder.create(
                    DrawboardEndpoint.class,
                    "/websocket/drawboard").build());
        }

        log.info("getEndpointConfigs, result.size={}", result.size());

        return result;
    }


    @Override
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {
        log.info("getAnnotatedEndpointClasses, scanned.size={}", scanned.size());

        // Deploy all WebSocket endpoints defined by annotations in the examples
        // web application. Filter out all others to avoid issues when running
        // tests on Gump
        Set<Class<?>> results = new HashSet<>();
        for (Class<?> clazz : scanned) {
            if (clazz.getPackage().getName().contains("websocket")) {
                results.add(clazz);
            }
        }

        log.info("getAnnotatedEndpointClasses, results.size={}", results.size());

        return results;
    }
}
