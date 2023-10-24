package com.szml.pl.service.mq.rocketmq.consumer;

import com.szml.pl.dto.ProductDto;
import com.szml.pl.service.ProductService;
import com.szml.pl.service.mq.rocketmq.producer.LineProducer;
import com.szml.pl.service.mq.rocketmq.util.MqLevelutil;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description:
 * @author：karma
 * @date: 2023/10/24
 */
@Service
@RocketMQMessageListener(topic = "pob_offline", consumerGroup = "pob_offline_consumer")
public class OfflineConsumer implements RocketMQListener<ProductDto> {
    @Resource
    LineProducer lineProducer;
    @Resource
    ProductService productService;
    @Override
    public void onMessage(ProductDto productDto) {
        if(productDto.getLineTime().getTime()<System.currentTimeMillis()){
            productService.offline(productDto);
            return;
        }
        Integer level = MqLevelutil.calculateDefault((productDto.getLineTime().getTime()-System.currentTimeMillis())/1000);
        if(level>=5){
            lineProducer.sendPobOffline(productDto);
        }else{
            productService.offline(productDto);
        }
    }
}
