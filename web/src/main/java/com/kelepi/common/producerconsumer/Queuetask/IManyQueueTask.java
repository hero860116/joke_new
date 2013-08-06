package com.kelepi.common.producerconsumer.Queuetask;

/**
 * 多队列任务
 * @author liweilin
 *
 */
public interface IManyQueueTask
{
    /**
     * 执行方法
     *
     */
    void execute();

    /**
     * 回流任务
     *
     */
    void refluenceTask();
}
