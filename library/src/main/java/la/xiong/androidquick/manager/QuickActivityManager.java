package la.xiong.androidquick.manager;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class QuickActivityManager {

    private static final String TAG = "QuickActivityManager";

    private static QuickActivityManager instance = null;
    private List<Activity> mActivities = new LinkedList<Activity>();

    private QuickActivityManager() {

    }

    public static QuickActivityManager getInstance() {
        if (null == instance) {
            synchronized (QuickActivityManager.class) {
                if (null == instance) {
                    instance = new QuickActivityManager();
                }
            }
        }
        return instance;
    }

    public int size() {
        return mActivities.size();
    }

    public synchronized Activity getForwardActivity() {
        return size() > 0 ? mActivities.get(size() - 1) : null;
    }

    public synchronized void addActivity(Activity activity) {
        mActivities.add(activity);
    }

    public synchronized void removeActivity(Activity activity) {
        if (mActivities.contains(activity)) {
            mActivities.remove(activity);
        }
    }

    public synchronized void clear() {
        for (int i = mActivities.size() - 1; i > -1; i--) {
            Activity activity = mActivities.get(i);
            removeActivity(activity);
            activity.finish();
            i = mActivities.size();
        }
    }

    public synchronized void clearToTop() {
        for (int i = mActivities.size() - 2; i > -1; i--) {
            Activity activity = mActivities.get(i);
            removeActivity(activity);
            activity.finish();
            i = mActivities.size() - 1;
        }
    }
}
