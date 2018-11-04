package com.beelego.sbadmin.local.endpointinfo;

import java.util.Map;

/**
 * @author : hama
 * @since : created in  2018/10/25
 */
public class EndpointAbstractFactory extends AbstractFactory {

    private EndpointAbstractFactory() {
    }

    private static EndpointAbstractFactory instance = null;

    public static EndpointAbstractFactory getInstance(Map<String, String> patterns) {
        if (null == instance) {
            instance = new EndpointAbstractFactory(patterns);
        }
        return instance;
    }


    private Map<String, String> patterns;

    public EndpointAbstractFactory(Map<String, String> maps) {
        this.patterns = maps;
    }


    @Override
    public Endpoint getEndpoint(String path) {
        String endpoint = patterns.get(path);
        switch (endpoint) {
            case "metrics":
                return new EndpointMetrics();
        }
        return new EndpointDoNothing();
    }
}
