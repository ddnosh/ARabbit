package com.androidwind.androidquick.sample;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.androidwind.androidquick.module.glide.GlideManager;
import com.androidwind.androidquick.module.retrofit.RetrofitManager;
import com.androidwind.androidquick.module.retrofit.exeception.ApiException;
import com.androidwind.androidquick.module.rxjava.BaseObserver;
import com.androidwind.androidquick.util.LogUtil;
import com.androidwind.androidquick.util.ToastUtil;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class DemoActivity extends BaseActivity {

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_demo;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        // RetrofitManager.getInstance().createApi(GankApis.class)
        //         .getHistoryDate()
        //         .subscribeOn(Schedulers.io())
        //         .observeOn(AndroidSchedulers.mainThread())
        //         .subscribe(new BaseObserver<GankRes<List<String>>>() {
        //
        //             @Override
        //             public void onError(ApiException exception) {
        //                 LogUtil.e(TAG, "error:" + exception.getMessage());
        //             }
        //
        //             @Override
        //             public void onSuccess(GankRes<List<String>> listGankRes) {
        //                 LogUtil.i(TAG, listGankRes.getResults().toString());
        //             }
        //         });

        RetrofitManager.getInstance().initBaseUrl("http://gank.io/api/");
    }

    public void OpenNetwork(View v) {
        RetrofitManager.getInstance().createApi(GankApis.class)
                .getHistoryDate()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<GankRes<List<String>>>() {

                    @Override
                    public void onError(ApiException exception) {
                        LogUtil.e(TAG, "error:" + exception.getMessage());
                        ToastUtil.showToast("Fail!");
                    }

                    @Override
                    public void onSuccess(GankRes<List<String>> listGankRes) {
                        LogUtil.i(TAG, listGankRes.getResults().toString());
                        ToastUtil.showToast("Success!");
                    }
                });
    }

    public void OpenImage(View v) {
        GlideManager
                .loadNet("https://hbimg.huabanimg.com/ca0077ed805df7a1566d68a74cae73c59994929b73f68-nkFwhw_fw658", (ImageView)findViewById(R.id.imageView));
    }
}
