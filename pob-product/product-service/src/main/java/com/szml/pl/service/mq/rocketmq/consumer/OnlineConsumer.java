package com.szml.pl.service.mq.rocketmq.consumer;

import com.szml.pl.common.Constants;
import com.szml.pl.common.Result;
import com.szml.pl.dao.ProductDao;
import com.szml.pl.dto.ProductDto;
import com.szml.pl.dto.ProductMqDto;
import com.szml.pl.service.ProductService;
import com.szml.pl.service.mq.rocketmq.producer.LineProducer;
import com.szml.pl.service.mq.rocketmq.util.MqLevelutil;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@RocketMQMessageListener(topic = "pob_online", consumerGroup = "pob_online_consumer")
public class OnlineConsumer implements RocketMQListener<ProductDto> {
    private Logger logger = LoggerFactory.getLogger(OnlineConsumer.class);
    @Resource
    LineProducer lineProducer;
    @Resource
    ProductService productService;
    @Override
    public void onMessage(ProductDto productDto) {
        if(productDto.getOnlineTime().getTime()<System.currentTimeMillis()){
            productService.online(productDto);
            return;
        }
        Integer level = MqLevelutil.calculateDefault((productDto.getOnlineTime().getTime()-System.currentTimeMillis())/1000);
        logger.info("延时等级 {}",level);
        if(level>=5){
            lineProducer.sendPobOnline(productDto);
            logger.info("再次发送延时消息");
        }else{
            Result online = productService.online(productDto);
            if(online.getCode().equals(Constants.ResponseCode.SUCCESS.getCode())){
                lineProducer.sendPobOffline(productDto);
            }
            logger.info("上线");
        }
    }
}
