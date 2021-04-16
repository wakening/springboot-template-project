//package com.example.provider.config;
//
//import com.alibaba.fastjson.JSON;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.concurrent.BasicThreadFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//import java.util.LinkedHashMap;
//import java.util.Map;
//import java.util.concurrent.LinkedBlockingQueue;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//
///**
// * 项目统一线程池管理
// *
// * @author wakening
// */
//@Getter
//@Setter
//@Slf4j
//@Component
//public class ThreadPoolConfig {
//
//    @Value("${threadPool.asyncThreadPoolExecutor.queueMaxSize:20000}")
//    private int asyncQueueMaxSize;
//
//    @Value("${threadPool.asyncThreadPoolExecutor.maxThreadPoolSize:200}")
//    private int asyncMaxThreadPoolSize;
//
//    /**
//     * 异步线程池，IO池
//     */
//    private ThreadPoolExecutor asyncThreadPoolExecutor;
//
//    /**
//     * 程序启动创建线程池。
//     * <p>
//     * ThreadPoolExecutor中策略的选择与工作队列的选择（java线程池）
//     * 工作原理
//     * 1、线程池刚创建时，里面没有一个线程。任务队列是作为参数传进来的。不过，就算队列里面有任务，线程池也不会马上执行它们。
//     * 2、当调用 execute() 方法添加一个任务时，线程池会做如下判断：
//     * a. 如果正在运行的线程数量小于 corePoolSize，那么马上创建线程运行这个任务；
//     * b. 如果正在运行的线程数量大于或等于 corePoolSize，那么将这个任务放入队列。
//     * c. 如果这时候队列满了，而且正在运行的线程数量小于 maximumPoolSize，那么还是要创建线程运行这个任务；
//     * d. 如果队列满了，而且正在运行的线程数量大于或等于 maximumPoolSize，那么线程池会抛出异常，告诉调用者“我不能再接受任务了”。
//     * 3、当一个线程完成任务时，它会从队列中取下一个任务来执行。
//     * 4、当一个线程无事可做，超过一定的时间（keepAliveTime）时，线程池会判断，如果当前运行的线程数大于 corePoolSize，那么这个线程就被停掉。所以线程池的所有任务完成后，它最终会收缩到 corePoolSize 的大小。这样的过程说明，并不是先加入任务就一定会先执行。假设队列大小为 10，corePoolSize 为 3，maximumPoolSize 为 6，那么当加入 20 个任务时，执行的顺序就是这样的：首先执行任务 1、2、3，然后任务 4~13 被放入队列。这时候队列满了，任务 14、15、16 会被马上执行，而任务 17~20 则会抛出异常。最终顺序是：1、2、3、14、15、16、4、5、6、7、8、9、10、11、12、13
//     */
//    @PostConstruct
//    private void init() {
//        final int minThreadPoolSize = Math.min(Runtime.getRuntime().availableProcessors(), 16);
//        int asyncMinThreadPoolSize = Math.max(8, minThreadPoolSize);
//        this.asyncThreadPoolExecutor = new ThreadPoolExecutor(
//                asyncMinThreadPoolSize,
//                this.asyncMaxThreadPoolSize,
//                10L,
//                TimeUnit.MINUTES,
//                new LinkedBlockingQueue<Runnable>(this.asyncQueueMaxSize),
//                new BasicThreadFactory.Builder().namingPattern("ThirdPool.asyncAsync-worker-%d").daemon(true).build()
//        );
//        log.info("Initializing ExecutorService asyncThreadPoolExecutor, minThreadPoolSize: {}, maxThreadPoolSize: {}, queueMaxSize: {}", asyncMinThreadPoolSize, asyncMaxThreadPoolSize, asyncQueueMaxSize);
//    }
//
//    @PreDestroy
//    public void destroy() {
//        printPoolInfo("asyncThreadPoolExecutor", this.asyncThreadPoolExecutor);
//
//        this.asyncThreadPoolExecutor.shutdown();
//    }
//
//    private void printPoolInfo(String poolName, ThreadPoolExecutor executor) {
//        Map<String, Object> map = new LinkedHashMap<>();
//        // 曾计划执行的近似任务总数
//        map.put("TaskCount", executor.getTaskCount());
//        // 已完成执行的近似任务总数
//        map.put("CompletedTaskCount", executor.getCompletedTaskCount());
//        // 池中曾出现过的最大线程数
//        map.put("LargestPoolSize", executor.getLargestPoolSize());
//        // 返回线程池中的当前线程数
//        map.put("PoolSize", executor.getPoolSize());
//        // 线程池中的当前活动线程数
//        map.put("ActiveCount", executor.getActiveCount());
//        // 线程池中约定的核心线程数
//        map.put("CorePoolSize", executor.getCorePoolSize());
//        // 线程池中约定的最大线程数
//        map.put("MaximumPoolSize", executor.getMaximumPoolSize());
//        log.info("Report thread pool info: [{}, {}]", poolName, JSON.toJSONString(map));
//    }
//
//}
