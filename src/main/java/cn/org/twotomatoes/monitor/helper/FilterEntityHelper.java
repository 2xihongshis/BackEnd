package cn.org.twotomatoes.monitor.helper;

import cn.org.twotomatoes.monitor.entity.*;

/**
 * 过滤 entity 数据, 让其能够被数据库接受
 */
public class FilterEntityHelper {

    /**
     * 将 BlankScreen 数据过滤为数据库能接受的数据
     *
     * @param blankScreen 待过滤的数据
     * @return 返回过滤后的数据
     */
    public static BlankScreen format(BlankScreen blankScreen) {
        return blankScreen;
    }

    /**
     * 将 FetchInfo 数据过滤为数据库能接受的数据
     *
     * @param fetchInfo 待过滤的数据
     * @return 返回过滤后的数据
     */
    public static FetchInfo format(FetchInfo fetchInfo) {
        return fetchInfo;
    }

    /**
     * 将 FirstInputDelay 数据过滤为数据库能接受的数据
     *
     * @param firstInputDelay 待过滤的数据
     * @return 返回过滤后的数据
     */
    public static FirstInputDelay format(FirstInputDelay firstInputDelay) {
        return firstInputDelay;
    }

    /**
     * 将 JsError 数据过滤为数据库能接受的数据
     *
     * @param jsError 待过滤的数据
     * @return 返回过滤后的数据
     */
    public static JsError format(JsError jsError) {
        return jsError;
    }

    /**
     * 将 LoadTime 数据过滤为数据库能接受的数据
     *
     * @param loadTime 待过滤的数据
     * @return 返回过滤后的数据
     */
    public static LoadTime format(LoadTime loadTime) {
        return loadTime;
    }

    /**
     * 将 LongTask 数据过滤为数据库能接受的数据
     *
     * @param longTask 待过滤的数据
     * @return 返回过滤后的数据
     */
    public static LongTask format(LongTask longTask) {
        return longTask;
    }

    /**
     * 将 PaintTime 数据过滤为数据库能接受的数据
     *
     * @param paintTime 待过滤的数据
     * @return 返回过滤后的数据
     */
    public static PaintTime format(PaintTime paintTime) {
        return paintTime;
    }

    /**
     * 将 PromiseError 数据过滤为数据库能接受的数据
     *
     * @param promiseError 待过滤的数据
     * @return 返回过滤后的数据
     */
    public static PromiseError format(PromiseError promiseError) {
        return promiseError;
    }

    /**
     * 将 ResourceError 数据过滤为数据库能接受的数据
     *
     * @param resourceError 待过滤的数据
     * @return 返回过滤后的数据
     */
    public static ResourceError format(ResourceError resourceError) {
        return resourceError;
    }

    /**
     * 将 StayTime 数据过滤为数据库能接受的数据
     *
     * @param stayTime 待过滤的数据
     * @return 返回过滤后的数据
     */
    public static StayTime format(StayTime stayTime) {
        return stayTime;
    }

    /**
     * 将 VisitInfo 数据过滤为数据库能接受的数据
     *
     * @param visitInfo 待过滤的数据
     * @return 返回过滤后的数据
     */
    public static VisitInfo format(VisitInfo visitInfo) {
        return visitInfo;
    }

    /**
     * 将 XhrInfo 数据过滤为数据库能接受的数据
     *
     * @param xhrInfo 待过滤的数据
     * @return 返回过滤后的数据
     */
    public static XhrInfo format(XhrInfo xhrInfo) {
        return xhrInfo;
    }

    /**
     * 将 CustomError 数据过滤为数据库能接受的数据
     *
     * @param customError 待过滤的数据
     * @return 返回过滤后的数据
     */
    public static CustomError format(CustomError customError) {
        return customError;
    }

    /**
     * 将 CustomMessage 数据过滤为数据库能接受的数据
     *
     * @param customMessage 待过滤的数据
     * @return 返回过滤后的数据
     */
    public static CustomMessage format(CustomMessage customMessage) {
        return customMessage;
    }

    /**
     * 将 ResourceEntity 数据过滤为数据库能接受的数据
     *
     * @param resource 待过滤的数据
     * @return 返回过滤后的数据
     */
    public static ResourceEntity format(ResourceEntity resource) {
        return resource;
    }
}
