package com.beelego.sbadmin.local.endpointinfo;

/**
 * @author : hama
 * @since : created in  2018/10/25
 */
public abstract class AbstractFactory {

    public abstract Endpoint getEndpoint(String path);


}
