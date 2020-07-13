package com.xrpt.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author by wjx
 * @date 2020/7/13
 * @DESC:这个Controller用来控制A标签的跳转。
 */
public class JumpController {

    @RequestMapping("/toPosterCenter")
    public String toPosterCenter(){
        return "Poster/PosterCenter";
    }
}
