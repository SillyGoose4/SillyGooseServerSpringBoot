package com.sillygoose.service;

import com.sillygoose.service.Mapper.CollectTimeMapper;
import com.sillygoose.service.Mapper.UserMapper;
import com.sillygoose.service.Model.CollectTime;
import com.sillygoose.service.Model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CollectTimeMapper collectTimeMapper;

    @Test
    public void contextLoads() {

    }
/*
    @Test
    public void testUserMapper(){
        User searchuser= userMapper.selectByPhoneOne("18051072519");
        userMapper.updateStatus(searchuser);

    }

    @Test
    public void testCollectTimeMapper(){
        CollectTime collectTime = collectTimeMapper.selectById(9);
        System.out.println(collectTime.getCloudLasttime());
        collectTime.setCloudLasttime(new Date());
        collectTimeMapper.updateByOne(collectTime);
        System.out.println(collectTime.getCloudLasttime());
    }*/

}