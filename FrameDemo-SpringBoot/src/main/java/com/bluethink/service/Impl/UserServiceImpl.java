package com.bluethink.service.Impl;

import com.bluethink.dao.UserInfoDAO;
import com.bluethink.dto.UserDTO;
import com.bluethink.entity.GraphInfo;
import com.bluethink.entity.UserInfo;
import com.bluethink.enums.ResultEnum;
import com.bluethink.exception.FrameException;
import com.bluethink.service.GraphService;
import com.bluethink.service.UserService;
import com.bluethink.util.BeanCopyUtils;
import com.bluethink.vo.GraphVO;
import com.bluethink.vo.UserVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoDAO userInfoDAO;
    @Autowired
    private GraphService graphService;

    @Override
    public UserVO findUserOne(Integer userId) {
        return findOne(userId);
    }

    @Override
    public PageInfo<UserVO> findUserAll(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<UserVO> userVOList = userInfoDAO.queryUserInfo();
        return new PageInfo<>(userVOList);
    }
    @Transactional
    @Override
    public UserVO addUser(UserDTO userDTO) {
        if (userDTO.getUserName() == null) {
            log.error("【用户名不能为空】");
            throw new FrameException(ResultEnum.USER_NAME_IS_NULL);
        }
        checkName(userDTO.getUserName());
//        checkPhone(userDTO);
        UserVO userVO = new UserVO();
        BeanCopyUtils.copyProperties(userDTO, userVO);
        userInfoDAO.insertUserInfo(userVO);
        return userVO;
    }
    @Transactional
    @Override
    public void deleteUserOne(Integer userId) {
        findOne(userId);
        /** 处理外键关系 */
        List<GraphInfo> graphInfoList = graphService.findByUserId(userId);
        if (graphInfoList.size() > 0) {
            log.error("【该用户存在图形信息不可删除】");
            throw new FrameException(ResultEnum.USER_DELETE_ILLEGAL_OPERATION);
        }
        userInfoDAO.deleteUserInfo(userId);
    }
    @Transactional
    @Override
    public void updateUser(Integer userId, UserDTO userDTO) {
        findOne(userId);
        if (userDTO.getUserName() != null){
            checkName(userDTO.getUserName());
        }
        userInfoDAO.updateUserInfo(userId, userDTO);
    }

    @Override
    public UserVO findUserByName(String userName) {
        UserVO userVO = userInfoDAO.queryUserInfoByName(userName);
        if (userVO == null) {
            log.error("【用户信息不存在】");
            throw new FrameException(ResultEnum.USER_INFO_NOT_EXIT);
        }
        return userVO;
    }

    @Override
    public UserVO login(String username, String password) {
        UserVO userVO = findUserByName(username);
        if ( password == null || "".equals(password)) {
            log.error("【密码输入为空】");
            throw new FrameException(ResultEnum.PASSWORD_IS_NULL);
        }
        if (!password.equals(userVO.getUserPassword())){
            log.error("【密码输入错误】");
            throw new FrameException(ResultEnum.PASSWORD_IS_WRONG);
        }
        return userVO;
    }

    private UserVO findOne(Integer userId){
        if (userId == null) {
            log.error("【用户编号不能为空】");
            throw new FrameException(ResultEnum.USER_ID_IS_NULL);
        }
        UserVO userVO = userInfoDAO.queryUserInfoByUserId(userId);
        if (userVO == null) {
            log.error("【用户信息不存在】");
            throw new FrameException(ResultEnum.USER_INFO_NOT_EXIT);
        }
        return userVO;
    }

    private void checkName(String userName){
        if (userName == null || "".equals(userName)) {
            log.error("【用户名称为空】");
            throw new FrameException(ResultEnum.USER_NAME_IS_NULL);
        }
        UserVO userVO = userInfoDAO.queryUserInfoByName(userName);
        if (userVO != null) {
            log.error("【用户名已存在】");
            throw new FrameException(ResultEnum.USER_NAME_IS_EXIT);
        }
    }

//    private void checkPhone(UserDTO userDTO) {
//        if (userDTO.getUserPhone() == null) {
//            log.error("【手机号不能为空】");
//            throw new FrameException(ResultEnum.USER_PHONE_IS_NULL);
//        }
//    }
}
