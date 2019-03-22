package com.bluethink.dao;

import com.bluethink.dto.GraphDTO;
import com.bluethink.dto.UserDTO;
import com.bluethink.vo.UserVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * @auther BlueThink Cyan
 * @create 2019/3/19
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserInfoDAOTest {
    @Autowired
    private UserInfoDAO userInfoDAO;
    @Test
    public void queryUserInfo() {
    }

    @Test
    public void queryUserInfoByUserId() {
    }

    @Test
    public void queryUserInfoByName() {
    }

    @Test
    @Transactional
    public void insertUserInfo() {
        UserVO userDTO = new UserVO();
        userDTO.setUserName("name");
        userDTO.setUserSex(1);
        userDTO.setUserAge(18);
        userDTO.setUserPhone("13298359891");
        userDTO.setUserAuth("common");
        userDTO.setUserEmail("name@136.com");
        userDTO.setUserPassword("*password");
        Integer id = userInfoDAO.insertUserInfo(userDTO);
        Assert.assertNotNull(id);
    }

    @Test
    @Transactional
    public void deleteUserInfo() {
        Integer id = userInfoDAO.deleteUserInfo(1);
        Assert.assertNotNull(id);
    }

    @Test
    @Transactional
    public void updateUserInfo() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName("cyan");
        userDTO.setUserSex(1);
        userDTO.setUserAge(18);
        userDTO.setUserPhone("13298359897");
        userDTO.setUserAuth("admin");
        userDTO.setUserEmail("cyan@136.com");
        userDTO.setUserPassword("*password");
        Integer id = userInfoDAO.updateUserInfo(1, userDTO);
        Assert.assertNotNull(id);
    }
}