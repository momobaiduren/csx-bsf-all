package com.yh.csx.bsf.demo.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;

/**
 * @author Huang Zhaoping
 * @version 2019/5/22
 */
@JobHandler("helloJob")
public class HelloJob extends IJobHandler {
    @Override
    public ReturnT<String> execute(String s) throws Exception {
        System.out.println("hello job run with: " + s);
        return ReturnT.SUCCESS;
    }
}
