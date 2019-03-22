package com.bluethink.controller;

import com.bluethink.annotation.GraphAuth;
import com.bluethink.dto.GraphDTO;
import com.bluethink.dto.UserDTO;
import com.bluethink.entity.GraphInfo;
import com.bluethink.entity.UserInfo;
import com.bluethink.service.GraphService;
import com.bluethink.service.UserService;
import com.bluethink.util.ResultVOUtil;
import com.bluethink.vo.GraphVO;
import com.bluethink.vo.ResultVO;
import com.bluethink.vo.UserVO;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther BlueThink Cyan
 * @create 2019/3/19
 */
@RestController
@RequestMapping("/graph")
@Slf4j
public class GraphController {
    @Autowired
    private GraphService graphService;

    /**
     * 查询单个
     * @param graphId
     * @return
     */
    @GetMapping("/one")
    public ResultVO<GraphVO> findGraphOne(@RequestParam("graphid") Integer graphId){
        GraphVO graphVO = graphService.findGraphOne(graphId);
        return ResultVOUtil.success(graphVO);
    };

    /**
     * 查询所有
     * @return
     */
    @GetMapping("/all")
    public ResultVO<List<GraphVO>> findGraphAll(){
        List<GraphVO> graphVOList = graphService.findGraphAll();
        return ResultVOUtil.success(graphVOList);
    };

    @GetMapping("/user")
    public ResultVO<List<GraphInfo>> findGraphByUserId(@RequestParam(value = "userid") Integer userId){
        List<GraphInfo> graphInfoList = graphService.findByUserId(userId);
        return ResultVOUtil.success(graphInfoList);
    };
    /**
     * 增加信息
     * @param graphDTO
     * @return
     */
    @PostMapping("/add")
    public ResultVO<GraphInfo> addGraph(@RequestBody GraphDTO graphDTO){
        graphService.addGraph(graphDTO);
        return ResultVOUtil.success("添加成功");
    };

    /**
     * 删除
     * @param graphId
     * @return
     */
    @DeleteMapping("/delete")
    @GraphAuth
    public ResultVO deleteGraphOne(@RequestParam("graphid") Integer graphId){
        graphService.deleteGraphOne(graphId);
        return ResultVOUtil.success("删除成功");
    };

    /**
     * 更新
     * @param graphId
     * @param graphDTO
     * @return
     */
    @PutMapping("/update")
    public ResultVO<GraphInfo> updateGraph(@RequestParam("graphid") Integer graphId,
                                             @RequestBody GraphDTO graphDTO){
        graphService.updateGraph(graphId, graphDTO);
        return ResultVOUtil.success("更新成功");
    };
    
}
