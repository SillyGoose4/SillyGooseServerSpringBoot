package com.sillygoose.service.Controller;

import com.sillygoose.service.Mapper.*;
import com.sillygoose.service.Model.Album;
import com.sillygoose.service.Model.Pic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/get")
public class GetController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GooseMapper gooseMapper;

    @Autowired
    private CollectTimeMapper collectTimeMapper;

    @Autowired
    private AlbumMapper albumMapper;

    @Autowired
    private PicMapper picMapper;

    @RequestMapping("/getPic")
    public void getPic(@RequestBody String message){

    }
    @RequestMapping(value = "download",method = RequestMethod.GET)
    public String downLoad(HttpServletResponse response, HttpServletRequest request){
        String userId = request.getParameter("userId");
        String picLevel = request.getParameter("picLevel");
        String picBelong = request.getParameter("picBelong");
        String location=getPicLocation(userId,picLevel.charAt(0),picBelong);
        if(location.equals("FAILED")){
            response.setStatus(404);
            return null;
        }
        File file = new File(location);

        if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName :" + location);

            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("----------file download" );
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取图片uri
     * 能力不足暂且放下权重
     * @param userId
     * @param picLevel
     * @param picBelong
     * @return
     */
    private String getPicLocation(String userId, Character picLevel, String picBelong){
        String location="";
        List<Pic> picList = new ArrayList<Pic>();
        picList = picMapper.selectByBelong(picBelong);
        Integer picId = 0;
        for(Pic it : picList){
            if(it.getPicLevel() == picLevel ){
                location = it.getPicUrl();
                picId = it.getPicId();
                break;
            }
        }
        if(picId != 0) {
            Album record = new Album();
            record.setAlbumId(Integer.parseInt(userId));
            record.setPicId(picId);
            albumMapper.insert(record);
            return location;
        }else{
            return "FAILED";
        }
    }
}
