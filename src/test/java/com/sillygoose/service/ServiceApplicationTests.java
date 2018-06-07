package com.sillygoose.service;

import com.sillygoose.service.Mapper.UserMapper;
import com.sillygoose.service.Model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void contextLoads() {

    }

    @Test
    public void testUserMapper(){
        User user = new User("wjr","wjrwjr","18799137052");
        //userMapper.createUser(user);
        /*if(userMapper.createUser(user) != 0) {
            System.out.println("Success");
        }else{
            System.out.println("failed");
        }*/
        List<User> userList = userMapper.selectByPhone(user.getUserPhone());
        if(userList.isEmpty()){
            userMapper.createUser(user);
        }else{
            //插入失败
        }

    }

    @Test
    public void testSearchService(){

    }

    @Test
    public void testCreateService(){


    }

}
