package com.bluethink.service.Impl;

import com.bluethink.dao.GraphInfoDAO;
import com.bluethink.dto.GraphDTO;
import com.bluethink.entity.GraphInfo;
import com.bluethink.enums.ResultEnum;
import com.bluethink.exception.FrameException;
import com.bluethink.service.GraphService;
import com.bluethink.service.UserService;
import com.bluethink.vo.GraphVO;
import com.bluethink.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @auther BlueThink Cyan
 * @create 2019/3/19
 */
@Service
@Slf4j
public class GraphServiceImpl implements GraphService {
    @Autowired
    private GraphInfoDAO graphInfoDAO;
    @Autowired
    private UserService userService;
    @Override
    public GraphVO findGraphOne(Integer graphId) {
        return findOne(graphId);
    }

    @Override
    public List<GraphVO> findGraphAll() {
        return graphInfoDAO.queryGraphInfo();

    }
    @Transactional
    @Override
    public void addGraph(GraphDTO graphDTO) {
        checkGraphContent(graphDTO);
        if (graphDTO.getGraphName() == null) {
            log.error("【图形名不能为空】");
            throw new FrameException(ResultEnum.GRAPH_NAME_IS_NULL);
        }
        /** 处理外键关系 */
        UserVO userVO = userService.findUserOne(graphDTO.getUserId());
        if (userVO == null) {
            log.error("【用户信息不存在】");
            throw new FrameException(ResultEnum.USER_INFO_NOT_EXIT);
        }
        GraphVO result = graphInfoDAO.queryGraphInfoByName(graphDTO.getGraphName());
        if (result != null) {
            log.error("【图形名已存在】");
            throw new FrameException(ResultEnum.GRAPH_NAME_IS_EXIT);
        }
        graphInfoDAO.insertGraphInfo(graphDTO);
    }
    @Transactional
    @Override
    public void deleteGraphOne(Integer graphId) {
        findOne(graphId);
        graphInfoDAO.deleteGraphInfo(graphId);
    }
    @Transactional
    @Override
    public void updateGraph(Integer graphId, GraphDTO graphDTO) {
        findOne(graphId);
        graphInfoDAO.updateGraphInfo(graphId, graphDTO);
    }

    @Override
    public List<GraphInfo> findByUserId(Integer userId) {
        if (userId == null) {
            log.error("【用户编号不能为空】");
            throw new FrameException(ResultEnum.USER_ID_IS_NULL);
        }
        List<GraphInfo> graphInfoList = graphInfoDAO.queryGraphInfoByUserId(userId);
        if (graphInfoList == null || graphInfoList.isEmpty()) {
            log.error("【当前用户没有存储数据】");
            throw new FrameException(ResultEnum.DATA_IS_NULL);
        }
        return graphInfoDAO.queryGraphInfoByUserId(userId);
    }
    private GraphVO findOne(Integer graphId) {
        if (graphId == null) {
            log.error("【图形编号不能为空】");
            throw new FrameException(ResultEnum.GRAPH_ID_IS_NULL);
        }
        GraphVO graphVO = graphInfoDAO.queryGraphInfoById(graphId);
        if (graphVO == null) {
            log.error("【图形信息不存在】");
            throw new FrameException(ResultEnum.GRAPH_INFO_NOT_EXIT);
        }
        return graphVO;
    }
    private void checkGraphContent(GraphDTO graphDTO) {
        if (graphDTO.getGraphType() == null) {
            log.error("【图形类型不能为空】");
            throw new FrameException(ResultEnum.GRAPH_TYPE_IS_NULL);
        }
        if (graphDTO.getGraphColor() == null) {
            log.error("【图形颜色不能为空】");
            throw new FrameException(ResultEnum.GRAPH_COLOR_IS_NULL);
        }
    }
}
