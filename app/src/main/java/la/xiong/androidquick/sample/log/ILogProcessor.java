package la.xiong.androidquick.sample.log;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public interface ILogProcessor {

    void init(LogConfig logConfig);

    void loadV(String vLog);

    void loadD(String dLog);

    void loadI(String iLog);

    void loadE(String eLog);
}
