package org.wxd.aws.ec2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wxd on 16-9-9.
 */

@RestController
@RequestMapping("/ec2")
public class EC2Controller {

    @RequestMapping("/")
    public String listEc2(){
        
        return "";
    }
}
