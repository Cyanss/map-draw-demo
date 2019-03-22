package com.bluethink.vo;

import lombok.Data;

/**
 * @auther BlueThink Cyan
 * @create 2019/3/19
 */
@Data
public class GraphVO {
    /** 图形编号 */
    private Integer graphId;
    /** 用户编号 */
    private Integer userId;
    /** 图形名称 */
    private String graphName;
    /** 图形颜色 */
    private String graphColor;
    /** 图形类型 */
    private String graphType;
    /** 图形内容描述 */
    private String graphContent;
//    /** 图形数据 */
//    private byte[] graphData;
    /** 图形数据 */
    private String graphData;
}

