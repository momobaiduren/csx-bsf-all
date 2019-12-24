package com.yh.csx.bsf.core.base;

/**
 * @author: chejiangyi
 * @version: 2019-05-27 13:44
 **/
public enum Environment {
    dev("开发"),
    //test("测试"),
    prd("生产");

    private String name;
    Environment(String name)
    {
        this.name = name;
    }
}
