package com.sillygoose.service.Mapper;

import com.sillygoose.service.Model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface UserMapper {
    int createUser(User record);

    List<User> selectByPhone(String userPhone);

    User selectById(Integer userId);

    int selectCountByPhone(String userPhone);

    User selectByPhoneOne(String userPhone);

    int selectByPhoneForId(String userPasswd);

    int updateStatus(User record);

    int updateSignInTime(User record);
}
