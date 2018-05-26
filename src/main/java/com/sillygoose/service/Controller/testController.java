package com.sillygoose.service.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class testController {

    @RequestMapping("/hello")
    public String print(){
        return "hello";
    }
}
