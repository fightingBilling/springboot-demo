package org.wxd.async.run;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.wxd.async.config.RunAsyncServiceConfig;
import org.wxd.async.service.AsyncService;

import java.util.List;

/**
 * Created by wanxiaod on 8/11/2016.
 */
public class RunCheckEmail {

    public static void main(String[] args) throws Exception{
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(RunAsyncServiceConfig.class);
        AsyncService asyncService = applicationContext.getBean(AsyncService.class);
        List<String> emaillList = asyncService.readEmailFile();
        /*
        for(int i=0; i<emaillList.size();i++){
            String s = emaillList.get(i);
            System.out.println(s.split("\t")[1]);
        }
        */

        // TODO 跑测试的时候需要增加实际的URL地址
        String url = "";

        for(int i=0; i<emaillList.size(); i++) {
            asyncService.checkValidEmail(url, emaillList.get(i));
            Thread.sleep(1000);
        }

    }
}
