package com.szml.pl.service.mq.rocketmq.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author：karma
 * @date: 2023/10/24
 */
public class MqLevelutil {

    private static List<Integer> defaultLevel;

    static {
        defaultLevel = new ArrayList<>();
        defaultLevel.add(1);
        defaultLevel.add(5);
        defaultLevel.add(10);
        defaultLevel.add(30);
        defaultLevel.add(60);
        defaultLevel.add(120);
        defaultLevel.add(180);
        defaultLevel.add(240);
        defaultLevel.add(300);
        defaultLevel.add(360);
        defaultLevel.add(420);
        defaultLevel.add(480);
        defaultLevel.add(540);
        defaultLevel.add(600);
        defaultLevel.add(1200);
        defaultLevel.add(1800);
        defaultLevel.add(3600);
        defaultLevel.add(7200);
    }

    public static Integer calculateDefault(long second) {
        Integer level = null;
        for (int i = defaultLevel.size() - 1; i >= 0; i--) {
            int l = (int) second / defaultLevel.get(i);
            if (l > 0 && level == null) {
                level = i;
            }
            if (level == null) {
                continue;
            }
            if (level < i) {
                break;
            }
        }
        return level + 1;
    }
}
