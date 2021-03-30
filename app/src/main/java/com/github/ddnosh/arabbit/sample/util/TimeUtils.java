package com.github.ddnosh.arabbit.sample.util;

import java.util.HashMap;

/**
 * 计时统计工具类
 */
public class TimeUtils {
    private static HashMap<String, Long> sCalTimeMap = new HashMap<>();
    public static final String COLD_START = "cold_start";
    public static final String HOT_START = "hot_start";
    public static long sColdStartTime = 0;

    /**
     * 记录某个事件的开始时间
     * @param key 事件名称
     */
    public static void beginTimeCalculate(String key) {
        long currentTime = System.currentTimeMillis();
        sCalTimeMap.put(key, currentTime);
    }

    /**
     * 获取某个事件的运行时间
     *
     * @param key 事件名称
     * @return 返回某个事件的运行时间，调用这个方法之前没有调用 {@link #beginTimeCalculate(String)} 则返回-1
     */
    public static long getTimeCalculate(String key) {
        long currentTime = System.currentTimeMillis();
        Long beginTime = sCalTimeMap.get(key);
        if (beginTime == null) {
            return -1;
        } else {
            sCalTimeMap.remove(key);
            return currentTime - beginTime;
        }
    }

    /**
     * 清除某个时间运行时间计时
     *
     * @param key 事件名称
     */
    public static void clearTimeCalculate(String key) {
        sCalTimeMap.remove(key);
    }

    /**
     * 清除启动时间计时
     */
    public static void clearStartTimeCalculate() {
        clearTimeCalculate(HOT_START);
        clearTimeCalculate(COLD_START);
        sColdStartTime = 0;
    }
}