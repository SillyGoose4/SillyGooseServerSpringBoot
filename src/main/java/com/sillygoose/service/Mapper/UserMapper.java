package com.sillygoose.service.Mapper;

import com.sillygoose.service.Model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface UserMapper {
    int createUser(User record);

    List<User> selectByPhone(String userPhone);

    User selectById(Integer userId);
}
