package com.yh.csx.bsf.elasticsearch.impl;

import com.yh.csx.bsf.core.common.Collector;

/**
 * @author Huang Zhaoping
 */
public class ElasticSearchMonitor {

    private static final String name = "elasticSearch.info";

    public static Collector.Hook hook() {
        return Collector.Default.hook(name + ".hook");
    }
}
