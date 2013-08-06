package com.kelepi.common.producerconsumer.thread;

import com.kelepi.common.producerconsumer.Queuetask.IManyQueueTask;
import com.kelepi.common.producerconsumer.assigner.AAssigner;

/**
 * 多队列消费者线程
 * @author liweilin
 *
 */
public class ConsumerThread extends Thread
{
    /**
     * 分配器
     */
    private AAssigner assigner;

    /**
     * 当前处理的任务
     */
    private IManyQueueTask queueTask;

    /**
     * 构造方法
     *
     * @param name     线程名称
     * @param assigner 分配器
     */
    public ConsumerThread(String name, AAssigner assigner)
    {
        super(name);
        this.assigner = assigner;
    }

    @Override
    public void run()
    {
        while (true)
        {
            //通过分配器获取任务
            queueTask = assigner.obtainTask();

            //执行任务
            queueTask.execute();

            //任务结束，还原为null
            queueTask = null;

            try
            {
                Thread.sleep(50);
            }
            catch (InterruptedException e)
            {
            }

        }
    }

    /**
     * 获取当前处理的任务
     * @return 返回任务
     */
    public IManyQueueTask getQueueTask()
    {
        return queueTask;
    }
}
