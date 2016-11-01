package org.wxd.async.run;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.wxd.async.config.RunAsyncServiceConfig;
import org.wxd.async.service.AsyncService;

/**
 * Created by wxd on 16-10-31.
 */
public class RunTestBinding {
    public static void main(String [] args){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(RunAsyncServiceConfig.class);
        AsyncService asyncService = applicationContext.getBean(AsyncService.class);
        String url = "http://qqapp-test.eprintsw.com/authsec/access/printer/binding?token=XjkMRh4LPLEBzUrJBRckMfKydn8fLbT2ov4wdMWDQB43jLLOJ5M8cCwduPcu0P6vpyRnWhFnt4RqNFthtEjhdSAPaK6xnJHG3Xyuj1SJPT5M5QHq0IsKtMzfcCrCmDdIBCeTznbYJXHwGUt8YzbHLIiPfIisvqfiFluJtElV8cFg2gQqeckIbrCqQ280iQlSqZXGFJyZxnLQ7ZDdAZf2jA96TIdwvkj5DFJG9DkV9Htn5c9XijbaY9UH636NCNGP";

        for(int i=701; i<751; i++) {
            asyncService.testBinding(url, i);
        }
    }
}
