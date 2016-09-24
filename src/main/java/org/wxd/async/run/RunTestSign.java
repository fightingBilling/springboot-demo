package org.wxd.async.run;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.wxd.async.config.RunAsyncServiceConfig;
import org.wxd.async.service.AsyncService;

/**
 * Created by wanxiaod on 7/6/2016.
 * TODO console 日志级别设置？
 */
public class RunTestSign {

    public static void main(String[] args){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(RunAsyncServiceConfig.class);
        //UseFunctionService useFunctionService = applicationContext.getBean(UseFunctionService.class);
        //System.out.println(useFunctionService.say("Hello World..."));
        AsyncService asyncService = applicationContext.getBean(AsyncService.class);
        String[] pid = {"1700001671","1700001671","1700001671","1700001671","1700001671","1700001671","1700001671","1700001671","1700001671","1700001671"};
        String[] sn = {"4a7ac254ad4041d3","c8c0f856bd5c4d88","a37f00f3efd84aaf","bd7402e669bb49ee","dd98ba55cc384f7d",
                "e94adf17069f439f","31fb2c4a205642bd","9ec27ad71d5e41be","23f7efbfcadc4ead","2f871519625b4f7f"};

        // TODO 跑测试的时候需要添加实际的URL地址
        String url = "";

        for(int i=0; i< 1000; i++) {
            asyncService.testSign(url, i, pid, sn);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        applicationContext.close();
    }
}
