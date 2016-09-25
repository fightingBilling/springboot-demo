package org.wxd.schedule;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by wxd on 16-9-25.
 */
@Configuration
@ComponentScan("org.wxd.schedule")
@EnableScheduling
public class ScheduleConfigAndRun {

    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScheduleConfigAndRun.class);
    }
}
