package com.bluethink.dao;

import com.bluethink.dto.UserDTO;
import com.bluethink.vo.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @auther BlueThink Cyan
 * @create 2019/3/19
 */
public interface UserInfoDAO {
    /** 查询所有 */
    List<UserVO> queryUserInfo();
    /** 查询单个 */
    UserVO queryUserInfoByUserId(Integer userId);
    /** 通过用户名查询 */
    UserVO queryUserInfoByName(String userName);
    /** 增加 */
    Integer insertUserInfo(UserVO userVO);
    /** 删除 */
    Integer deleteUserInfo(Integer userId);
    /** 修改 */
    Integer updateUserInfo(@Param("userId") Integer userId, @Param("userDTO") UserDTO userDTO);

}
