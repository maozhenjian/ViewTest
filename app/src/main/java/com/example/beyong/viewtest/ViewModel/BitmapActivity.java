package com.example.beyong.viewtest.ViewModel;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.beyong.viewtest.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class BitmapActivity extends Activity {
    private ImageView imageView;
    private Button btn;
    private String url = "http://a2.att.hudong.com/38/59/300001054794129041591416974.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);

        btn = (Button) findViewById(R.id.btn);
        imageView = (ImageView) findViewById(R.id.img);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new myAsyncTask(imageView).execute(url);
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }

    private class myAsyncTask extends AsyncTask<String, Void, Bitmap> {
        private ImageView img;

        public myAsyncTask(ImageView img2) {
            img = img2;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap = changeBitmap(getBitmapFromURL(strings[0]));
            Log.d("bitmap", bitmap.toString());
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            img.setImageBitmap(bitmap);

            /**
             .由于前面创建的Bitmap所占用的内存还没有回收，而导致引发OutOfMemory错误，;
             所以用下面方法判断是否回收。
             */

//            if(!bitmap.isRecycled())
//            {
//                  bitmap.recycle();
//                System.gc();
//            }
        }
    }

    /**
     * 从网络获取图片
     */
    public Bitmap getBitmapFromURL(String urlString) {
        Bitmap bitmap;
        InputStream is = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            is = new BufferedInputStream(connection.getInputStream());
            bitmap = BitmapFactory.decodeStream(is);
            connection.disconnect();
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 将图片高宽和的大小kB压缩
     */

    public Bitmap changeBitmap(Bitmap rawBitmap) {
        //得到图片原始的高宽
        int rawHeight = rawBitmap.getHeight();
        int rawWidth = rawBitmap.getWidth();
        // 设定图片新的高宽
        int newHeight = 600;
        int newWidth = 600;
        // 计算缩放因子
        float heightScale = ((float) newHeight) / rawHeight;
        float widthScale = ((float) newWidth) / rawWidth;
        // 新建立矩阵
        Matrix matrix = new Matrix();
        matrix.postScale(heightScale, widthScale);
        // 设置图片的旋转角度
        // matrix.postRotate(-30);
        // 设置图片的倾斜
        // matrix.postSkew(0.1f, 0.1f);
        // 将图片大小压缩
        // 压缩后图片的宽和高以及kB大小均会变化
        Bitmap newBitmap = Bitmap.createBitmap(rawBitmap, 0, 0, rawWidth, rawHeight, matrix, true);
        return newBitmap;
    }

    /**
     *Res资源加载方式
     * *BitmapFactory这个类提供了多个解析方法(decodeByteArray, decodeFile, decodeResource等)用于创建Bitmap对象，
     * 我们应该根据图片的来源选择合适的方法。比如SD卡中的图片可以使用decodeFile方法，网络上的图片可以使用decodeStream方法
     */

    //  Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.t2);

    /**
     将Bitmap转换为Drawable   Drawable转Bitmap
     */

}

