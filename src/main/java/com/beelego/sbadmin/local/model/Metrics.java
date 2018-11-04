package com.beelego.sbadmin.local.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : hama
 * @since : created in  2018/10/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Metrics extends BaseObject {

    private Long mem;

    private Long heap;

    @JsonProperty("mem.free")
    private Long memFree;

    @JsonProperty("heap.committed")
    private Long heapCommitted;

    @JsonProperty("heap.init")
    private Long heapInit;

    @JsonProperty("heap.used")
    private Long heapUsed;

    private Long processors; // ; //: 8,

    @JsonProperty("instance.uptime")
    private Long instanceUptime; // ; //: 2645561,

    private Long uptime; // ; //: 2691428,

    @JsonProperty("systemload.average")
    private Long systemloadAverage; // ; //: -1,

    @JsonProperty("nonheap.committed")
    private Long nonheapCommitted; //: 129856,

    @JsonProperty("nonheap.init")
    private Long nonheapInit; //: 2496,

    @JsonProperty("nonheap.used")
    private Long nonheapUsed; //: 127295,

    private Long nonheap; //: 0,

    @JsonProperty("threads.peak")
    private Long threadsPeak; //: 59,

    @JsonProperty("threads.daemon")
    private Long threadsDaemon; //: 34,


    @JsonProperty("threads.totalStarted")
    private Long threadsotalStarted; //: 327,

    private Long threads; //: 48,

    private Long classes; //: 15357,

    @JsonProperty("classes.loaded")
    private Long classesLoaded; //: 15357,

    @JsonProperty("classes.unloaded")
    private Long classesUnloaded; //: 0,

    @JsonProperty("gc.ps_scavenge.count")
    private Long gcPsScavengeCount; //: 37,

    @JsonProperty("gc.ps_scavenge.time")
    private Long gcPsScavengeTime; //: 597,

    @JsonProperty("gc.ps_marksweep.count")
    private Long gcPsMarksweepCount; //: 3,

    @JsonProperty("gc.ps_marksweep.time")
    private Long gcPsMarksweepTime; //: 643,

    @JsonProperty("integration.channel.errorChannel.errorRate.mean")
    private Long integrationChannelErrorChannelErrorRateMean; //: 0,



}
