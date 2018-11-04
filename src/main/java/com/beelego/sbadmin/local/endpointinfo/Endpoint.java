package com.beelego.sbadmin.local.endpointinfo;

import java.io.IOException;

/**
 * @author : hama
 * @since : created in  2018/10/25
 */
public interface Endpoint {

    Object convert(String data) throws IOException;
}
