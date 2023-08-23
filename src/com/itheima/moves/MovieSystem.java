package com.itheima.moves;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.*;

/*启动类 2021,6,8*/
public class MovieSystem {
    public static void main(String[] args) {
        try {
            movieRelease.getSystem();
            User userS = movieRelease.getSystem();
            pool.execute(userS);//调用线程池
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static  ExecutorService pool = new ThreadPoolExecutor(3, 5,
            5, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(5),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());
    }