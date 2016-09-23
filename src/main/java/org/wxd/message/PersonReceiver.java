package org.wxd.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.wxd.domain.Person;

/**
 * Created by wxd on 16-9-19.
 */
@Component
public class PersonReceiver {

    @RabbitListener(queues = "springbootdemo")
    public void receivePersonMessage(Person person){
        System.out.println("receive person message:{id:" + person.getId() + ",name:" + person.getName()
                + ", address:" + person.getAddress() + ",mobile:" + person.getMobile() + ",age:" + person.getAge() + "}");
    }
}
