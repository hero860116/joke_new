
package com.kelepi.common.producerconsumer.manager;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.kelepi.common.producerconsumer.GlobalLock;
import com.kelepi.common.producerconsumer.Queuetask.IManyQueueTask;
import com.kelepi.common.producerconsumer.assigner.AAssigner;
import com.kelepi.common.producerconsumer.queue.MyQueue;
import com.kelepi.common.producerconsumer.thread.ConsumerThread;

/**
 * 队列，消费者，分配器 管理
 * @author liweilin
 *
 */
public class ManyQueueConsumerManager
{
    /**
     * 队列Map
     */
    private Map<String, MyQueue<IManyQueueTask>> taskQueueMap = new ConcurrentHashMap<String, MyQueue<IManyQueueTask>>();

    /**
     * 分配器
     */
    private AAssigner assigner;

    /**
     * 生产者消费者名称
     */
    private String name;

    /**
     * 线程列表
     */
    private List<ConsumerThread> consumerThreads = new LinkedList<ConsumerThread>();

    /**
     *
     * @param queueSize
     * @param threadSize
     */
    public ManyQueueConsumerManager(Class assignerclass, int threadSize, String name)
    {
        try
        {
            this.name = name;
            assigner = (AAssigner) assignerclass.newInstance();
            assigner.setTaskQueueMap(taskQueueMap);
        }
        catch (InstantiationException e)
        {
        }
        catch (IllegalAccessException e)
        {
        }

        for (int i = 1; i <= threadSize; i++)
        {
            ConsumerThread cThread = new ConsumerThread(name + i, assigner);
            cThread.start();

            consumerThreads.add(cThread);
        }
    }

    /**
     * 往队列中增加task
     * @param queueTask 队列任务
     * @param taskName  任务名称
     */
    public void putQueueTask(String taskId , IManyQueueTask task)
    {
        //将任务加入指定队列
        try
        {
            taskQueueMap.get(taskId).put(task);
        }
        catch (InterruptedException e)
        {
        }

        //唤醒消费者线程
        try
        {
            GlobalLock.getReentrantLock().lock();
            GlobalLock.getCondition().signalAll();
        }
        finally
        {
            GlobalLock.getReentrantLock().unlock();
        }
    }

    /**
     * 往队列首部增加task
     * @param queueTask 队列任务
     * @param taskName  任务名称
     */
    public void putFirstQueueTask(String taskId, IManyQueueTask queueTask)
    {
        //加入队列首，回流使用
        taskQueueMap.get(taskId).addFirst(queueTask);

        //唤醒消费者线程
        try
        {
            GlobalLock.getReentrantLock().lock();
            GlobalLock.getCondition().signalAll();
        }
        finally
        {
            GlobalLock.getReentrantLock().unlock();
        }
    }

    /**
     * 往队列中增加新task
     * @param <T>
     * @param taskId    任务ID
     * @param size      队列大小
     * @param taskInfo  任务信息
     */
    public <T> void addNewTask(String taskId , int size , T taskInfo)
    {

        GlobalLock.getReentrantLock().lock();

        try
        {
            //增加队列
            taskQueueMap.put(taskId, new MyQueue<IManyQueueTask>(size));

            //增加任务信息
            assigner.addTaskInfo(taskId, taskInfo);

        }
        finally
        {
            GlobalLock.getReentrantLock().unlock();
        }
    }

    /**
     * 回流线程任务
     *
     */
    public void refluenceThreadTask()
    {
        //循环所有线程
        for (ConsumerThread consumerThread : consumerThreads)
        {
            //获取线程当前正在处理的任务
            IManyQueueTask queueTask = consumerThread.getQueueTask();

            //如果队列中任务不为空
            if (queueTask != null)
            {
                //执行回流
                queueTask.refluenceTask();
            }
        }
    }


    public AAssigner getAssigner()
    {
        return assigner;
    }

    public String getName()
    {
        return name;
    }
}
