package com.kelepi.common.producerconsumer.thread;

import java.util.concurrent.BlockingQueue;

import com.kelepi.common.producerconsumer.Queuetask.IQueueTask;

/**
 * 简单队列消费者线程
 * @author liweilin
 *
 */
public class SingleConsumerThread extends Thread
{
    /**
     * 数据队列
     */
    private BlockingQueue<IQueueTask> blockingQueue;

    /**
     * 构造方案
     * @param name             线程名称
     * @param blockingQueue    队列
     */
    public SingleConsumerThread(String name, BlockingQueue<IQueueTask> blockingQueue)
    {
        super(name);

        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                IQueueTask task = blockingQueue.take();

                task.execute();

                Thread.sleep(50);
            }
            catch (InterruptedException e)
            {
            }
        }
    }
}
