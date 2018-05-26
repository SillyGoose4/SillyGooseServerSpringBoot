package com.sillygoose.service.Controller;

import com.sillygoose.service.Mapper.UserMapper;
import com.sillygoose.service.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class controller {
    
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/")
    String home() {
        User user = new User("wjr","wjrwjr","18799137052");
        String res = "";
        //userMapper.createUser(user);
        /*if(userMapper.createUser(user) != 0) {
            System.out.println("Success");
        }else{
            System.out.println("failed");
        }*/
        List<User> userList = userMapper.selectByPhone(user.getUserPhone());
        if(userList.isEmpty()){
            userMapper.createUser(user);

            res = user.toString();
        }else{
            //插入失败
            for (User it : userList
                 ) {
                res += it.toString()+"<br \\>";
            }
        }
        return res;
    }

}
