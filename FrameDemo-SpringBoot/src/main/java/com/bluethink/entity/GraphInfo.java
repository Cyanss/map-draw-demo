package com.bluethink.entity;

import lombok.Data;

import java.sql.Blob;

/**
 * @auther BlueThink Cyan
 * @create 2019/3/18
 */
@Data
public class GraphInfo {
    /** 图形信息编号 */
    private Integer graphId;
    /** 用户编号 */
    private Integer userId;
    /** 图形类型 */
    private String graphType;
    /** 图形颜色 */
    private String graphColor;
    /** 图形名称 */
    private String graphName;
    /** 图形内容描述 */
    private String graphContent;
//    /** 图形数据 */
//    private byte[] graphData;
    /** 图形数据 */
    private String graphData;

}
