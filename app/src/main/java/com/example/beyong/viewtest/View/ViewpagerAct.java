package com.example.beyong.viewtest.View;

import android.annotation.TargetApi;
import android.app.Application;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.beyong.viewtest.BaseActivity;
import com.example.beyong.viewtest.MyApplication;
import com.example.beyong.viewtest.R;
import com.lidroid.xutils.view.annotation.ViewInject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.xutils.x;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewpagerAct extends BaseActivity {
    private List<String> urls = new ArrayList<>();
    private List<ImageView> indicator_imgs = new ArrayList<>();
    private MyAdapter adapter;// 图片轮播的adapter
    private static final int TIME = 2040;// 定时切换的时间
    private Runnable viewPageRunnable;

    private static int AD_HEIGHT = 515;
    private static int AD_WIDTH = 780;
    private String picName = "networkPic.jpg";//
    private ViewPager view_pager;
    private ImageView image;
    private View item;
    private LinearLayout.LayoutParams params;//尺寸

    private LayoutInflater inflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);


        view_pager= (ViewPager) findViewById(R.id.view_pager);
        //-----Image------------
        int width = MyApplication.getApplication().getScreenWidth();
        int height = MyApplication.getApplication().getScreenHeight();
        params = new LinearLayout.LayoutParams(width, width * AD_HEIGHT / AD_WIDTH);

        urls.add("http://img2.3lian.com/img2007/10/28/123.jpg");
        urls.add("http://down.tutu001.com/d/file/20101129/2f5ca0f1c9b6d02ea87df74fcc_560.jpg");
        urls.add("http://zx.kaitao.cn/UserFiles/Image/beijingtupian6.jpg");
        //轮播图
        setItemPhotoData();
        //页面切换动画
        view_pager.setPageTransformer(true,new PagerTransDefine());
//        initRunnable();
    }


    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {

                default:
                    break;
            }
        }
    };


    @Override
    protected void onPause() {
        super.onPause();
//        initRunnable();
    }

    /**
     * 初始化引导图标 动态创建多个小圆点，然后组装到线性布局里
     */
    private void initIndicator() {

        ImageView imgView;
        View v = findViewById(R.id.indicator);// 线性水平布局，负责动态调整导航图标

        for (int i = 0; i < urls.size(); i++) {
            // for (int i = 0; i < 7; i++) {
            imgView = new ImageView(this);
            LinearLayout.LayoutParams params_linear = new LinearLayout.LayoutParams(
                    10, 10);
            params_linear.setMargins(7, 10, 7, 10);
            imgView.setLayoutParams(params_linear);
            // indicator_imgs[i] = imgView;
            indicator_imgs.add(imgView);// =imgView;

            if (i == 0) { // 初始化第一个为选中状态
                indicator_imgs.get(i).setBackgroundResource(
                        R.mipmap.indicator_focused);
                // indicator_imgs[i].setBackgroundResource(R.drawable.indicator_focused);
            } else {
                // indicator_imgs[i].setBackgroundResource(R.drawable.indicator);
                indicator_imgs.get(i).setBackgroundResource(
                        R.mipmap.indicator);
            }
            // ((ViewGroup)v).addView(indicator_imgs[i]);
            ((ViewGroup) v).addView(indicator_imgs.get(i));

        }
    }


    private void setItemPhotoData() {
        // 这里开始加载图片轮播的数据
        List<View> list = new ArrayList<View>();
        inflater = LayoutInflater.from(this);

        /**
         * 创建多个item （每一条viewPager都是一个item） 从服务器获取完数据（如文章标题、url地址） 后，再设置适配器
         */
        for (int i = 0; i < urls.size(); i++) {
            item = inflater.inflate(R.layout.item_photo_main, null);
            list.add(item);
        }

        // 创建适配器， 把组装完的组件传递进去
        adapter = new MyAdapter(list);
        view_pager.setAdapter(adapter);

        // 绑定动作监听器：如翻页的动画
        view_pager.setOnPageChangeListener(new MyListener());
        initIndicator();
    }


    /**
     * 适配器，负责装配 、销毁 数据 和 组件 。
     */
    private class MyAdapter extends PagerAdapter {

        private List<View> mList;

        private AsyncImageLoader asyncImageLoader;

        public MyAdapter(List<View> list) {
            mList = list;
            asyncImageLoader = new AsyncImageLoader();
        }

        /**
         * Return the number of views available.
         */
        @Override
        public int getCount() {
            return mList.size();
        }

        /**
         * Remove a page for the given position. 滑动过后就销毁 ，销毁当前页的前一个的前一个的页！
         * instantiateItem(View container, int position) This method was
         * deprecated in API level . Use instantiateItem(ViewGroup, int)
         */

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mList.get(position));
            // PagerAdapter只缓存三张要显示的图片，如果滑动的图片超出了缓存的范围，就会调用这个方法，将图片销毁
            //        container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        /**
         * Create the page for the given position.
         */
        @Override
        public Object instantiateItem(final ViewGroup container,
                                      final int position) {
            Drawable cachedImage = asyncImageLoader.loadDrawable(
                    urls.get(position), new AsyncImageLoader.ImageCallback() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void imageLoaded(Drawable imageDrawable,
                                                String imageUrl) {

                            View view = mList.get(position);
                            image = ((ImageView) view.findViewById(R.id.image));
                            image.setBackground(imageDrawable);
                            image.setLayoutParams(params);//// TODO: 15/10/20
                            image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                            container.removeView(mList.get(position));
                            container.addView(mList.get(position));
                            // adapter.notifyDataSetChanged();

                        }
                    });

            View view = mList.get(position);
            image = ((ImageView) view.findViewById(R.id.image));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                image.setBackground(cachedImage);
            }

            container.removeView(mList.get(position));
            container.addView(mList.get(position));
            // adapter.notifyDataSetChanged();

            return mList.get(position);

        }

    }

    /**
     * 动作监听器，可异步加载图片
     */
    private class MyListener implements ViewPager.OnPageChangeListener {
        /**3.onPageScrollStateChanged(int state)：这个方法在手指操作屏幕的时候发生变化。
         * 有三个值：0（END）,1(PRESS) , 2(UP) 。
         当用手指滑动翻页时，手指按下去的时候会触发这个方法，state值为1，
         手指抬起时，如果发生了滑动（即使很小），这个值会变为2，然后最后变为0 。
         总共执行这个方法三次。一种特殊情况是手指按下去以后一点滑动也没有发生，
         这个时候只会调用这个方法两次，state值分别是1,0 。
         当setCurrentItem翻页时，会执行这个方法两次，state值分别为2 , 0 。


         arg0 ==1的时候表示正在滑动，arg0==2的时候表示滑动完毕了，arg0==0的时候表示什么都没做，就是停在
         * */
        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == 0) {
                // new MyAdapter(null).notifyDataSetChanged();
            }
        }
/**
**arg0 ==1的时候表示正在滑动，arg0==2的时候表示滑动完毕了，arg0==0的时候表示什么都没做，就是停在
* **/
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            Log.e("onPageScrolled","onPageScrolled:+"+"  arg0:"+arg0);
        }

        @Override
        public void onPageSelected(int position) {
            Log.e("TAG","onPageSelected:+"+"           position:"+position);
            // 改变所有导航的背景图片为：未选中
            // for (int i = 0; i < indicator_imgs.length; i++) {
            for (int i = 0; i < indicator_imgs.size(); i++) {

                indicator_imgs.get(i).setBackgroundResource(
                        R.mipmap.indicator);

            }
            // 改变当前背景图片为：选中
            indicator_imgs.get(position).setBackgroundResource(
                    R.mipmap.indicator_focused);
        }
    }


    /**
     * 异步加载图片
     */
    static class AsyncImageLoader {

        // 软引用，使用内存做临时缓存 （程序退出，或内存不够则清除软引用）
        private HashMap<String, SoftReference<Drawable>> imageCache;

        public AsyncImageLoader() {
            imageCache = new HashMap<String, SoftReference<Drawable>>();
        }

        /**
         * 定义回调接口
         */
        public interface ImageCallback {
            public void imageLoaded(Drawable imageDrawable, String imageUrl);
        }

        /**
         * 创建子线程加载图片 子线程加载完图片交给handler处理（子线程不能更新ui，而handler处在主线程，可以更新ui）
         * handler又交给imageCallback，imageCallback须要自己来实现，在这里可以对回调参数进行处理
         *
         * @param imageUrl      ：须要加载的图片url
         * @param imageCallback ：
         * @return
         */
        public Drawable loadDrawable(final String imageUrl,
                                     final ImageCallback imageCallback) {

            // 如果缓存中存在图片 ，则首先使用缓存
            if (imageCache.containsKey(imageUrl)) {
                SoftReference<Drawable> softReference = imageCache
                        .get(imageUrl);
                Drawable drawable = softReference.get();
                if (drawable != null) {
                    imageCallback.imageLoaded(drawable, imageUrl);// 执行回调
                    return drawable;
                }
            }

            /**
             * 在主线程里执行回调，更新视图
             */
            final Handler handler = new Handler() {
                public void handleMessage(Message message) {
                    imageCallback.imageLoaded((Drawable) message.obj, imageUrl);
                }
            };

            /**
             * 创建子线程访问网络并加载图片 ，把结果交给handler处理
             */
            new Thread() {
                @Override
                public void run() {
                    Drawable drawable = loadImageFromUrl(imageUrl);
                    // 下载完的图片放到缓存里
                    imageCache.put(imageUrl, new SoftReference<Drawable>(
                            drawable));
                    Message message = handler.obtainMessage(0, drawable);
                    handler.sendMessage(message);
                }
            }.start();

            return null;
        }

        /**
         * 下载图片 （注意HttpClient 和httpUrlConnection的区别）
         */
        public Drawable loadImageFromUrl(String url) {
            try {
                HttpClient client = new DefaultHttpClient();
                client.getParams().setParameter(
                        CoreConnectionPNames.CONNECTION_TIMEOUT, 1000 * 15);
                HttpGet get = new HttpGet(url);
                HttpResponse response;
                response = client.execute(get);
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity entity = response.getEntity();
                    Drawable d = Drawable.createFromStream(entity.getContent(),
                            "src");
                    d.setBounds(0, 0, 0, 0);
//					d.setBounds(0, 0,  768,408);

                    return d;
                } else {
                    return null;
                }
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        // 清除缓存
        public void clearCache() {

            if (this.imageCache.size() > 0) {

                this.imageCache.clear();
            }
        }
    }

    // 定时切换 TODO：让on Pause 上添加代码 maybe the handler will error
    protected void initRunnable() {
        viewPageRunnable = new Runnable() {

            @Override
            public void run() {
                int nowIndex = view_pager.getCurrentItem();
                int count = view_pager.getAdapter().getCount();
                // 如果下一张的索引大于最后一张，则切换到第一张
                if (nowIndex + 1 >= count) {
                    view_pager.setCurrentItem(0);
                } else {
                    view_pager.setCurrentItem(nowIndex + 1);
                }
                handler.postDelayed(viewPageRunnable, TIME);
            }
        };
        handler.postDelayed(viewPageRunnable, TIME);
    }


}
