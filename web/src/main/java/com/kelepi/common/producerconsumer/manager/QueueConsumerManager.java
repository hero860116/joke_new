package com.kelepi.common.producerconsumer.manager;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.kelepi.common.producerconsumer.Queuetask.IQueueTask;
import com.kelepi.common.producerconsumer.thread.SingleConsumerThread;

/**
 * 队列，消费者，分配器 管理
 * @author liweilin
 *
 */
public class QueueConsumerManager
{

    /**
     * 阻塞队列
     */
    private BlockingQueue<IQueueTask> blockingQueue;

    /**
     * 构造队列与线程
     *
     * @param name       模式名称
     * @param queueSize  队列大小
     * @param threadSize 线程个数
     */
    public QueueConsumerManager(String name, int queueSize, int threadSize)
    {
        //构造指定大小的阻塞队列
        blockingQueue = new ArrayBlockingQueue<IQueueTask>(queueSize);

        //构造消费者线程
        for (int i = 1; i <= threadSize; i++)
        {
            new SingleConsumerThread(name + i, blockingQueue).start();
        }
    }



    /**
     * 往队列中增加task
     * @param queueTask 队列任务
     * @param taskName  任务名称
     */
    public void put(IQueueTask task)
    {
        try
        {
            //加入任务
            blockingQueue.put(task);
        }
        catch (InterruptedException e)
        {

        }
    }

    /**
     * 获取生产者消费者队列大小
     *
     * @return 队列大小
     */
    public int getSize()
    {
        return blockingQueue.size();
    }

}