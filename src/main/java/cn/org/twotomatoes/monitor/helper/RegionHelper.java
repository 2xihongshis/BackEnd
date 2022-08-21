package cn.org.twotomatoes.monitor.helper;

import org.lionsoul.ip2region.xdb.Searcher;

/**
 * @author HeYunjia
 */
public class RegionHelper {

    /**
     * 根据 IP 获取国家
     *
     * @param ip IP 地址
     * @return 返回 IP 对应的国家的中文名字
     */
    public static String getCountry(String ip){

        String info;
        try {
            info = searcher.search(ip);
        } catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return info.substring(0, info.indexOf("|"));
    }

    /**
     * 根据 IP 获取省份
     *
     * @param ip IP地址
     * @return 返回 IP 对应的省份
     */
    public static String getProvince(String ip) {

        String info;
        try {
            info = searcher.search(ip);
        } catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        int start = 0, end = 0;
        for (int i = 0; i < info.length(); i++) {
            if (info.charAt(i) != '|') continue;

            end += 1;
            if (end == 2) start = i + 1;
            if (end == 3) {
                end = i;
                break;
            }
        }
        return info.substring(start, end);
    }

    private static Searcher searcher;

    public static void setSearcher(Searcher searcher) {
        RegionHelper.searcher = searcher;
    }
}
