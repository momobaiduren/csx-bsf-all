package com.yh.csx.bsf.shardingjdbc;

import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.Collection;

/**
 * @author: chejiangyi
 * @version: 2019-10-24 14:17
 * 数据源分库算法
 *
 **/
public class DataSourceShardingAlgorithm implements org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm {
    @Override
    public Collection<String> doSharding(Collection collection, HintShardingValue hintShardingValue) {
        return hintShardingValue.getValues();
    }
}
