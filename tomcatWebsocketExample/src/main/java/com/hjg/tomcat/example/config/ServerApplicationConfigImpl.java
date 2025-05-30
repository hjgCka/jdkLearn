package com.hjg.tomcat.example.config;

/**
 * @Description
 * @Author hjg
 * @Date 2025-05-30 12:04
 */
public class ServerApplicationConfigImpl  {

//    private static final Logger logger = LoggerFactory.getLogger(ServerApplicationConfigImpl.class);
//
//    public ServerApplicationConfigImpl() {
//        logger.info("创建ServerApplicationConfigImpl");
//    }
//
//    @Override
//    public Set<ServerEndpointConfig> getEndpointConfigs(
//            Set<Class<? extends Endpoint>> scanned) {
//
//        logger.info("getEndpointConfigs scanned size : {}", scanned.size());
//
//        Set<ServerEndpointConfig> result = new HashSet<>();
//
//        if (scanned.contains(EchoEndpoint.class)) {
//            result.add(ServerEndpointConfig.Builder.create(
//                    EchoEndpoint.class,
//                    "/websocket/echoProgrammatic")
//                    .configurator(new SpringEndpointConfigurator())
//                    .build());
//        }
//
//        if (scanned.contains(DrawboardEndpoint.class)) {
//            result.add(ServerEndpointConfig.Builder.create(
//                    DrawboardEndpoint.class,
//                    "/websocket/drawboard")
//                    .configurator(new SpringEndpointConfigurator())
//                    .build());
//        }
//
//        return result;
//    }
//
//
//    @Override
//    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {
//        logger.info("getAnnotatedEndpointClasses scanned size : {}", scanned.size());
//
//        // Deploy all WebSocket endpoints defined by annotations in the examples
//        // web application. Filter out all others to avoid issues when running
//        // tests on Gump
//        Set<Class<?>> results = new HashSet<>();
//        results = scanned.stream().filter(c -> !c.isAnnotationPresent(Component.class))
//                .collect(Collectors.toSet());
////        for (Class<?> clazz : scanned) {
////            if (clazz.getPackage().getName().startsWith("websocket.")) {
////                results.add(clazz);
////            }
////        }
//
//        logger.info("getAnnotatedEndpointClasses results size : {}", results.size());
//
//        return results;
//    }
}
