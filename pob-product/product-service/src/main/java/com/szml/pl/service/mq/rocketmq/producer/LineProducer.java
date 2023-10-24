package com.szml.pl.service.mq.rocketmq.producer;

import com.alibaba.fastjson.JSON;
import com.szml.pl.common.Constants;
import com.szml.pl.common.Result;
import com.szml.pl.dto.ProductDto;
import com.szml.pl.dto.ProductMqDto;
import com.szml.pl.service.mq.rocketmq.util.MqLevelutil;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LineProducer {
    private Logger logger = LoggerFactory.getLogger(LineProducer.class);
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public static final String POB_ONLINE = "pob_online";
    /**
     * MQ主题：活动领取记录
     *
     * 创建topic：bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic lottery_activity_partake
     */
    public static final String POB_OFFLINE = "pob_offline";

    public Result sendPobOnline(ProductDto productDto) {
        if (productDto.getOnlineTime().getTime()<System.currentTimeMillis()){
            return Result.buildResult(Constants.ResponseCode.UN_ERROR.getCode(),Constants.ResponseCode.UN_ERROR.getInfo());
        }
        Integer level = MqLevelutil.calculateDefault((productDto.getOnlineTime().getTime()-System.currentTimeMillis())/1000);
        logger.info("{} {}",productDto.getOnlineTime().getTime(),System.currentTimeMillis());
        logger.info("延时等级 {}",level);
        rocketMQTemplate.asyncSend(POB_ONLINE, MessageBuilder.withPayload(productDto).build(), new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                logger.info("发送成功");
            }

            @Override
            public void onException(Throwable throwable) {
//                sendPobOnline(productDto);
                logger.info("发送失败");
            }
        }, 2000, level);
        return Result.buildResult(Constants.ResponseCode.SUCCESS.getCode(),Constants.ResponseCode.SUCCESS.getInfo());
    }
    public Result sendPobOffline(ProductDto productDto) {
        if (productDto.getLineTime().getTime()<System.currentTimeMillis()){
            return Result.buildResult(Constants.ResponseCode.UN_ERROR.getCode(),Constants.ResponseCode.UN_ERROR.getInfo());
        }
        Integer level = MqLevelutil.calculateDefault((productDto.getLineTime().getTime()-System.currentTimeMillis())/1000);
        rocketMQTemplate.asyncSend(POB_OFFLINE, MessageBuilder.withPayload(productDto).build(), new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {

            }

            @Override
            public void onException(Throwable throwable) {
                sendPobOffline(productDto);
            }
        }, 2000, level);
        return Result.buildResult(Constants.ResponseCode.SUCCESS.getCode(),Constants.ResponseCode.SUCCESS.getInfo());
    }



}
