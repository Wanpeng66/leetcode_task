package com.wp;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: wp
 * @Title: lamda_test
 * @Description: TODO
 * @date 2022/6/30 16:27
 */
public class lamda_test {
    public static void main( String[] args ) throws Exception {
        List<String> hosts = Arrays.asList( "127.0.0.1:9300" );
        List<Callable<String[]>> callables = hosts.stream().map( hn -> (Callable<String[]>) () -> new String[]{hn} ).collect( Collectors.toList() );
        ExecutorService threadPool = Executors.newFixedThreadPool( 1 );
        List<Future<String[]>> futures = threadPool.invokeAll( callables );
        threadPool.shutdown();
        Thread.sleep( 10000 );
        for (Future<String[]> future : futures) {
            System.out.println( future.get() );
        }
    }
}
