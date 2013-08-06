package com.kelepi.common.producerconsumer.manager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 生产者消费者工厂
 *
 * @author liweilin
 *
 */
public class ManyProducerConsumerFactory
{
    /**
     * 单例
     */
    private static ManyProducerConsumerFactory instance = new ManyProducerConsumerFactory();

    /**
     * 存放已经构造好的生产者消费者模型
     */
    private Map<String, ManyQueueConsumerManager> queueConsumerManagerMap = new ConcurrentHashMap<String, ManyQueueConsumerManager>();

    /**
     * 私有构造方法
     *
     */
    private ManyProducerConsumerFactory()
    {

    }

    /**
     * 构造生产者消费者模型
     *
     * @param name       生产者消费者名称
     * @param queueSize  队列大小
     * @param threadSize 消费者线程个数
     */
    public static void createProducerConsumer(String name, Class assigner, int threadSize)
    {
        //创建生产者消费者模型
        ManyQueueConsumerManager queueConsumerManager = new ManyQueueConsumerManager(assigner, threadSize, name);

        //加入缓存
        instance.getQueueConsumerManagerMap().put(name, queueConsumerManager);
    }

    /**
     * 返回生产者消费者管理类
     *
     * @param name 生产者消费者名称
     * @return 生产者消费者管理类
     */
    public static ManyQueueConsumerManager getProducerConsumer(String name)
    {
        return instance.getQueueConsumerManagerMap().get(name);
    }

    private Map<String, ManyQueueConsumerManager> getQueueConsumerManagerMap()
    {
        return queueConsumerManagerMap;
    }

}