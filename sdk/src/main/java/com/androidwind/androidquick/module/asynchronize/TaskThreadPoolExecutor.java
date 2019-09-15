package com.androidwind.androidquick.module.asynchronize;

import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class TaskThreadPoolExecutor extends ThreadPoolExecutor {

    private static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors();
    private static final int MAXIMUM_POOL_SIZE = 128;
    private static final int KEEP_ALIVE = 60;
    private static final AtomicLong SEQ_SEED = new AtomicLong(0);

    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "TaskThreadPoolExecutor#" + mCount.getAndIncrement());
        }
    };

    /**
     * FIFO
     */
    private static final Comparator<Runnable> FIFO = new Comparator<Runnable>() {
        @Override
        public int compare(Runnable lhs, Runnable rhs) {
            if (lhs instanceof SimpleTask && rhs instanceof SimpleTask) {
                SimpleTask lpr = ((SimpleTask) lhs);
                SimpleTask rpr = ((SimpleTask) rhs);
                int result = lpr.priority.ordinal() - rpr.priority.ordinal();
                return result == 0 ? (int) (lpr.SEQ - rpr.SEQ) : result;
            } else {
                return 0;
            }
        }
    };

    /**
     * LIFO
     */
    private static final Comparator<Runnable> LIFO = new Comparator<Runnable>() {
        @Override
        public int compare(Runnable lhs, Runnable rhs) {
            if (lhs instanceof SimpleTask && rhs instanceof SimpleTask) {
                SimpleTask lpr = ((SimpleTask) lhs);
                SimpleTask rpr = ((SimpleTask) rhs);
                int result = lpr.priority.ordinal() - rpr.priority.ordinal();
                return result == 0 ? (int) (rpr.SEQ - lpr.SEQ) : result;
            } else {
                return 0;
            }
        }
    };

    public TaskThreadPoolExecutor(boolean fifo) {
        this(CORE_POOL_SIZE, fifo);
    }

    public TaskThreadPoolExecutor(int poolSize, boolean fifo) {
        this(poolSize, MAXIMUM_POOL_SIZE, KEEP_ALIVE, TimeUnit.SECONDS, new PriorityBlockingQueue<Runnable>(MAXIMUM_POOL_SIZE, fifo ? FIFO : LIFO), sThreadFactory);
    }

    public TaskThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public boolean isBusy() {
        return getActiveCount() >= getCorePoolSize();
    }

    @Override
    public void execute(Runnable runnable) {
        if (runnable instanceof SimpleTask) {
            ((SimpleTask) runnable).SEQ = SEQ_SEED.getAndIncrement();
        }
        super.execute(runnable);
    }
}