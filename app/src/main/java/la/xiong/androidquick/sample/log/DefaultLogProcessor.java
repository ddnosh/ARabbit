package la.xiong.androidquick.sample.log;

import android.util.Log;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class DefaultLogProcessor implements ILogProcessor {

    @Override
    public void init(LogConfig logConfig) {

    }

    @Override
    public void loadV(String vLog) {
        Log.v("DefaultLogProcessor", "defaultlog:" + vLog);
    }

    @Override
    public void loadD(String dLog) {
        Log.d("DefaultLogProcessor", "defaultlog:" + dLog);
    }

    @Override
    public void loadI(String iLog) {
        Log.i("DefaultLogProcessor", "defaultlog:" + iLog);
    }

    @Override
    public void loadE(String eLog) {
        Log.e("DefaultLogProcessor", "defaultlog:" + eLog);
    }
}
