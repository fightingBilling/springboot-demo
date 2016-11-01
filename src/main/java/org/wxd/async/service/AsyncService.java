package org.wxd.async.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by wxd on 16-9-24.
 */
@Service
public class AsyncService {

    @Value("classpath:email.txt")
    private Resource emailFile;

    /**
     *
     * @param url: url中需要包括token参数
     * @param count
     * @param pid
     * @param sn
     */
    @Async
    public void testSign(String url, int count, String[] pid, String[] sn){
        long start = Calendar.getInstance().getTimeInMillis();

        int index = count % 10;

        StringBuffer sb = new StringBuffer();
        sb.append("{\"pid\":\"" + pid[index] + "\",\"sn\":\"" + sn[index] + "\"}");

        HttpHeaders headers =new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request=new HttpEntity(sb.toString(), headers);

        RestTemplate restTemplate =  new RestTemplate();
        try {
            ResponseEntity response = restTemplate.postForEntity(url, request, String.class);

            //System.out.println(response.getBody());
            long end2 = Calendar.getInstance().getTimeInMillis();
            System.out.println("["+count+"] Time:"  + (end2-start) + ", Input:" + sb.toString() + ", Output:" + response.getBody());
        }catch(Exception e){
            long end = Calendar.getInstance().getTimeInMillis();
            System.out.println("["+count+"] Time:"  + (end-start) + ", Input:" + sb.toString() + "exception:" +  e.getMessage());
        }
    }

    /**
     * 读取email文件中的行数据
     * @return
     */
    public List<String> readEmailFile(){
        BufferedReader br = null;
        List<String> list = new ArrayList<String>();
        try {
            br = new BufferedReader(new InputStreamReader(emailFile.getInputStream()));
            String s = null;
            while(null != (s = br.readLine())){
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     *
     * @param url check email url地址：http://host:port/path/to/uri?param=
     * @param email
     * @throws Exception
     */
    @Async
    public void checkValidEmail(String url, String email) throws Exception{
        String[] emailArr = email.split("\t");
        url = url + emailArr[1];

        RestTemplate restTemplate =  new RestTemplate();
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            protected boolean hasError(HttpStatus statusCode) {
                return false;
            }
        });
        try {
            ResponseEntity response = restTemplate.getForEntity(url, String.class);
            System.out.println(emailArr[0] + "," + emailArr[1] + "," + emailArr[2] + "," + response.getStatusCode());
        }catch(Exception e){
            e.printStackTrace();
        }


    }

    @Async
    public void testBinding(String url, int index){
        StringBuffer sb = new StringBuffer();
        sb.append("{\"pid\":\"" + "1700001671" + "\",\"sn\":\"aaaaaaaaaa" + index
                + "\",\"din\":\"1000000000000"+index+"\",\"dtoken\":\"dotkenaaaaaaaaaa"+index+"\", \"drefreshtoken\": \"drefreshtokenaaaaaaaaaa"+index+"\"}");

        HttpHeaders headers =new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request=new HttpEntity(sb.toString(), headers);

        RestTemplate restTemplate =  new RestTemplate();

        long start = Calendar.getInstance().getTimeInMillis();
        try{
            ResponseEntity response = restTemplate.postForEntity(url, request, String.class);

            //System.out.println(response.getBody());
            long end2 = Calendar.getInstance().getTimeInMillis();
            System.out.println("["+index+"] Time:"  + (end2-start) + ", Input:" + sb.toString() + ", Output:" + response.getBody());
        }catch(Exception e){
            long end = Calendar.getInstance().getTimeInMillis();
            System.out.println("["+index+"] Time:"  + (end-start) + ", Input:" + sb.toString() + "exception:" +  e.getMessage());
        }
    }
}
