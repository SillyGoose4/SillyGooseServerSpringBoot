package com.sillygoose.service.Controller;

import com.sillygoose.service.Mapper.AlbumMapper;
import com.sillygoose.service.Mapper.CollectTimeMapper;
import com.sillygoose.service.Mapper.GooseMapper;
import com.sillygoose.service.Mapper.UserMapper;
import com.sillygoose.service.Model.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GooseMapper gooseMapper;

    @Autowired
    private CollectTimeMapper collectTimeMapper;

    @Autowired
    private AlbumMapper albumMapper;

    @RequestMapping("/post")
    public String get(@RequestBody String message){
        JSONObject json = JSONObject.fromObject(message);
        JSONObject res = new JSONObject();
        switch (json.getString("Value")){
            case "SIGNIN":
                MessageBox messageBox = dealSignIn(json);
                res.put("Result",messageBox);
                switch (messageBox){
                    case SI_SUCCESS:
                        res.put("Message",dealSignInMsg(json));
                        break;
                    case SI_FAIL:
                        break;
                    case SI_PASSWORDWRONG:
                        break;
                }
                break;
            case "SIGNUP":
                res.put("Result",dealSignUp(json));
                break;
            default:
                break;
        }
        return  res.toString();
    }

    /**
     * 处理登录请求
     * @param msg
     * @return
     */
    private MessageBox dealSignIn(JSONObject msg){
        MessageBox res = MessageBox.SI_NOTFIND;

        if(1 == userMapper.selectCountByPhone(msg.getString("Phone"))){
            User searchuser= userMapper.selectByPhoneOne(msg.getString("Phone"));
            if(msg.getString("Passwd").equals(searchuser.getUserPasswd())){
                res = MessageBox.SI_SUCCESS;
            }else{
                res = MessageBox.SI_PASSWORDWRONG;
            }
        }else{
            res = MessageBox.SI_NOTFIND;
        }
        return res;
    }

    /**
     * 处理注册请求
     * @param msg
     * @return
     */
    private MessageBox dealSignUp(JSONObject msg){
        MessageBox res = null;
        User user;
        Goose goose;
        CollectTime collectTime;
        int userId = 0;
        if(1 == userMapper.selectCountByPhone(msg.getString("Phone"))){
            res = MessageBox.SU_EXISTS;
        }else{
            user = new User(msg.getString("Name"),msg.getString("Passwd"),msg.getString("Phone"));
            userMapper.createUser(user);
            userId = userMapper.selectByPhoneForId(msg.getString("Phone"));
            goose=new Goose(userId);
            goose.InitGoose();
            collectTime = new CollectTime(userId);
            collectTime.InitTime(new Date());
            int a = gooseMapper.insert(goose);
            int b = collectTimeMapper.insert(collectTime);
            if(a != 0 && b != 0){
                res = MessageBox.SU_SUCCESS;
            }else{
                res = MessageBox.SU_FAIL;
            }
        }
        return res;
    }

    /**
     * 向登录请求返回数据
     * @param msg
     * @return
     */
    private JSONObject dealSignInMsg(JSONObject msg){
        JSONObject temp = new JSONObject();
        User user;
        Goose goose;
        CollectTime collectTime;
        List<Album> albumList = new ArrayList<Album>();
        int userId = 0;
        user = userMapper.selectByPhoneOne(msg.getString("Phone"));
        userId = user.getUserId();
        temp.put("User",user);
        collectTime = collectTimeMapper.selectById(userId);
        temp.put("CollectTime",collectTime);
        goose = gooseMapper.selectById(userId);
        temp.put("Goose",goose);
        albumList = albumMapper.selectByUserId(userId);
        temp.put("Album",albumList);
        return temp;
    }

}
