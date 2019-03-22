package com.bluethink.service;

import com.bluethink.dto.UserDTO;
import com.bluethink.entity.UserInfo;
import com.bluethink.vo.UserVO;
import com.github.pagehelper.PageInfo;

/**
 * @auther BlueThink Cyan
 * @create 2019/3/19
 */
public interface UserService {
    /** 查询单个 */
    UserVO findUserOne(Integer userId);
    /** 查询所有 */
    PageInfo<UserVO> findUserAll(Integer page, Integer size);
    /** 增加 */
    UserVO addUser(UserDTO userDTO);
    /** 删除 */
    void deleteUserOne(Integer userId);
    /** 修改 */
    void updateUser(Integer userId, UserDTO userDTO);
    /** 通过用户名查询 */
    UserVO findUserByName(String userName);
    /** 登陆 */
    UserVO login(String userName, String password);
}
