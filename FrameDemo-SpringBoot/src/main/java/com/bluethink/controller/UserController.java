package com.bluethink.controller;

import com.bluethink.annotation.LoginAndRegister;
import com.bluethink.dto.UserDTO;
import com.bluethink.entity.UserInfo;
import com.bluethink.service.UserService;
import com.bluethink.util.ResultVOUtil;
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
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 查询一条信息
     * @param userId
     * @return
     */
    @GetMapping("/one")
    public ResultVO<UserVO> findUserOne(@RequestParam("userid") Integer userId){
        UserVO userVO  = userService.findUserOne(userId);
        return ResultVOUtil.success(userVO);
    };

    /**
     * 查询所有信息 分页
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/page")
    public ResultVO<List<UserVO>> findUserAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                              @RequestParam(value = "size", defaultValue = "10") Integer size){
        PageInfo<UserVO> pageInfo = userService.findUserAll(page, size);
        return ResultVOUtil.success(pageInfo);
    };

    /**
     * 增加一条信息
     * @param UserDTO
     * @return
     */
    @PostMapping("/add")
    @LoginAndRegister
    public ResultVO<UserVO> addUser(@RequestBody UserDTO UserDTO){
        UserVO userVO = userService.addUser(UserDTO);
        return ResultVOUtil.success(userVO);
    };

    /**
     * 删除一条
     * @param userId
     * @return
     */
    @DeleteMapping("/delete")
    public ResultVO deleteUserOne(@RequestParam("userid") Integer userId){
        userService.deleteUserOne(userId);
        return ResultVOUtil.success("删除成功");
    };

    /**
     * 修改一条信息
     * @param userId
     * @param UserDTO
     * @return
     */
    @PutMapping("/update")
    public ResultVO updateUser(@RequestParam("userid") Integer userId,
                                         @RequestBody UserDTO UserDTO){
        userService.updateUser(userId, UserDTO);
        return ResultVOUtil.success("更新成功");
    };

    /**
     * 通过用户名查询
     * @param userName
     * @return
     */
    @GetMapping("/name")
    public ResultVO<UserVO> findUserByName(@RequestParam("username") String userName){
        UserVO userVO = userService.findUserByName(userName);
        return ResultVOUtil.success(userVO);
    };

    /**
     * 通过用户名查询
     * @param userName
     * @return
     */
    @GetMapping("/login")
    @LoginAndRegister
    public ResultVO<UserVO> login(@RequestParam("username") String userName,
                                  @RequestParam("password") String password){
        UserVO userVO = userService.login(userName, password);
        return ResultVOUtil.success("登陆成功", userVO);
    };
}
