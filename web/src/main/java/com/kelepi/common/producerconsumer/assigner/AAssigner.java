package com.kelepi.common.producerconsumer.assigner;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.kelepi.common.producerconsumer.Queuetask.IManyQueueTask;
import com.kelepi.common.producerconsumer.queue.MyQueue;

/**
 * 分配器实例
 * @author liweilin
 *
 */
public abstract class AAssigner<T>
{
    /**
     * 任务信息Map
     * 存放每个任务详细信息，分配器依据这些信息进行分配算法控制
     */
    protected Map<String, T> taskInfoMap = new ConcurrentHashMap<String, T>();

    /**
     * 队列Map
     * 分配器根据分配算法从这些队列中获取任务
     */
    protected Map<String, MyQueue<IManyQueueTask>> taskQueueMap;

    /**
     * 获取任务接口，消费者线程调用
     * @return 任务
     */
    public abstract IManyQueueTask obtainTask();

    /**
     * 给分配器增加一个任务信息
     * @param taskId
     * @param task
     */
    public void addTaskInfo(String taskId, T task)
    {
        taskInfoMap.put(taskId, task);
    }

    public void setTaskQueueMap(Map<String, MyQueue<IManyQueueTask>> taskQueueMap)
    {
        this.taskQueueMap = taskQueueMap;
    }

    public void removeTask(String taskId)
    {
        taskInfoMap.remove(taskId);
        taskQueueMap.remove(taskId);
    }
}
