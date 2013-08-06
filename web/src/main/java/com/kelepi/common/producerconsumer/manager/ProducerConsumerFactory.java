package com.kelepi.common.producerconsumer.manager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 生产者消费者工厂
 *
 * @author liweilin
 *
 */
public class ProducerConsumerFactory
{
    /**
     * 单例
     */
    private static ProducerConsumerFactory instance = new ProducerConsumerFactory();

    /**
     * 存放已经构造好的生产者消费者模型
     */
    private Map<String, QueueConsumerManager> queueConsumerManagerMap = new ConcurrentHashMap<String, QueueConsumerManager>();

    /**
     * 私有构造方法
     *
     */
    private ProducerConsumerFactory()
    {

    }

    /**
     * 构造生产者消费者模型
     *
     * @param name       生产者消费者名称
     * @param queueSize  队列大小
     * @param threadSize 消费者线程个数
     */
    public static void createProducerConsumer(String name, int queueSize, int threadSize)
    {
        //创建生产者消费者模型
        QueueConsumerManager queueConsumerManager = new QueueConsumerManager(name, queueSize, threadSize);

        //加入缓存
        instance.getQueueConsumerManagerMap().put(name, queueConsumerManager);
    }

    /**
     * 返回生产者消费者管理类
     *
     * @param name 生产者消费者名称
     * @return 生产者消费者管理类
     */
    public static QueueConsumerManager getProducerConsumer(String name)
    {
        return instance.getQueueConsumerManagerMap().get(name);
    }

    private Map<String, QueueConsumerManager> getQueueConsumerManagerMap()
    {
        return queueConsumerManagerMap;
    }

}
