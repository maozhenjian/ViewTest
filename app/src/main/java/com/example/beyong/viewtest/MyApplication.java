package com.example.beyong.viewtest;

import android.app.Application;
import android.content.Context;
import android.graphics.Point;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beyong.viewtest.Utils.CrashHandler;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.xutils.x;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 振坚 on 2016/3/8.
 */
public class MyApplication extends Application {

    private static MyApplication mInstance = null;
    public static MyApplication getApplication() {
        return mInstance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        //对应CrashHandler捕获异常
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(getApplicationContext());
        //  Android中如何捕获未捕获的异常(重要)
//        Thread.setDefaultUncaughtExceptionHandler(this);
        //xUtils初始化
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
//        全局保持一个单例的Toast，这样就可以进行快速刷新。
        ToastMgr.builder.init(getApplicationContext());
//        单例Application
        mInstance = this;

        initImageLoader();

        getScreenInfo();


    }

    public int screenWidth, screenHeight;

    private void getScreenInfo() {
        // 方法1 Android获得屏幕的宽和高
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }


    //        应用程序最高可用内存是多少。
    public int getMaxMemory() {
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        Log.e("TAG", "Max memory is " + maxMemory + "KB");
        return maxMemory;
    }

    public enum ToastMgr{
        builder;
        private View view;
        private TextView tv;
        private Toast toast;
        /**
         * 初始化Toast
         * @param context
         */
        public void init(Context context){
            //自定义Toast布局
            view = LayoutInflater.from(context).inflate(R.layout.toast_view, null);
            tv = (TextView) view.findViewById(R.id.toast_textview);
            toast = new Toast(context);
            toast.setView(view);
        }

        /**
         * 显示Toast
         * @param content
         * @param duration Toast持续时间
         */
        public void display(CharSequence content , int duration){
            if (content.length()!=0) {
                tv.setText(content);
                toast.setDuration(duration);
//                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        }
    }

    //网络处理 end------------------------------------
    void initImageLoader() {

        HttpParams params = new BasicHttpParams();
        // Turn off stale checking. Our connections break all the time anyway,
        // and it's not worth it to pay the penalty of checking every time.
        HttpConnectionParams.setStaleCheckingEnabled(params, false);
        // Default connection and socket timeout of 10 seconds. Tweak to taste.
        HttpConnectionParams.setConnectionTimeout(params, 10 * 1000);
        HttpConnectionParams.setSoTimeout(params, 10 * 1000);
        HttpConnectionParams.setSocketBufferSize(params, 8192);

        // Don't handle redirects -- return them to the caller. Our code
        // often wants to re-POST after a redirect, which we must do ourselves.
        HttpClientParams.setRedirecting(params, false);
        // Set the specified user agent and register standard protocols.
        HttpProtocolParams.setUserAgent(params, "some_randome_user_agent");
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));

        ClientConnectionManager manager = new ThreadSafeClientConnManager(params, schemeRegistry);


        DisplayImageOptions defaultOoptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .displayer(new SimpleBitmapDisplayer())
                .imageScaleType(ImageScaleType.NONE_SAFE)

                .cacheOnDisk(true).build();

        ImageLoaderConfiguration configuration =
                new ImageLoaderConfiguration.Builder(this)
                        .threadPriority(Thread.NORM_PRIORITY - 2)
                        .denyCacheImageMultipleSizesInMemory()
                        .tasksProcessingOrder(QueueProcessingType.LIFO)
                        .imageDecoder(new BaseImageDecoder(true))
                        .defaultDisplayImageOptions(defaultOoptions)
                        .imageDownloader(new HttpClientImageDownloader(this, new DefaultHttpClient(manager, params)))
                        .build();

        ImageLoader.getInstance().init(configuration);
    }

    class HttpClientImageDownloader extends BaseImageDownloader {

        private DefaultHttpClient httpClient;

        public HttpClientImageDownloader(Context context, DefaultHttpClient httpClient) {
            super(context);
            this.httpClient = httpClient;
        }

        @Override
        protected InputStream getStreamFromNetwork(String imageUri, Object extra) throws IOException {
            HttpGet httpRequest = new HttpGet(imageUri);
            httpRequest.setHeader("Accept", "*/*");//因为防火墙的关系必须设置 Header中的Accept项目
            HttpResponse response = httpClient.execute(httpRequest);
            HttpEntity entity = response.getEntity();
            BufferedHttpEntity bufHttpEntity = new BufferedHttpEntity(entity);
            return bufHttpEntity.getContent();
        }
    }

    //  Android中如何捕获未捕获的异常(重要)
//    @Override
//    public void uncaughtException(final Thread thread, final Throwable ex) {
//        new Thread(new Runnable() {
//            @Override public void run() {
//                Looper.prepare();
//                System.out.println(Thread.currentThread());
//                Toast.makeText(getApplicationContext(), "thread=" + thread.getId() + " ex="
//                        + ex.toString(),Toast.LENGTH_SHORT).show(); Looper.loop(); } }).start();
//        SystemClock.sleep(3000);
//        android.os.Process.killProcess(android.os.Process.myPid());
//    }

}