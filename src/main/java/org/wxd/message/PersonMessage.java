package org.wxd.message;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wxd.domain.Person;

/**
 * Created by wxd on 16-9-19.
 */
@Component
public class PersonMessage {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendPersonMessage(Person person){
        rabbitTemplate.convertAndSend("springbootdemo", person);
    }
}
