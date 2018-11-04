package com.beelego.sbadmin.local.endpointinfo;

import com.beelego.sbadmin.local.model.BaseObject;

import java.io.IOException;

/**
 * @author : hama
 * @since : created in  2018/10/25
 */
public class EndpointDoNothing extends BaseObject implements Endpoint {

    @Override
    public Object convert(String data) throws IOException {
        return data;
    }
}
