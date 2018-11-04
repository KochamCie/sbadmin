package com.beelego.sbadmin.local.endpointinfo;

import com.beelego.sbadmin.local.model.Metrics;
import com.beelego.sbadmin.local.model.BaseObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.Date;

/**
 * @author : hama
 * @since : created in  2018/10/25
 */
@Slf4j
public class EndpointMetrics extends BaseObject implements Endpoint {

    @Override
    public Object convert(String data) throws IOException {
        if(StringUtils.isBlank(data)){
            return null;
        }
        log.info("convert in : {}", data);
        Metrics metrics = readValue(data, Metrics.class);

        if(metrics.getMemFree()>715264){
            log.error("mem free ooooooo : {}, {}", metrics.getMemFree(), new Date().toLocaleString());
        }
        return metrics;
    }
}
