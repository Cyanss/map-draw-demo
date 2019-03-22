package com.bluethink.dto;

import lombok.Data;

/**
 * @auther BlueThink Cyan
 * @create 2019/3/19
 */
@Data
public class GraphDTO {
    /** 用户编号 */
    private Integer userId;
    /** 插件名称 */
    private String graphName;
    /** 插件类型 */
    private String graphType;
    /** 图形颜色 */
    private String graphColor;
    /** 图形内容描述 */
    private String graphContent;
//    /** 图形数据 */
//    private byte[] graphData;
    /** 图形数据 */
    private String graphData;
}
