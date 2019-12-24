package com.yh.csx.bsf.health.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 报警消息
 * @author: chejiangyi
 * @version: 2019-07-24 13:44
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message{
    private EnumWarnType warnType;
    private String title;
    private String content;
}