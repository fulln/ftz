package me.fulln.ftz.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @AUthor: fulln
 * @Description: 视图跳转层
 * @Date : Created in  17:50  2018/9/16.
 */
@Controller
public class ViewController {

    @RequestMapping("/view")
    public String play() {
        return "play/play";
    }

    @RequestMapping("/setting")
    public String setting() {
        return "play/setting";
    }

}


