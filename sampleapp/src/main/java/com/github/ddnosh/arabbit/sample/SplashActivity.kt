package com.github.ddnosh.arabbit.sample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.ddnosh.arabbit.sample.util.TimeUtils
import java.lang.Thread.sleep

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        calculateStartTime()
        Thread {
            sleep(1000)
            // 耗时任务，比如加载网络数据
            runOnUiThread {
                val intent: Intent = Intent().setClass(this, MainActivity::class.java)
                startActivity(intent)
                this@SplashActivity.finish()
            }
        }.start()
    }

    private fun calculateStartTime() {
        val coldStartTime: Long = TimeUtils.getTimeCalculate(TimeUtils.COLD_START)
        // 这里记录的TimeUtils.coldStartTime是指Application启动的时间，最终的冷启动时间等于Application启动时间+热启动时间
        TimeUtils.sColdStartTime = if (coldStartTime > 0) coldStartTime else 0
        TimeUtils.beginTimeCalculate(TimeUtils.HOT_START)
    }
}
