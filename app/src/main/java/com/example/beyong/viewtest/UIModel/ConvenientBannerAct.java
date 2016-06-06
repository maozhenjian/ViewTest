package com.example.beyong.viewtest.UIModel;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.beyong.viewtest.R;

import java.util.ArrayList;

import javax.xml.transform.Transformer;

//轮播图
//github 地址   https://github.com/saiwu-bigkoo/Android-ConvenientBanner
public class ConvenientBannerAct extends AppCompatActivity implements OnItemClickListener {
    private ConvenientBanner convenientBanner;//顶部广告栏控件

    private ArrayList<Integer> localImages = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convenient_banner);
        convenientBanner = (ConvenientBanner) findViewById(R.id.convenientBanner);

        localImages.add(R.mipmap.ic_launcher);
        localImages.add(R.mipmap.ic_launcher);
        localImages.add(R.mipmap.ic_launcher);

        convenientBanner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, localImages)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.mipmap.indicator, R.mipmap.indicator_focused}) ;


    }

    @Override
    public void onItemClick(int position) {

    }


    public class LocalImageHolderView implements Holder<Integer> {
        private ImageView imageView;
        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, Integer data) {
            imageView.setImageResource(data);
        }
    }

}
