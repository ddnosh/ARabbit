package la.xiong.androidquick.sample.log;

import com.androidwind.log.BuildConfig;
import com.androidwind.log.TinyLog;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class TinyLogProcessor implements ILogProcessor {

    @Override
    public void init(LogConfig logConfig) {
        if (logConfig != null) {
            TinyLog.config()
                    .setEnable(logConfig.isEnable())
                    .setWritable(logConfig.isWritable())
                    .setLogLevel(logConfig.getLevel())
                    .apply();
        }
    }

    @Override
    public void loadV(String vLog) {
        TinyLog.v("tinylog:" + vLog);
    }

    @Override
    public void loadD(String dLog) {
        TinyLog.d("tinylog:" + dLog);
    }

    @Override
    public void loadI(String iLog) {
        TinyLog.i("tinylog:" + iLog);
    }

    @Override
    public void loadE(String eLog) {
        TinyLog.e("tinylog:" + eLog);
    }
}
