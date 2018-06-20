package com.sillygoose.service.Controller;

import com.sillygoose.service.JsonDateValueProcessor;
import com.sillygoose.service.Mapper.AlbumMapper;
import com.sillygoose.service.Mapper.CollectTimeMapper;
import com.sillygoose.service.Mapper.GooseMapper;
import com.sillygoose.service.Mapper.UserMapper;
import com.sillygoose.service.Model.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
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

    Date lastSignInTime;

    Date currentSignInTime;

    /**
     * 涉及登录、注册、退出登录
     * @param message
     * @return
     */
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
            case "SIGNOUT":
                User temp = userMapper.selectByPhoneOne(json.getString("Phone"));
                res.put("Result",MessageBox.SO_SUCCESS);
                userMapper.updateStatus(temp);
                break;
            default:
                break;
        }
        return  res.toString();
    }

    /**
     * 更新
     * 涉及更新goose各个数据、collect time各个数据
     * @param msg
     * @return
     */
    @RequestMapping("/update")
    public String update(@RequestBody String msg){
        JSONObject data = JSONObject.fromObject(msg);
        JSONObject result = new JSONObject();
        switch (data.getString("Value")){
            case "GOOSE":
                if(dealUpdateGoose(data)){
                    result.put("Result",MessageBox.UG_SUCCESS);
                }else{
                    result.put("Result",MessageBox.SYS_ERROR);
                }
                break;
            case "COLLECTTIME":
                result.put("Result",dealUpdateCollectTime(data));
                break;
        }
        return result.toString();
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
                if(searchuser.getUserStatus() == 0) {
                    res = MessageBox.SI_SUCCESS;
                    userMapper.updateStatus(searchuser);
                    lastSignInTime = searchuser.getLastSignIn();
                    currentSignInTime = new Date();
                    searchuser.setLastSignIn(currentSignInTime);
                    userMapper.updateSignInTime(searchuser);
                }else{
                    res = MessageBox.SI_ALREADYSIGNIN;
                }
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
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        JSONObject temp = new JSONObject();
        User user;
        Goose goose;
        CollectTime collectTime;
        List<Album> albumList = new ArrayList<Album>();
        int userId = 0;
        user = userMapper.selectByPhoneOne(msg.getString("Phone"));
        userId = user.getUserId();
        temp.put("User",JSONObject.fromObject(user,jsonConfig));
        collectTime = collectTimeMapper.selectById(userId);
        temp.put("CollectTime",JSONObject.fromObject(collectTime,jsonConfig));
        goose = gooseMapper.selectById(userId);
        temp.put("Goose",JSONObject.fromObject(goose,jsonConfig));
        albumList = albumMapper.selectByUserId(userId);
        temp.put("Album",albumList);
        return temp;
    }

    /*  Maybe it's like trash */
    private boolean dealUpdateGoose(JSONObject msg){
        Goose goose = new Goose();
        goose.setGooseCloud(msg.getInt("gooseCloud"));
        goose.setGooseWind(msg.getInt("gooseWind"));
        goose.setGooseSun(msg.getInt("gooseSun"));
        goose.setGooseStar(msg.getInt("gooseStar"));
        goose.setGooseDevil(msg.getInt("gooseDevil"));
        goose.setGooseRain(msg.getInt("gooseRain"));
        goose.setGooseEny(msg.getInt("gooseEny"));
        goose.setUserId(msg.getInt("userId"));
        if(gooseMapper.updateById(goose) != 0){
            return true;
        }else{
            return false;
        }
    }

    private MessageBox dealUpdateCollectTime(JSONObject msg){
        CollectTime collectTime = new CollectTime(msg.getInt("userId"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        switch (msg.getString("One")){
            case "Cloud":
                collectTime.setCloudLasttime(date);
                break;
            case "Wind":
                collectTime.setWindLasttime(date);
                break;
            case "Rain":
                collectTime.setRainLasttime(date);
                break;
            case "Devil":
                collectTime.setDevilLasttime(date);
                break;
            case "Sun":
                collectTime.setSunLasttime(date);
                break;
            case "Star":
                collectTime.setStarLasttime(date);
                break;
        }
        if(collectTimeMapper.updateByOne(collectTime) != 0){
            return MessageBox.UC_SUCCESS;
        }else{
            return MessageBox.SYS_ERROR;
        }
    }
}
