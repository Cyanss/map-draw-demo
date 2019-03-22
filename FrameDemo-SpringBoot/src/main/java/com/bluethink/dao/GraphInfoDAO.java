package com.bluethink.dao;

import com.bluethink.dto.GraphDTO;
import com.bluethink.entity.GraphInfo;
import com.bluethink.vo.GraphVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @auther BlueThink Cyan
 * @create 2019/3/19
 */
public interface GraphInfoDAO {
    /** 查询单个 */
    GraphVO queryGraphInfoById(Integer graphId);
    /** 查询所有 */
    List<GraphVO> queryGraphInfo();
    /** 增加 */
    Integer insertGraphInfo(GraphDTO graphDTO);
    /** 删除 */
    void deleteGraphInfo(Integer graphId);
    /** 修改 */
    Integer updateGraphInfo(@Param("graphId")Integer graphId, @Param("graphDTO") GraphDTO graphDTO);
    /** 通过用户编号查找图形信息 */
    List<GraphInfo> queryGraphInfoByUserId(Integer userId);
    /** 通过插件名称查找信息 */
    GraphVO queryGraphInfoByName(String graphName);
}
