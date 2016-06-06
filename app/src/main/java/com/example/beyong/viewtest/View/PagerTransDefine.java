package com.example.beyong.viewtest.View;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 *Created by 振坚 on 2016/2/2.
 * 参数position
 *给定界面的位置相对于屏幕中心的偏移量。在用户滑动界面的时候，是动态变化的。
 *那么我们可以将position的值应用于setAlpha(), setTranslationX(), or setScaleY()方法，
 *从而实现自定义的动画效果。
 *另 外在ViewPager滑动时，内存中存活的Page都会执行transformPage方法，
 *在滑动过程中涉及到两个Page，当前页和下一页，而它们 的position值是相反的（
 *因为是相对运动,一个滑入一个滑出），
 *比如，页面A向右滑动到屏幕一半，页面B也正好处于一半的位置，
 *那么A和B的 position为：0.5 和 -0.5
 *position ==  0 ：当前界面位于屏幕中心的时候
 *position ==  1 ：当前Page刚好滑出屏幕右侧
 *position == -1 ：当前Page刚好滑出屏幕左侧
 */
public class PagerTransDefine implements ViewPager.PageTransformer {
    private static float MIN_SCALE=0.75f;
        @Override
        public void transformPage(View page, float position) {
            int pageWith=page.getWidth();
            if (position<-1){
                page.setAlpha(0);
            }
            else if(position<=0){
                page.setAlpha(1+position);
                page.setTranslationX(0);
                page.setScaleX(1);
                page.setScaleY(1);
            }else if(position<=1){
                page.setAlpha(1-position);
                page.setTranslationX(pageWith*-position);
                float scaleFavtor=MIN_SCALE+(1-MIN_SCALE)*(1-Math.abs(position));
                page.setScaleX(scaleFavtor);
                page.setScaleY(scaleFavtor);
            }else{
                page.setAlpha(0);
            }
    }
}
