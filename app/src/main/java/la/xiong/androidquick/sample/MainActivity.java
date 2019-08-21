package la.xiong.androidquick.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import la.xiong.androidquick.module.network.retrofit.RetrofitManager;
import la.xiong.androidquick.module.network.retrofit.exeception.ApiException;
import la.xiong.androidquick.module.rxjava.BaseObserver;
import la.xiong.androidquick.tool.LogUtil;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        RetrofitManager mRetrofitManager = new RetrofitManager();
        mRetrofitManager.initBaseUrl("http://gank.io/api/");
        mRetrofitManager.createApi(GankApis.class)
                .getHistoryDate()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<GankRes<List<String>>>() {

                    @Override
                    public void onError(ApiException exception) {
                        LogUtil.e(TAG, "error:" + exception.getMessage());
                    }

                    @Override
                    public void onSuccess(GankRes<List<String>> listGankRes) {
                        LogUtil.i(TAG, listGankRes.getResults().toString());
                    }
                });
    }
}
