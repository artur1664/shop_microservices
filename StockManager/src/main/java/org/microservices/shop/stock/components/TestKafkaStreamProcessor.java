package org.microservices.shop.stock.components;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.microservices.shop.stock.model.MongoSource;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableBinding(Processor.class)
//@EnableBinding(Sink.class)
public class TestKafkaStreamProcessor {


    @StreamListener(Processor.INPUT)
//    @SendTo(Processor.OUTPUT)
    public void process(String msg) {
        try {
            MongoSource mongoSource = new ObjectMapper().readValue(msg, MongoSource.class);
            log.info("this time it works fine! {}", mongoSource.getFullDocument().get_id().get$oid());
            log.info("this time it works fine! {}", mongoSource.getFullDocument().getName());
            log.info("this time it works fine! {}", mongoSource.getFullDocument().getDescription());
            log.info("this time it works fine! {}", mongoSource.getFullDocument().getPrice());
        } catch (JsonProcessingException e) {
            log.error("", e);
        }
    }

}
