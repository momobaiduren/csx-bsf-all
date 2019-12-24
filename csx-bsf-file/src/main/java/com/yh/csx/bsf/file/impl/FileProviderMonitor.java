package com.yh.csx.bsf.file.impl;

import com.yh.csx.bsf.core.common.Collector;

/**
 * @author Huang Zhaoping
 */
public class FileProviderMonitor {

    private static String name = "file.info";

    public static Collector.Hook hook() {
        return Collector.Default.hook(name + ".hook");
    }
}
