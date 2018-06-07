package com.sillygoose.service.Controller;

import com.sillygoose.service.Mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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



}
