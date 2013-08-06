
package com.kelepi.common.producerconsumer.queue;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 支持回流的队列
 * @author liweilin
 *
 * @param <T>
 */
public class MyQueue<T>
{

    private LinkedList<T> queue = new LinkedList<T>();

    private int maxSize;

    //锁
    private final ReentrantLock lock;

    //条件
    private final Condition notFull;;

    /**
     * 初始化方法
     * @param size 大小
     */
    public MyQueue(int size)
    {
        maxSize = size;
        lock = new ReentrantLock();
        notFull = lock.newCondition();
    }

    /**
     * 无阻塞增加元素
     * @param e
     */
    public void addFirst(T e)
    {
        queue.addFirst(e);
    }

    /**
     * 有阻塞增加元素
     * @param e
     * @throws InterruptedException
     */
    public void put(T e) throws InterruptedException
    {
        lock.lock();
        try
        {
            while (queue.size() == maxSize)
            {
                notFull.await();
            }

            queue.addLast(e);
        }
        finally
        {
            lock.unlock();
        }
    }


    /**
     * 无阻塞获取数据
     * @return
     */
    public T take()
    {
        lock.lock();

        T task = null;
        try
        {
            task = queue.removeFirst();
            notFull.signalAll();
        }
        finally
        {
            lock.unlock();
        }

        return task;
    }

    /**
     * 判断队列是否为空
     * @return
     */
    public boolean isEmpty()
    {
        return queue.isEmpty();
    }

    /**
     * 获得队列大小
     * @return
     */
    public int size()
    {
        return queue.size();
    }

}
