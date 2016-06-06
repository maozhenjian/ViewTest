package com.example.beyong.viewtest.HTTP;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 振坚 on 2016/4/27.
 */
public class HttpURLConnection {

    //  异步处理
    private Handler handler=new Handler(){

        public void handleMessage(Message msg){
            switch (msg.what){
                case 1:
                    String response= (String) msg.obj;
                    Log.e("handler", Thread.currentThread().getName().toString()+"response  "+response);
            }
        }
     };

/***
 *
 * new Thread的弊端如下：
 a. 每次new Thread新建对象性能差。
 b. 线程缺乏统一管理，可能无限制新建线程，相互之间竞争，及可能占用过多系统资源导致死机或oom。
 c. 缺乏更多功能，如定时执行、定期执行、线程中断。
 相比new Thread，Java提供的四种线程池的好处在于：
 a. 重用存在的线程，减少对象创建、消亡的开销，性能佳。
 b. 可有效控制最大并发线程数，提高系统资源的使用率，同时避免过多资源竞争，避免堵塞。
 c. 提供定时执行、定期执行、单线程、并发数控制等功能。
 * */
    private void sendRequestWithHttpURLConnection(final String s) {
        //开启线程发送网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e("HttpURLConnection" ,Thread.currentThread().getName().toString());
                java.net.HttpURLConnection connection=null;
                try {
                    URL url=new URL(s);
                    connection= (java.net.HttpURLConnection) url.openConnection();
//                   connection= (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");//请求方法
                    connection.setConnectTimeout(8000);//连接超时时间
                    connection.setReadTimeout(8000);//读取超时时间
                    //服务器返回的输入流
                    InputStream in=connection.getInputStream();
                    //对输入流进行读取
                    BufferedReader reader=new BufferedReader(new InputStreamReader(in));

                    StringBuilder response=new StringBuilder();
                    String line;
                    while((line=reader.readLine())!=null){
                        response.append(line);
                    }

                    Message message = new Message();
                    message.what = 1;
                    message.obj = response.toString();//将服务器返回的结果存放到Message中
                    handler.sendMessage(message);
                    reader.close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }


/**
 * Java通过Executors提供四种线程池，分别为：
 *newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
 *newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
 *newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
 *newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
 *
 */

    /** newCachedThreadPool*/
//      创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
//      线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
    private void newCachedThreadPool(){
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(index * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index);
                }
            });
        }
    }

    /** newFixedThreadPool*/
//    创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
//    因为线程池大小为3，每个任务输出index后sleep 2秒，所以每两秒打印3个数字。
//    定长线程池的大小最好根据系统资源进行设置。如Runtime.getRuntime().availableProcessors()。
    private void newFixedThreadPool(){
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }


    /** newSingleThreadExecutor*/
//    创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
//    结果依次输出，相当于顺序执行各个任务。
//    现行大多数GUI程序都是单线程的。Android中单线程可用于数据库操作，文件操作，应用批量安装，应用批量删除等不适合并发但可能IO阻塞性及影响UI线程响应的操作。
    private void newSingleThreadExecutor(){
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
