package com.kelepi.common.producerconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 合成队列全局锁
 *
 * @author liweilin
 *
 */
public final class GlobalLock
{
    /**
     * 单例
     */
    private static GlobalLock instance = new GlobalLock();

    /**
     * 锁
     */
    private ReentrantLock assignerLock;

    /**
     * 等待不为空
     */
    private Condition assignerNotEmpty;

    /**
     * 私有构造函数
     *
     */
    private GlobalLock()
    {
        assignerLock = new ReentrantLock();
        assignerNotEmpty = assignerLock.newCondition();
    }

    /**
     * 获得全局锁
     * @return
     */
    public static ReentrantLock getReentrantLock()
    {
        return instance.getAssignerLock();
    }

    /**
     * 获得等待条件
     * @return
     */
    public static Condition getCondition()
    {
        return instance.getAssignerNotEmpty();
    }

    public ReentrantLock getAssignerLock()
    {
        return assignerLock;
    }

    public Condition getAssignerNotEmpty()
    {
        return assignerNotEmpty;
    }

}
