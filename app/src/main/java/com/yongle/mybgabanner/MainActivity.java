package com.yongle.mybgabanner;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.Arrays;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

public class MainActivity extends Activity implements BGABanner.Delegate<ImageView, String>, BGABanner.Adapter<ImageView, String> {
    private BGABanner mDefaultBanner;
    private String  imgs[] = { "http://7xk9dj.com1.z0.glb.clouddn.com/banner/imgs/16.png",
             "http://7xk9dj.com1.z0.glb.clouddn.com/banner/imgs/17.png",  "http://7xk9dj.com1.z0.glb.clouddn.com/banner/imgs/18.png"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDefaultBanner = findViewById(R.id.banner_main_default);
        /**
         * 设置是否开启自动轮播，需要在 setData 方法之前调用，并且调了该方法后必须再调用一次 setData 方法
         * 例如根据图片当图片数量大于 1 时开启自动轮播，等于 1 时不开启自动轮播
         */
        mDefaultBanner.setAutoPlayAble(imgs.length> 1);
        mDefaultBanner.setAdapter(this);
        mDefaultBanner.setData(Arrays.asList(imgs),null);
        mDefaultBanner.setDelegate(this);
    }


    @Override
    public void onBannerItemClick(BGABanner banner, ImageView itemView, @Nullable String model, int position) {
        Toast.makeText(banner.getContext(), "点击了第" + (position + 1) + "页", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fillBannerItem(BGABanner banner, ImageView itemView, @Nullable String model, int position) {
        Glide.with(itemView.getContext())
                .load(model)
                .apply(new RequestOptions().placeholder(R.drawable.holder).error(R.drawable.holder).dontAnimate().centerCrop())
                .into(itemView);
    }
}
