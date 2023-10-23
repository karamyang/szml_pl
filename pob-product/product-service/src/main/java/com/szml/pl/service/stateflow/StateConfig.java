package com.szml.pl.service.stateflow;

import com.szml.pl.common.Constants;
import com.szml.pl.service.stateflow.event.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 状态流转配置
 * @author：karma
 * @date: 2023/10/22
 */

public class StateConfig {
    @Resource
    DraftState draftState;
    @Resource
    private OffLineState offLineState;
    @Resource
    private OnLineState onLineState;
    @Resource
    private UnderReviewState underReviewState;
    @Resource
    private UnReviewedState unReviewedState;
    @Resource
    private PassReviewState passAuditState;

    protected static Map<Integer, AbstractState> stateGroup = new ConcurrentHashMap<>();


    @PostConstruct
    public void init() {
        stateGroup.put(Constants.ProductState.DRAFT.getCode(),draftState);
        stateGroup.put(Constants.ProductState.UNREVIEW.getCode(),unReviewedState);
        stateGroup.put(Constants.ProductState.UNDERREVIEW.getCode(),underReviewState);
        stateGroup.put(Constants.ProductState.PASSREVIEW.getCode(),passAuditState);
        stateGroup.put(Constants.ProductState.OFFLINE.getCode(),offLineState);
        stateGroup.put(Constants.ProductState.ONLINE.getCode(),onLineState);
        System.out.println("hello");
    }
}
