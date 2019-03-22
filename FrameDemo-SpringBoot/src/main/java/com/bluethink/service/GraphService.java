package com.bluethink.service;

import com.bluethink.dto.GraphDTO;
import com.bluethink.entity.GraphInfo;
import com.bluethink.vo.GraphVO;
import com.bluethink.vo.UserVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @auther BlueThink Cyan
 * @create 2019/3/19
 */
public interface GraphService {
    /** 查询单个 */
    GraphVO findGraphOne(Integer graphId);
    /** 查询所有 */
    List<GraphVO> findGraphAll();
    /** 增加 */
    void addGraph(GraphDTO graphDTO);
    /** 删除 */
    void deleteGraphOne(Integer graphId);
    /** 修改 */
    void updateGraph(Integer graphId, GraphDTO graphDTO);
    /** 通过用户编号查找图形信息 */
    List<GraphInfo> findByUserId(Integer userId);
}
