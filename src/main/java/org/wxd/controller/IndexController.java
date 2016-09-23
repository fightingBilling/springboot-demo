package org.wxd.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wxd on 16-9-9.
 */

@RestController
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "Index Page";
    }
}
