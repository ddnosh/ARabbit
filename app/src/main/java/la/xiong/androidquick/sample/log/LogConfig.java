package la.xiong.androidquick.sample.log;

import java.io.File;
import java.io.PrintWriter;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class LogConfig {

    public static final int LOG_V = 0;
    public static final int LOG_D = 1;
    public static final int LOG_I = 2;
    public static final int LOG_W = 3;
    public static final int LOG_E = 4;

    private boolean enable;
    private boolean writable;
    private String path;
    private int size = 1;
    private String encrypt;
    private int level = LOG_V;

    public boolean isEnable() {
        return enable;
    }

    public LogConfig setEnable(boolean enable) {
        this.enable = enable;
        return this;
    }

    public boolean isWritable() {
        return writable;
    }

    public LogConfig setWritable(boolean writable) {
        this.writable = writable;
        return this;
    }

    public String getPath() {
        return path;
    }

    public LogConfig setPath(String path) {
        this.path = path;
        return this;
    }

    public int getSize() {
        return size;
    }

    public LogConfig setSize(int size) {
        this.size = size;
        return this;
    }

    public String getEncrypt() {
        return encrypt;
    }

    public LogConfig setEncrypt(String encrypt) {
        this.encrypt = encrypt;
        return this;
    }

    public int getLevel() {
        return level;
    }

    public LogConfig setLevel(int level) {
        this.level = level;
        return this;
    }
}
