package com.os.comment.utils.threadUtils;

/**
 * Created by PengSongHe on 2015/12/31.
 */
public class ThreadPoolTest {

    public static final long START = System.currentTimeMillis();

    public static void main(String[] args) throws InterruptedException {
        ThreadPool threadPool = new ThreadPool(3); //创建一个有个3工作线程的线程池
        Thread.sleep(500); //休眠500毫秒,以便让线程池中的工作线程全部运行

        //运行任务
        long start = System.currentTimeMillis();//开始时间

        for (int i = 0; i <=500 ; i++) { //创建6个任务
            threadPool.execute(createTask(i));
        }
        threadPool.waitFinish(); //等待所有任务执行完毕
        long end = System.currentTimeMillis();
        System.out.println("总用时 ============：" + (end - start) + "s");
        threadPool.closePool(); //关闭线程池
    }
    private static Runnable createTask(final int taskID) {
        return new Runnable() {
            public void run() {
                System.out.println("Hello world----"+Thread.currentThread()+"------"+taskID);
            }
        };
    }
}
