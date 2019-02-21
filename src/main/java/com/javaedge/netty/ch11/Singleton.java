package com.javaedge.netty.ch11;

import io.netty.handler.codec.mqtt.MqttEncoder;
import io.netty.handler.timeout.ReadTimeoutException;

/**
 * @author JavaEdge
 * @see ReadTimeoutException
 * @see MqttEncoder
 */
public class Singleton {
    private static Singleton singleton;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (singleton == null) {
            // 避免多线程环境下的🔐竞争
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
