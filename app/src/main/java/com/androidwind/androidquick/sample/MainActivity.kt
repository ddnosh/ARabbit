package com.androidwind.androidquick.sample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.androidwind.androidquick.sample.coroutine.CoroutineActivity
import com.androidwind.androidquick.sample.databinding.ActivityMainBinding
import com.androidwind.androidquick.sample.dialog.DialogActivity
import com.androidwind.androidquick.sample.image.GlideActivity
import com.androidwind.androidquick.sample.mvvm.LoginActivity
import com.androidwind.androidquick.sample.mvvm.livedata.LiveDataActivity
import com.androidwind.androidquick.sample.network.NetworkActivity
import com.androidwind.androidquick.sample.util.TimeUtils
import com.androidwind.androidquick.util.ToastUtil

class MainActivity : AppCompatActivity() {
    private val TAG = "app_start_timer"
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        initView()
    }

    private fun initView() {
        //多页面状态
        viewBinding.multipleStatusView.setOnClickListener {
            ToastUtil.showToast("retried.")
        }
//        showLoadingDialog()

        viewBinding.button1.setOnClickListener {
            val intent: Intent = Intent().setClass(this, LoginActivity::class.java)
            startActivity(intent)
        }
        viewBinding.button2.setOnClickListener {
            val intent: Intent = Intent().setClass(this, LiveDataActivity::class.java)
            startActivity(intent)
        }
        viewBinding.button3.setOnClickListener {
            val intent: Intent = Intent().setClass(this, NetworkActivity::class.java)
            startActivity(intent)
        }
        viewBinding.button4.setOnClickListener {
            val intent: Intent = Intent().setClass(this, GlideActivity::class.java)
            startActivity(intent)
        }
        viewBinding.button5.setOnClickListener {
            val intent: Intent = Intent().setClass(this, DialogActivity::class.java)
            startActivity(intent)
        }
        viewBinding.button6.setOnClickListener {
            val intent: Intent = Intent().setClass(this, CoroutineActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        if (hasFocus) {
            val hotStartTime = TimeUtils.getTimeCalculate(TimeUtils.HOT_START);
            Log.d(TAG, "热启动时间:$hotStartTime")
            if (TimeUtils.sColdStartTime > 0 && hotStartTime > 0) {
                // 真正的冷启动时间 = Application启动时间 + 热启动时间
                val coldStartTime = TimeUtils.sColdStartTime + hotStartTime;
                Log.d(TAG, "application启动时间:" + TimeUtils.sColdStartTime)
                Log.d(TAG, "冷启动时间:$coldStartTime")
                // 过滤掉异常启动时间
                if (coldStartTime < 50000) {
                    // 上传冷启动时间coldStartTime
                }
            } else if (hotStartTime > 0) {
                // 过滤掉异常启动时间
                if (hotStartTime < 30000) {
                    // 上传热启动时间hotStartTime
                }
            }
        }
    }
}