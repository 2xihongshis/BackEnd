package cn.org.twotomatoes.monitor.util;

/**
 * url 中的 ':' 影响 redis 中的自动 ':' 分割, 替换一下
 *
 * @author HeYunjia
 */
public class URLUtils {

    private static final char colon = ':';
    private static final char hyphen = '-';

    /**
     * 将 url 中的第三个 '/' 之前的 ':' 转换为 '-'
     *
     * @param url 原串
     * @return 返回转换后的串
     */
    public static String convert(String url) {
        return convert(url, false);
    }

    /**
     * 将 url 中的第三个 '/' 之前的 ':' 转换为 '-'
     *
     * @param url 原串
     * @param isRevert 为 true 时, 反转操作, 默认 false
     * @return 返回目标串
     */
    public static String convert(String url, boolean isRevert) {
        char src = isRevert ? hyphen : colon;
        char des = isRevert ? colon : hyphen;
        int num = 0;
        char[] chs = url.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] == src) chs[i] = des;
            if (chs[i] == '/') num += 1;
            if (num == 3) break;
        }
        return new String(chs);
    }

}
